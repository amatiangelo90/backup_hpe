package com.hpe.dataload.generator.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.intergration.DataloadGeneratorResult;
import com.hpe.dataload.generator.model.dataloadmodel.*;
import com.hpe.dataload.generator.model.intfilemodel.BlockExecutionFlowType;
import com.hpe.dataload.generator.model.intfilemodel.DataSetItem;
import com.hpe.dataload.generator.model.intfilemodel.ItemType;
import com.hpe.dataload.generator.orm.model.ActionDetails;
import com.hpe.dataload.generator.utils.configuration.ConfigurationClass;
import com.hpe.dataload.generator.utils.configuration.ConfigurationClassLinux;
import com.hpe.dataload.generator.utils.configuration.ConfigurationClassWindows;
import com.hpe.ted.model.TbsFlow;
import org.hibernate.annotations.Check;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataloadGenUtils {


    private static final Logger logger = Logger.getLogger(DataloadGenUtils.class.getName());

    public static final String XML_EXTENSION                = "^.*\\.(xml)$";
    public static final String EMPTY_STRING                 = "";
    public static final String SPECIAL_CHAR_EX              = "[^A-Za-z0-9_ \\-]";
    public static final String SPECIAL_CHAR_FILEPATH        = "[^A-Za-z0-9_ .:\\\\/]";
    private static final String DATALOAD_PATTERN            = "dataloadGen_";
    private static final String XML_EXT                     = ".xml";
    private static final String OS_NAME                     = "os.name";
    private static final String LINUX_OS                    = "linux";
    private static final String WINDOWS_OS                  = "windows";
    public static final String EXIT                         = "EXIT";
    private static final String NEXT_GROUP_PLACEHOLDER      = "NEXT_G_";

    private static Set<String> hashSetNextGroup = new HashSet<>();
    private static Set<Checkpoint> checkpointSet = new HashSet<>();
    private static ObjectMapper mapper = new ObjectMapper();
    /**
     * The final file will be stored as "dataload_${techname}.xml" into the directory where the input file used is stored
     *
     *
     * @param dataloadTD
     * @param fullPath
     * @param solutionName
     * @param dataloadVersion
     * @param versionTED
     * @param description
     * @param operatorName
     *
     * @throws IOException
     */

    public static DataloadGeneratorResult saveResultIntoExternalFile(TD dataloadTD,
                                                                     String fullPath,
                                                                     String solutionName,
                                                                     String dataloadVersion,
                                                                     String versionTED,
                                                                     String description,
                                                                     String operatorName,
                                                                     boolean generateActionStructure,
                                                                     boolean generateWorkflowsStructure,
                                                                     boolean generateGroupsStructure,
                                                                     List<String> nextGroupList) throws DataloadGeneratorException {


        String dataload;

        try {
            dataload = DataloadGenUtils.getStringXmlFromTDObject(dataloadTD);

            String fileName = DATALOAD_PATTERN + solutionName +"-"+ dataloadVersion + XML_EXT;
            checkFileNameFormatIntegrity(fullPath + fileName, XML_EXTENSION);


            Map<String,String> stringStringMap = new LinkedHashMap<>();

            String currentRanId = "TED_" + UUID.randomUUID();
            stringStringMap.put("Author", "AmatiCorp®\n");
            stringStringMap.put("Solution", solutionName);
            stringStringMap.put("Dataload ID", currentRanId+"\n");
            stringStringMap.put("Dataload version", dataloadVersion);
            stringStringMap.put("Service Activator Version", versionTED);
            stringStringMap.put("Operator" , operatorName+"");
            stringStringMap.put("Description", description + "\n");

            if(generateGroupsStructure){
                stringStringMap.put("GROUPS DETAILS" , "#" + String.valueOf(dataloadTD.getGroups().getGroup().size()));
                stringStringMap.put("------------------------------" , "------------------------------");
                for(GroupType groupType : dataloadTD.getGroups().getGroup()){
                    stringStringMap.put(groupType.getName() , "ORDER " + groupType.getOrder() + " " + getGroupStatus(groupType, nextGroupList) + "\n");
                    stringStringMap.put(" - StateIndexes " + groupType.getName(), String.valueOf(groupType.getStates().getName()));
                    stringStringMap.put("## NextGroups called by " + groupType.getName(), getNextGroupList(groupType)+ "\n ");
                }
                stringStringMap.put("-------------------------------" , "-------------------------------\n");
            }

            if(generateActionStructure) {
                if(dataloadTD.getActions() != null) {
                    stringStringMap.put("Actions", getActionNameList(dataloadTD) + "\n");
                }
            }

            if(generateWorkflowsStructure) {
                if(dataloadTD.getWorkflowTemplates() != null) {
                    stringStringMap.put("Workflows", getWorkflowNameList(dataloadTD) + "\n");
                }
            }

            if(true){
                if(dataloadTD.getHelpTexts() != null){
                    stringStringMap.put("HelpTexts", getHelpTextNameList(dataloadTD) + "\n");
                }
            }

            stringStringMap.put("Date", new Date().toString() + "\n\n");

            dataload = addCommentsToXmlGenerated(dataload, stringStringMap);

            writeObjectOnFile(dataload, fullPath+fileName);
            printOutputInfo(fullPath + fileName, solutionName, dataloadVersion, versionTED, operatorName, description );

            return DataloadGeneratorResult
                    .builder()
                    .dataloadId(currentRanId)
                    .dataloadXml(dataload)
                    .build();

        }
        catch (IOException e) {
            logger.severe("ERROR - Exception is been throwed during the saving process of the dataload. Exception: " + e);
            throw new DataloadGeneratorException("ERROR - Exception is been throwed during the saving process of the dataload. Exception: " + e);
        }catch (Exception e){
            logger.severe("ERROR - Exception is been throwed during the saving process of the dataload. Exception: " + e);
            throw new DataloadGeneratorException("ERROR - Exception is been throwed during the saving process of the dataload. Exception: " + e);
        }
    }

    private static List<String> getHelpTextNameList(TD dataloadTD) {
        List<String> helptextList = new ArrayList<>();
        Iterator<HelpTextType> iterator = dataloadTD.getHelpTexts().getHelpText().iterator();
        while (iterator.hasNext()){
            helptextList.add(iterator.next().getName());
        }
        return helptextList;
    }

    /**
     *
     * This method save a file to a file system
     *
     * It needs in input a Object String and the path where to save
     * @param objectString
     * @param path
     * @throws DataloadGeneratorException
     */
    public static void writeObjectOnFile(String objectString, String path) throws DataloadGeneratorException {
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path), "UTF8"));
            out.write(objectString);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot write on file. " + e.getMessage());
        }finally {
            try {
                if(out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new DataloadGeneratorException("Error - Cannot write on file. " + e.getMessage());
            }
        }
    }


    private static String getNextGroupList(GroupType groupType) {
        List<String> nextGroupsList = new ArrayList<>();

        for(GroupType.Indexes.Index index : groupType.getIndexes().getIndex()){
            if(index.getNextGroup() != null){
                nextGroupsList.add(index.getNextGroup());
            }
        }
        if(nextGroupsList.size() == 0)
            return "-";
        return String.valueOf(nextGroupsList);
    }

    private static String getGroupStatus(GroupType groupType, List<String> nextGroupList) {
        if(nextGroupList == null){
            return EMPTY_STRING;
        }
        if(groupType.getOrder() == 0){
            return EMPTY_STRING;
        }else{
            if(nextGroupList.contains(groupType.getName())){
                return EMPTY_STRING;
            }else{
                return "[###WARNING: Current group is never called by any group!###]";
            }
        }
    }

    public static DataloadGeneratorResult saveResultIntoExternalFile(TD dataloadTD,
                                                                     String fullPath,
                                                                     String solutionName,
                                                                     String dataloadVersion,
                                                                     String versionTED,
                                                                     String description,
                                                                     String operatorName,
                                                                     boolean generateActionStructure,
                                                                     boolean generateWorkflowsStructure,
                                                                     boolean generateGroupsStructure) throws DataloadGeneratorException {


        DataloadGeneratorResult dataloadGeneratorResult = saveResultIntoExternalFile(dataloadTD,
                fullPath,
                solutionName,
                dataloadVersion,
                versionTED,
                description,
                operatorName,
                generateActionStructure,
                generateWorkflowsStructure,
                generateGroupsStructure, null);
        return dataloadGeneratorResult;
    }

    private static List<String> getActionNameList(TD dataloadTD) {
        List<String> actionList = new ArrayList<>();
        Iterator<ActionType> iterator = dataloadTD.getActions().getAction().iterator();
        while (iterator.hasNext()){
            actionList.add(iterator.next().getName());
        }
        return actionList;
    }

    private static List<String> getWorkflowNameList(TD dataloadTD) {
        List<String> wfList = new ArrayList<>();
        Iterator<WorkflowTemplateType> iterator = dataloadTD.getWorkflowTemplates().getWorkflowTemplate().iterator();
        while (iterator.hasNext()){
            wfList.add(iterator.next().getWorkflow());
        }
        return removeDuplicatedValueFromListOfString(wfList);
    }

    /**
     *
     * This method is used to convert the modelized dataload into string to save as file on file system (have look at the dataloadmodel section)
     *
     * That file is the final result that is going to be deployed
     *
     * @param tdTypeType
     * @return
     * @throws JAXBException
     */

    public static String getStringXmlFromTDObject(TD tdTypeType) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TD.class);

        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        m.marshal(tdTypeType, sw);
        return sw.toString();
    }

    public static String buildXmlStringFromDataSetObject(DataSetItem dataSet) throws DataloadGeneratorException {

        try {
            JAXBContext context = JAXBContext.newInstance(DataSetItem.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            m.marshal(dataSet, sw);
            return sw.toString();
        } catch (PropertyException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException(e);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException(e);
        }
    }

    public static String jaxbObjectToXML_TbsFlow(TbsFlow tbsFlow) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TbsFlow.class);

        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter sw = new StringWriter();
        m.marshal(tbsFlow, sw);
        return sw.toString();
    }


    public static String addCommentsToXmlGenerated(String dataload, Map<String, String> commentsMap) throws IOException, SAXException, ParserConfigurationException, TransformerException {

        Document doc = convertStringToXMLDocument(dataload);

        Element element = doc.getDocumentElement();
        StringBuilder stringBuilder = new StringBuilder("\n\n        Dataload Generated Automatically\n\n");
        for (Map.Entry<String, String> entry : commentsMap.entrySet()) {
            stringBuilder.append("        ").append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }

        Comment comment = doc.createComment(stringBuilder.toString());
        element.getParentNode().insertBefore(comment, element);

        return refactorXml(doc);
    }

    public static String refactorXml(Document xml) throws TransformerException {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));

        return out.toString();
    }


    private static Document convertStringToXMLDocument(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlString));

        return db.parse(is);
    }

    /**
     *
     * This method take a list and delete all the duplicated values present
     *
     * @param wholeList
     * @return
     */
    public static List<String> removeDuplicatedValueFromListOfString(List<String> wholeList) {
        return wholeList.stream().distinct().collect(Collectors.toList());
    }

    /**
     *
     * It could be possible that stateIndexes coming out replicated. It is not an error, because you have just
     * duplicated and redundant noProblemDetected. But it gonna be a problem when we gonna deploy the output dataload
     *
     * This method give back a same list gave as input but without duplicated items
     *
     * @param wholeList
     * @return
     */
    public static List<GroupType.Indexes.Index> removeDuplicatesFromListOfIndexType(List<GroupType.Indexes.Index> wholeList) {
        return wholeList.stream().distinct().collect(Collectors.toList());
    }

    /**
     *
     * this method create an empty array with defined size. It need to be populated with empty string because in the end the
     * "state item" section is going to be an emptyTag section into dataload file
     *
     * @param size
     * @return
     */
    public static String[] buildEmptyArrayWithDefinedSize(int size) {
        String[] currentArrayOfString = new String[size];
        for(int i = 0; i < currentArrayOfString.length; i++){
            currentArrayOfString[i] = EMPTY_STRING;
        }
        return currentArrayOfString;
    }

    /**
     *
     * Check input integrity
     * No empty or null input are admitted
     *
     * The file name must match the extension condition gave in input (regexPatternExtension)
     *
     * @param filePath
     * @param regexPatternExtension
     * @throws DataloadGeneratorException
     */
    public static void checkFileNameFormatIntegrity(String filePath, String regexPatternExtension) throws DataloadGeneratorException {

        if(EMPTY_STRING.equalsIgnoreCase(filePath) || filePath == null){
            logger.severe("ERROR - The path file is empty or null");
            throw new DataloadGeneratorException("ERROR - The path file is empty or null");

        }else {
            Pattern pattern = Pattern.compile(regexPatternExtension);
            if(!pattern.matcher(filePath).matches()){
                logger.severe("ERROR - Wrong file extension. Check file [" + filePath +"]");
                throw new DataloadGeneratorException("ERROR - Wrong file extension. Check file [" + filePath +"]");
            }

        }
    }

    /**
     * This map keep track about the order of the state indexes.
     * When we gonna populate the dataload, we need to know in which position the indexes are
     *
     * @return
     */
    public static Map<String, Integer> getIndexMap(List<String> stateItemList) throws DataloadGeneratorException {
        if(stateItemList.size() == 0){
            throw new DataloadGeneratorException("Error - Cannot proceed with the generation of dataload. State Item list is empty");
        }
        Map<String, Integer> indexMap = new HashMap<>();

        for(int i = 0; i < stateItemList.size(); i++){
            indexMap.put(stateItemList.get(i), i);
        }
        return indexMap;
    }

    /**
     * hashSetNextGroup contains the list of the 'next group name' for all the dataload's groups.
     *
     * The name of the group is repeated only one time inside the list
     *
     * @return
     */
    public static Set<String> getHashSetNextGroup() {
        return hashSetNextGroup;
    }

    public static Set<Checkpoint> getCheckpointSet(){
        return checkpointSet;
    }

    /**

     * This method check if a inpunt string contains any special character.
     * If any special characters are found the method throws an DataloadException to inform the user to fix the value
     *
     */
    public static void validateStringValueBySpecialCharPresence(String inspectedObject, String regexPattern) throws DataloadGeneratorException {
        Pattern pattern = Pattern.compile(regexPattern);
        if(pattern.matcher(inspectedObject).find()){
            logger.severe("ERROR - The following value : [" + inspectedObject+ "'] contains special character. Remove and try again");
            throw new DataloadGeneratorException("ERROR - The following value : [" + inspectedObject+ "'] contains special character. Remove and try again");
        }
    }

    /**
     * this method give back the path where save the dataload generated
     *
     * In case we are working into the local machine, he takes the path of the intermediate file that we used
     * If we are working on linux, test environment, the generated dataload is going to be placed under the path .../data, from where we can start the deploy
     *
     *
     * @param xmlFilePath
     * @param solutionName
     * @return
     * @throws DataloadGeneratorException
     */
    public static String getPathFromIntermediateFile(String xmlFilePath, String solutionName) throws DataloadGeneratorException {
        Path p = Paths.get(xmlFilePath);
        Path folder = p.getParent();
        String fullPath;

        if(System.getProperty(OS_NAME).toLowerCase().contains(WINDOWS_OS)) {
            fullPath = folder + "\\";
        }else if(System.getProperty(OS_NAME).toLowerCase().contains(LINUX_OS)){
            fullPath = "/opt/OV/ServiceActivator/solutions/" + solutionName + "/etc/data/";
        }else{
            logger.severe("Error - Impossible to manage process for the current OS: " + System.getProperty(OS_NAME) + "\n");
            throw new DataloadGeneratorException("Error - Impossible to manage process for the current OS: " + System.getProperty(OS_NAME) + "\n");
        }
        if(Files.notExists(Paths.get(fullPath))) {
            logger.severe("Error - Path or File doesn't exist. Impossible to save the dataload file \n\n       - " + fullPath);
            throw new DataloadGeneratorException("Error - Path or File doesn't exist. Impossible to save the dataload file \n\n       - " + fullPath);
        }
        return fullPath;
    }

    public static void validateItemTypeObject(ItemType currentItemFromDataSet) throws DataloadGeneratorException {
        Iterator<BlockExecutionFlowType> iterator = currentItemFromDataSet.getBlocksExecutionGroup().getBlockExecutionFlow().iterator();
        List<String> currentBlockNameList = new ArrayList<>();
        while(iterator.hasNext()){
            BlockExecutionFlowType currentBlockExFlow = iterator.next();
            if(currentBlockExFlow.getBlockName() != null){
                validateStringValueBySpecialCharPresence(currentBlockExFlow.getBlockName(), SPECIAL_CHAR_EX);
                currentBlockNameList.add(currentBlockExFlow.getBlockName());
            }
            if(currentBlockExFlow.getMatchCond() != null)
                validateStringValueBySpecialCharPresence(currentBlockExFlow.getMatchCond(), SPECIAL_CHAR_EX);
        }
    }

    /**
     * this method validate the DataSetItem Object taken as input
     *
     *  - All the main sections (pattern_filter, blocks_execution_group,pattern_output) must be NOT null
     *  - Cannot be present null or empty field into the pattern_filter section
     *  - Cannot be present null or empty field into the patter_output section
     *  - The pattern_exectution_group must have al least one block_execution_element valorized
     *  - The pattern_exectution_group must have one and only one EXIT block
     *  - If the 'block_execution_flow' describe a process block (ECIT block EXCLUDED), it cannot have a null or empty 'match_cond' field
     *  - EXIT block must have 'match_cond' field null or empty. If not the field is ignored anyway
     *
     *
     * @param dateSetType
     * @throws DataloadGeneratorException
     */
    public static void validateDataSetTypeObj(DataSetItem dateSetType) throws DataloadGeneratorException {
        int exitBlockCounter;
        int processBlockCounter;

        if(dateSetType != null) {

            if(!dateSetType.getItem().isEmpty() && dateSetType.getItem().size() != 0){

                Iterator<ItemType> iterator = dateSetType.getItem().iterator();
                while (iterator.hasNext()){
                    ItemType currentItemType = iterator.next();
                    if(currentItemType.getPatternFilter() == null){
                        throw new DataloadGeneratorException("Error - The current ItemType object, generated from the intermediate file, has 'PatternFilter' section [NULL]. Check the input structure." + currentItemType);
                    }else{
                        if(currentItemType.getPatternFilter().getTechnology() == null ||
                                EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternFilter().getTechnology())){
                            logger.severe("Error - Current pattern filter has empty or null 'technology' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                            throw new DataloadGeneratorException("Error - Current pattern filter has empty or null 'technology' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                        }
                        if(currentItemType.getPatternFilter().getClassValue() == null ||
                                EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternFilter().getClassValue())){
                            logger.severe("Error - Current pattern filter has empty or null 'ClassValue' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                            throw new DataloadGeneratorException("Error - Current pattern filter has empty or null 'ClassValue' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                        }
                        if(currentItemType.getPatternFilter().getIsUniqueCpe() == null ||
                                EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternFilter().getIsUniqueCpe())){
                            logger.severe("Error - Current pattern filter has empty or null 'isUniqueCpe' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                            throw new DataloadGeneratorException("Error - Current pattern filter has empty or null 'isUniqueCpe' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                        }
                        if(currentItemType.getPatternFilter().getProblemType() == null ||
                                EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternFilter().getProblemType())){
                            logger.severe("Error - Current pattern filter has empty or null 'ProblemType' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                            throw new DataloadGeneratorException("Error - Current pattern filter has empty or null 'ProblemType' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                        }
                        if(currentItemType.getPatternFilter().getRgType() == null ||
                                EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternFilter().getRgType())){
                            logger.severe("Error - Current pattern filter has empty or null 'RgType' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                            throw new DataloadGeneratorException("Error - Current pattern filter has empty or null 'RgType' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                        }
                        if(currentItemType.getPatternFilter().getSegment() == null ||
                                EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternFilter().getSegment())){
                            logger.severe("Error - Current pattern filter has empty or null 'Segment' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                            throw new DataloadGeneratorException("Error - Current pattern filter has empty or null 'Segment' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                        }
                        if(currentItemType.getPatternFilter().getService() == null ||
                                EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternFilter().getService())){
                            logger.severe("Error - Current pattern filter has empty or null 'Service' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                            throw new DataloadGeneratorException("Error - Current pattern filter has empty or null 'Service' field. Impossible to generate dataload. " + currentItemType.getPatternFilter());
                        }

                    }
                    if(currentItemType.getBlocksExecutionGroup() == null ){
                        logger.severe("Error - The current ItemType object, generated from the intermediate file, has 'BlockExecutionGroup' section [NULL]. Check the input structure -"  + currentItemType);
                        throw new DataloadGeneratorException("Error - The current ItemType object, generated from the intermediate file, has 'BlockExecutionGroup' section [NULL]. Check the input structure -"  + currentItemType);
                    }else if(currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow() == null || currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow().isEmpty()){
                        logger.severe("Error - The current ItemType object, generated from the intermediate file, has 'BlockExecutionFlow' List [NULL] or [EMPTY]. Check the input structure - " + currentItemType);
                        throw new DataloadGeneratorException("Error - The current ItemType object, generated from the intermediate file, has 'BlockExecutionFlow' List [NULL] or [EMPTY]. Check the input structure - " + currentItemType);

                    }else {

                        Iterator<BlockExecutionFlowType> blockExecFlowTypeIterator = currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow().iterator();
                        exitBlockCounter = 0;
                        processBlockCounter = 0;
                        while(blockExecFlowTypeIterator.hasNext()) {
                            BlockExecutionFlowType currBlockExecFlowType = blockExecFlowTypeIterator.next();

                            if(currBlockExecFlowType.getBlockName() == null ||
                                    EMPTY_STRING.equalsIgnoreCase(currBlockExecFlowType.getBlockName())){
                                logger.severe("Error - Impossible to generate dataload. Current 'block execution' section has empty or null 'BlockName' field. Fix the problem and try again. Wrong Section ===> [" + currBlockExecFlowType + "]");
                                throw new DataloadGeneratorException("Error - Impossible to generate dataload. Current 'block execution' section has empty or null 'BlockName' field. Fix the problem and try again. Wrong Section ===> [" + currBlockExecFlowType + "]");
                            }else{
                                if(!DataloadGenUtils.EXIT.equalsIgnoreCase(currBlockExecFlowType.getBlockName())){
                                    if(currBlockExecFlowType.getBlockName().toLowerCase().contains(NEXT_GROUP_PLACEHOLDER.toLowerCase())) {
                                        if(!"ELSE".equalsIgnoreCase(currBlockExecFlowType.getMatchCond())){
                                            hashSetNextGroup.add(currBlockExecFlowType.getMatchCond());
                                        }
                                    }
                                    processBlockCounter ++;
                                    if(currBlockExecFlowType.getMatchCond() == null ||
                                            EMPTY_STRING.equalsIgnoreCase(currBlockExecFlowType.getMatchCond())){
                                        logger.severe("Error - Impossible to generate dataload. Current 'block execution' section has empty or null 'MatchCond' field. Fix the problem and try again. Wrong Section ===> [" + currBlockExecFlowType + "]");
                                        throw new DataloadGeneratorException("Error - Impossible to generate dataload. Current 'block execution' section has empty or null 'MatchCond' field. Fix the problem and try again. Wrong Section ===> [" + currBlockExecFlowType + "]");
                                    }
                                }else{
                                    exitBlockCounter ++;
                                }
                            }
                        }
                        switch (String.valueOf(exitBlockCounter)) {
                            case "0":
                                logger.severe(("Error - There are no EXIT block configured. Fix the problem and try again. Wrong Section ===> " + currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow()));
                                throw new DataloadGeneratorException("Error - There are no EXIT block configured. Fix the problem and try again. Wrong Section ===> " + currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow());
                            case "1":
                                break;
                            default:
                                logger.severe("Error - There are more than 1 EXIT block configured. This block must be present ONLY ONE TIME into 'blocks_execution_group' section. Fix the problem and try again. Wrong Section ===> " + currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow());
                                throw new DataloadGeneratorException("Error - There are more than 1 EXIT block configured. This block must be present ONLY ONE TIME into 'blocks_execution_group' section. Fix the problem and try again. Wrong Section ===> " + currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow());
                        }

                        if(processBlockCounter == 0){
                            logger.severe(("Error - There are no process block configured. Fix the problem and try again. Wrong Section ===> " + currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow()));
                            throw new DataloadGeneratorException("Error - There are no process block configured. Fix the problem and try again. Wrong Section ===> " + currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow());
                        }

                        if(!EXIT.equalsIgnoreCase(currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow().get(currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow().size() - 1).getBlockName())) {
                            logger.severe(("Error - Exit block is not configured as the last block of execution. Every single flow must finish with EXIT block. Fix the problem and try again. Wrong Section ===> " + currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow()));
                            throw new DataloadGeneratorException("Error - Exit block is not configured as the last block of execution. Every single flow must finish with EXIT block. Wrong Section ===> " + currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow());
                        }
                    }

                    if(currentItemType.getPatternOutput() == null){
                        logger.severe("Error - The current ItemType object, generated from the intermediate file, has 'PatternOutput' section [NULL]. Check the input structure - " + currentItemType);
                        throw new DataloadGeneratorException("Error - The current ItemType object, generated from the intermediate file, has 'PatternOutput' section [NULL]. Check the input structure - " + currentItemType);
                    }else{
                        if(EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternOutput().getProblemNotSolved()) ||
                                currentItemType.getPatternOutput().getProblemNotSolved() == null){
                            logger.severe("Error - Impossible to generate dataload. 'problemNotSolved' field is not valorized, fix the error and try again. ===> Wrong section: [" + currentItemType.getPatternOutput() +"]");
                            throw new DataloadGeneratorException("Error - Impossible to generate dataload. 'problemNotSolved' field is not valorized, fix the error and try again. ===> Wrong section: [" + currentItemType.getPatternOutput() +"]");
                        }
                        if(EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternOutput().getInterventionRequired()) ||
                                currentItemType.getPatternOutput().getInterventionRequired() == null){
                            logger.severe("Error - Impossible to generate dataload. 'interventionRequired' field is not valorized, fix the error and try again. ===> Wrong section: [" + currentItemType.getPatternOutput() +"]");
                            throw new DataloadGeneratorException("Error - Impossible to generate dataload. 'interventionRequired' field is not valorized, fix the error and try again. ===> Wrong section: [" + currentItemType.getPatternOutput() +"]");
                        }
                        if(EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternOutput().getMassiveDisservice()) ||
                                currentItemType.getPatternOutput().getMassiveDisservice() == null){
                            logger.severe("Error - Impossible to generate dataload. 'massiveDisservice' field is not valorized, fix the error and try again. ===> Wrong section: [" + currentItemType.getPatternOutput() +"]");
                            throw new DataloadGeneratorException("Error - Impossible to generate dataload. 'massiveDisservice' field is not valorized, fix the error and try again. ===> Wrong section: [" + currentItemType.getPatternOutput() +"]");
                        }
                        if(EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternOutput().getNoProblemDetected()) ||
                                currentItemType.getPatternOutput().getNoProblemDetected() == null) {
                            logger.severe("Error - Impossible to generate dataload. 'noProblemDetected' field is not valorized, fix the error and try again. ===> Wrong section: [" + currentItemType.getPatternOutput() +"]");
                            throw new DataloadGeneratorException("Error - Impossible to generate dataload. 'noProblemDetected' field is not valorized, fix the error and try again. ===> Wrong section: [" + currentItemType.getPatternOutput() +"]");
                        }
                        if(EMPTY_STRING.equalsIgnoreCase(currentItemType.getPatternOutput().getProblemSolved()) ||
                                currentItemType.getPatternOutput().getProblemSolved() == null){
                            logger.severe("Error - Impossible to generate dataload. 'problemSolved' field is not valorized, fix the error and try again. ===> Wrong section: [" + currentItemType.getPatternOutput() +"]");
                            throw new DataloadGeneratorException("Error - Impossible to generate dataload. 'problemSolved' field is not valorized, fix the error and try again. ===> Wrong section: [" + currentItemType.getPatternOutput() +"]");
                        }
                    }
                }
            }else{
                logger.severe("Error - The current ItemType object, generated from the intermediate file, is [EMPTY]. Check the input structure.");
                throw new DataloadGeneratorException("Error - The current ItemType object, generated from the intermediate file, is [EMPTY]. Check the input structure.");
            }
        } else {
            logger.severe("Error - The current ItemType object, generated from the intermediate file, is [NULL]. Check the input structure.");
            throw new DataloadGeneratorException("Error - The current ItemType object, generated from the intermediate file, is [NULL]. Check the input structure.");
        }
    }


    public static String validateOutputPath(String outputPath) throws DataloadGeneratorException {
        if(outputPath == null){
            logger.severe("Error - The 'output path' is null");
            throw new DataloadGeneratorException("Error - The 'output path' file is null");
        }
        if(EMPTY_STRING.equalsIgnoreCase(outputPath) || outputPath.isEmpty()){
            logger.severe("Error - The 'output path' is empty");
            throw new DataloadGeneratorException("Error - The 'output path' is empty");
        }
        Path path = Paths.get(outputPath);

        if(Files.notExists(path)){
            logger.severe("Error - The output path [ "+ outputPath +" ] gave in input doesn't exist. Process will be stopped due impossibility to store the resulting dataload.");
            throw new DataloadGeneratorException("Error - The output path [ " + outputPath + " ] gave in input doesn't exist. Process will be stopped due impossibility to store the resulting dataload");
        }else {
            if(System.getProperty(OS_NAME).toLowerCase().contains(WINDOWS_OS)) {
                if(path.endsWith("\\")){
                    return outputPath;
                }else{
                    return outputPath + "\\";
                }
            }else if(System.getProperty(OS_NAME).toLowerCase().contains(LINUX_OS)){
                if(path.endsWith("/")){
                    return outputPath;
                }else{
                    return outputPath + "/";
                }
            }else{
                logger.severe("Error - Thw tool is currently running on a Operating System which is not supported. Move to Windows or linux environment");
                throw new DataloadGeneratorException("Error - Thw tool is currently running on a Operating System which is not supported. Move to Windows or linux environment");
            }

        }
    }

    public static void validateSolutionName(String solutionName) throws DataloadGeneratorException{
        if(solutionName == null || EMPTY_STRING.equalsIgnoreCase(solutionName)){
            logger.severe("Error - Cannot proceed with the dataload creation due missing solution name paramter");
            throw new DataloadGeneratorException("Error - Cannot proceed with the dataload creation due missing solution name paramter");
        }

    }

    public static void validateXmlFile(String xmlFile) throws DataloadGeneratorException {
        if(xmlFile == null){
            logger.severe("Error - The intermediate file is null");
            throw new DataloadGeneratorException("Error - The intermediate file is null");
        }
        if(EMPTY_STRING.equalsIgnoreCase(xmlFile) || xmlFile.isEmpty()){
            logger.severe("Error - The intermediate file is empty");
            throw new DataloadGeneratorException("Error - The intermediate file is empty");
        }
    }

    public static String buildStringXmlFromActionType(ActionType actionType) throws DataloadGeneratorException {
        try {
            JAXBContext context = JAXBContext.newInstance(ActionType.class);
            JAXBElement<ActionType> jaxbElement =
                    new JAXBElement<>( new QName("", "action"),
                            ActionType.class,
                            actionType);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
//            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            m.marshal(jaxbElement, sw);
            return sw.toString();
        } catch (PropertyException e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        } catch (JAXBException e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        }
    }

    public static String buildStringXmlFromWorkflowTemplate(WorkflowTemplateType templateType) throws DataloadGeneratorException {
        try {
            JAXBContext context = JAXBContext.newInstance(WorkflowTemplateType.class);
            JAXBElement<WorkflowTemplateType> jaxbElement =
                    new JAXBElement<>( new QName("", "workflowTemplateType"),
                            WorkflowTemplateType.class,
                            templateType);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            m.marshal(jaxbElement, sw);
            return sw.toString();
        } catch (PropertyException e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        } catch (JAXBException e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        }
    }

    public static String buildStringXmlFromHelpTextTemplate(HelpTextType helpTextType) throws DataloadGeneratorException {
        try {
            JAXBContext context = JAXBContext.newInstance(HelpTextType.class);
            JAXBElement<HelpTextType> jaxbElement =
                    new JAXBElement<>( new QName("", "helpText"),
                            HelpTextType.class,
                            helpTextType);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            m.marshal(jaxbElement, sw);
            return sw.toString();
        } catch (PropertyException e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        } catch (JAXBException e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        }
    }


    public static ActionType buildActionTypeObjectFromXmlString(String actionTypeXml) throws DataloadGeneratorException {
        validateInputStringXml(actionTypeXml);
        System.out.println("Converting string xml to ActionType Object. [ " + actionTypeXml + "]");
        try {
            JAXBContext jc = JAXBContext.newInstance(ActionType.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(actionTypeXml));
            JAXBElement<ActionType> je = unmarshaller.unmarshal(streamSource, ActionType.class);
            return je.getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Input is not parseable as ActionType object.");
        }
    }

    public static WorkflowTemplateType buildWorkflowTypeObjectFromXmlString(String workflowXml) throws DataloadGeneratorException {
        validateInputStringXml(workflowXml);
        System.out.println("Converting string xml to Workflow Template Type Object. [" + workflowXml + "]");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WorkflowTemplateType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (WorkflowTemplateType) jaxbUnmarshaller.unmarshal(new StringReader(workflowXml));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Input is not parseable WorkflowTemplateType object");
        }
    }

    public static HelpTextType buildHelpTextTypeTypeObjectFromXmlString(String helptextXml) throws DataloadGeneratorException {
        validateInputStringXml(helptextXml);
        System.out.println("Converting string xml to Helptext Template Type Object. [" + helptextXml + "]");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(HelpTextType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (HelpTextType) jaxbUnmarshaller.unmarshal(new StringReader(helptextXml));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Input is not parseable HelpText Type object");
        }
    }

    private static void validateInputStringXml(String input) throws DataloadGeneratorException {
        if(input == null || EMPTY_STRING.equalsIgnoreCase(input)){
            throw new DataloadGeneratorException("Error - Input list is empty or NULL");
        }
    }

    public static String buildStringXmlFromGroupType(GroupType groupType) throws DataloadGeneratorException {
        try {
            JAXBContext context = JAXBContext.newInstance(GroupType.class);
            JAXBElement<GroupType> jaxbElement =
                    new JAXBElement<>( new QName("", "groupType"),
                            GroupType.class,
                            groupType);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
            StringWriter sw = new StringWriter();
            m.marshal(jaxbElement, sw);
            return sw.toString();
        } catch (PropertyException e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        } catch (JAXBException e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        }
    }

    public static String groupTypeObjectToString(GroupType groupType) throws DataloadGeneratorException {
        try {
            JAXBContext context = JAXBContext.newInstance(GroupType.class);
            JAXBElement<GroupType> jaxbElement =
                    new JAXBElement<>( new QName("", "group"),
                            GroupType.class,
                            groupType);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
//            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            m.marshal(jaxbElement, sw);
            return sw.toString();
        } catch (PropertyException e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        } catch (JAXBException e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw new DataloadGeneratorException(e);
        }
    }

    public static TD.Actions xmlStringToTDActions(String xmlActions) throws DataloadGeneratorException {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(TD.Actions.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (TD.Actions) jaxbUnmarshaller.unmarshal(new StringReader(xmlActions));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Wrong input file structure." + e);
        }
    }


    public static WorkflowTemplateType getWorkflowStructureByActionWfAndSolution(String workflowName, String actionName, String solutionName) {
        WorkflowTemplateType workflowTemplateType = new WorkflowTemplateType();
        workflowTemplateType.setActionName(actionName);
        workflowTemplateType.setWorkflow(workflowName);
        workflowTemplateType.setActionSolution(solutionName);
        workflowTemplateType.setServiceType("any");
        return workflowTemplateType;
    }

    public static ActionType buildActionTypeDriveToLowerOrderGroupByFullStateVectorList(String actionName,
                                                                                        List<String> fullStateVectorList) throws DataloadGeneratorException {
        if(fullStateVectorList != null
                && !fullStateVectorList.isEmpty()){
            ActionType actionType = new ActionType();
            actionType.setName(actionName);
            actionType.setLabel("Driver - " + actionName);
            actionType.setDescription("!!Driver!Driver");

            List<ActionType.OutputParameters.OutputParameter> outputParameterList = new ArrayList<>();
            for(String currentStateVectoreName : fullStateVectorList){
                ActionType.OutputParameters.OutputParameter outputParameter = new ActionType.OutputParameters.OutputParameter();
                outputParameter.setName(currentStateVectoreName);
                outputParameter.setType("String");
                outputParameter.setLabel("!!result!");
                outputParameter.setEditable(false);
                outputParameter.setHidden(true);
                outputParameter.setIncludeInStateVector(true);
                outputParameter.setStateVectorName(currentStateVectoreName);
                outputParameterList.add(outputParameter);
            }
            ActionType.OutputParameters outputParameters = new ActionType.OutputParameters();
            outputParameters.getOutputParameter().addAll(outputParameterList);
            actionType.setOutputParameters(outputParameters);
            return actionType;
        }
        throw new DataloadGeneratorException("Error - Cannot build Action Structure for [" + actionName + "]. Unable to populate output parameters due vector list in use is empty or null.");
    }

    public static List<String> getFullStateVectorList(TD dataload) {
        List<String> stateVectoreList = new ArrayList<>();

        for(GroupType groupType : dataload.getGroups().getGroup()){
            if(!"0".equalsIgnoreCase(String.valueOf(groupType.getOrder()))){
                stateVectoreList.addAll(groupType.getStates().getName());
            }
        }
        return stateVectoreList;
    }
    public static void printOutputInfo(String fullPath, String solutionName, String version, String versionSA, String operator, String description){


        logger.info("\n\n        Well done!!! \n");
        logger.info("        Dataload saved as:                 " + fullPath);
        logger.info("        Solution Name:                     " + solutionName);
        logger.info("        TED Environment Version:           " + versionSA);
        logger.info("        Dataload Version:                  " + version);
        logger.info("        Description:                       " + description);
        logger.info("        Operator:                          " + operator);
        logger.info("        Path builded for current os:       " + System.getProperty("os.name") +"\n");
        logger.info("        " + new Date());
        logger.info("\n\n                                                                                                                                           -- AmatiCorp. --\n\n   -----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static List<ActionDetails> getActionListDetails(String path) throws DataloadGeneratorException {
        try {
            return mapper.readValue(new File(path), List.class);
        } catch (IOException e) {
            throw new DataloadGeneratorException("Error - Cannot retrieve Action Details." + e.getMessage());
        }
    }
    public static List<ActionDetails> getActionDetailList(String path) throws DataloadGeneratorException {
        try {
            List list = mapper.readValue(new File(path), List.class);
            List<ActionDetails> actionDetailsList = new ArrayList<>();
            for(Object actionDetails : list){
                actionDetailsList.add(buildActionList(actionDetails));
            }
            return actionDetailsList;
        } catch (IOException e) {
            throw new DataloadGeneratorException("Error - Cannot retrieve Action Details." + e.getMessage());
        }
    }

    private static ActionDetails buildActionList(Object actionDetails) {
        LinkedHashMap linkedHashMap = (LinkedHashMap) actionDetails;

        return new ActionDetails(
                (String)linkedHashMap.get("name"),
                (boolean) linkedHashMap.get("proceed"),
                (boolean) linkedHashMap.get("reccomended"),
                (boolean) linkedHashMap.get("possible"),
                (boolean) linkedHashMap.get("excluded"));
    }

    //TODO quando ci sarà la possibilità di lavorare su un dataload già creato sarà possibile rimuovere un gruppo tramite questo metodo
    /**
     *
     * This method remove a group and all his actionDefinitions and workflows associated with the actions
     *
     * @param path
     * @param groupNameToRemove
     * @return
     */
    public static TD removeGroupFromTD(String path, String groupNameToRemove) throws DataloadGeneratorException {
        TD td_refactored = getDataloadFromPath(path);
        List<GroupType> toRemove = new ArrayList<>();
        for(GroupType groupType : td_refactored.getGroups().getGroup()){
            if(groupNameToRemove.equalsIgnoreCase(groupType.getName())){
                toRemove.add(groupType);
            }
        }
        td_refactored.getActions().getAction().removeAll(getActionListToRemove(toRemove,td_refactored));
        td_refactored.getGroups().getGroup().removeAll(toRemove);
        return td_refactored;
    }

    public static Collection<?> getActionListToRemove(List<GroupType> toRemove, TD td_refactored) {

        List<ActionType> actionToRemove = new ArrayList<>();
        for(GroupType groupType : toRemove){
            for(ActionType actionType : td_refactored.getActions().getAction()){
                if(groupType.getStates().getName().contains(actionType.getName())){
                    actionToRemove.add(actionType);
                }

            }
        }
        return actionToRemove;
    }

    public static TD getDataloadFromPath(String path) throws DataloadGeneratorException {
        System.out.println("Retrieving dataload object from path [" + path + "]");
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(TD.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (TD) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {

            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot retrieve dataload from path [" + path + "]. " + e);
        }
    }

    public static TD buildTDObjectFromStringXml(String dataloadStringXml) throws DataloadGeneratorException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TD.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (TD) jaxbUnmarshaller.unmarshal(new StringReader(dataloadStringXml));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot generate TD object instance from xml string. " + e);
        }
    }



    public static ConfigurationClass getConfigurationFile() throws DataloadGeneratorException {
        if(System.getProperty(OS_NAME).toLowerCase().contains(WINDOWS_OS)) {
            return new ConfigurationClassWindows();
        }else if(System.getProperty(OS_NAME).toLowerCase().contains(LINUX_OS)){
            return new ConfigurationClassLinux();
        }else{
            logger.severe("Error - Thw tool is currently running on a Operating System which is not supported. Move to Windows or linux environment");
            throw new DataloadGeneratorException("Error - Thw tool is currently running on a Operating System which is not supported. Move to Windows or linux environment");
        }
    }

    public static String getStringXmlFromGroupTypeObject(GroupType groupType) throws DataloadGeneratorException {
        System.out.println("Retrieving group type object from xml string - Group Name [" + groupType.getName() + "] + Group Order [" + groupType.getOrder() + "]");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GroupType.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(groupType, sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot retrieve group type to String Xml [" + groupType.getName() + "]. " + e);
        }
    }
    public static GroupType getGroupTypeObjectFromXmlString(String groupTypeStringXml) throws DataloadGeneratorException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GroupType.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(groupTypeStringXml);
            return (GroupType) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Unable to convert string xml to GroupType Object. " + e);
        }
    }

    public static void checkIfActionTypeListContainsDuplicatedActions(List<ActionType> actionTypesList) throws DataloadGeneratorException {
        List<String> strings = new ArrayList<>();
        for(ActionType actionType : actionTypesList){
            if(strings.contains(actionType.getName())){
                throw new DataloadGeneratorException("Error - Impossible to import actions list. Found duplicated configuration for action [" + actionType.getName() + "]");
            }
            strings.add(actionType.getName());
        }
    }

    public static List<String> retrieveActionListFromGroupsObject(TD.Groups groups) {
        List<String> actionsNameList = new ArrayList<>();
        if(groups.getGroup() != null){
            for(GroupType groupType : groups.getGroup()){
                if(groupType.getIndexes() != null){
                    if(groupType.getIndexes().getIndex() != null){
                        for(GroupType.Indexes.Index index : groupType.getIndexes().getIndex()){
                            if(index.getActions() != null){
                                if(index.getActions().getRecommended() != null){
                                    for(ActionRefType recommended : index.getActions().getRecommended().getAction()){
                                        actionsNameList.add(recommended.getName());
                                    }
                                }
                                if(index.getActions().getPossible() != null){
                                    for(ActionRefType possible : index.getActions().getPossible().getAction()){
                                        actionsNameList.add(possible.getName());
                                    }
                                }
                                if(index.getActions().getExcluded() != null){
                                    for(ActionRefType excluded : index.getActions().getExcluded().getAction()){
                                        actionsNameList.add(excluded.getName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return actionsNameList;
    }

}
