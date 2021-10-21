package com.hpe.dataload.generator.engine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.intergration.DataloadGeneratorResult;
import com.hpe.dataload.generator.model.dataloadmodel.*;
import com.hpe.dataload.generator.model.intfilemodel.*;
import com.hpe.dataload.generator.orm.TbsOrmBuilder;
import com.hpe.dataload.generator.orm.model.ActionDetails;
import com.hpe.dataload.generator.utils.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;

import static com.hpe.dataload.generator.utils.DataloadGenUtils.*;
import static com.hpe.dataload.generator.utils.WorkflowTemplateUtils.buildWorkflowToDriveProcessThroughtGroups;
import static com.hpe.dataload.generator.utils.WorkflowTemplateUtils.saveWfDriveGroupAsXmlFile;
import static com.hpe.dataload.generator.utils.XmlSourceReader.*;

/**
 *  This class contains the main method to do conversion
 *  from xml file or xml file path it gonna obtain the final data load
 *
 *  @author Angelo Amati
 */

@SuppressWarnings("Duplicates")
public class Builder {

    private static final Logger logger = Logger.getLogger(Builder.class.getName());

    private static final int GROUP_ORDER_0                  = 0;
    private static final String TECHNOLOGY                  = "TECHNOLOGY";
    private static final String SERVICE                     = "SERVICE";
    private static final String IS_UNIQUE_CPE               = "ISUNIQUECPE";

    private static final String PATTERN_NUMBER              = "PATTERNNUMBER";
    private static final String PROBLEM_TYPE                = "PROBLEMTYPE";
    private static final String RG_TYPE                     = "RGTYPE";
    private static final String STAR_CHAR                   = "*";

    private static final String HPETD_BUNDLE                = "HPETD_Bundle";
    private static final String CLASS_VALUE                 = "CLASSVALUE";

    private static final String SEGMENT                     = "SEGMENT";
    private static final String ELSE                        = "ELSE";
    private static final String ANY                         = "any";

    private static HashSet<String> actionsName;
    private static Collection<? extends String> fieldsAsStringList;
    private static Map<String, String> duplicatedBlockNameMap;

    public static ArrayList<String> proceedTrueActions;
    public static ArrayList<String> reccomendedActions;
    public static ArrayList<String> possibleActions;
    public static ArrayList<String> excludedActions;

    private static boolean retrieveStructureFromExternalFile        = false;
    private static List<Checkpoint> checkpoints = new ArrayList<>();
    private static List<NextGroupReminder> nexgGroupReminderList = new ArrayList<>();
    /**
     * This method is called by test classes
     * It wants as input the filePath of the xmlIntermetiate files, located into the same machine
     *
     * The process saves the dataload as xml file into the same directory of the intermetiate file
     *
     * @param xmlFilePath
     * @param generateActionStructure
     * @param generateWorkflowsStructure
     * @param versionTED
     * @param solutionName
     * @param description
     * @param operatorName
     * @param dataloadVersion
     * @throws DataloadGeneratorException
     */
    public static void generateDataloadFromXmlPath(String xmlFilePath,
                                                   boolean generateActionStructure,
                                                   boolean generateWorkflowsStructure,
                                                   String versionTED,
                                                   String solutionName,
                                                   String description,
                                                   String operatorName,
                                                   String dataloadVersion) throws DataloadGeneratorException {

        logger.info("\n   ----------------------------------------------------------------------------------------------------------------------------------------------------------------------- \n\n    DATALOAD GENERATOR \n\n    Start Building dataload for the current intermediate file path : " + xmlFilePath + "\n\n");
        DataSetItem dataSetItemObjectFromExt = getDataSetTypeObjectFromPath(xmlFilePath);
        DataSetItem dataSetItemObject = manageDuplicatedBlocknameIntoSameItem(dataSetItemObjectFromExt);

        TD td = buildDataload(dataSetItemObject,
                generateActionStructure,
                generateWorkflowsStructure,
                versionTED,
                solutionName,
                dataloadVersion,
                null,
                null,
                null);

        String pathFromIntermediateFile = getPathFromIntermediateFile(xmlFilePath, solutionName);
        DataloadGenUtils.saveResultIntoExternalFile(
                td,
                pathFromIntermediateFile,
                solutionName,
                dataloadVersion,
                versionTED,
                description,
                operatorName,
                generateActionStructure,
                generateWorkflowsStructure,
                true);
    }

    /**
     * This methood is the one called from the exteranl resorce. It perform the INTEGRATION with the external system
     *
     * NOTE: The method want as input the whole xmlIntermediate file as String
     *
     * The output of the process is represented by:
     *
     *      DataloadGeneratorResult:
     *
     *              - randomId (to recognize the single generation process)
     *              - dataload give back a whole dataload as xmlString
     *
     *      OutputFile:
     *
     *              - the process save into file system the dataload, as xml file, into the path taken as input 'outputPath'
     *
     *
     * @param xmlFile
     * @param generateActionStructure
     * @param generateWorkflowsStructure
     * @param versionTED
     * @param solutionName
     * @param description
     * @param operatorName
     * @param dataloadVersion
     * @param outputPath
     * @return
     * @throws DataloadGeneratorException
     */

    public static DataloadGeneratorResult generateDataloadFromXmlFile(String xmlFile,
                                                                      boolean generateActionStructure,
                                                                      boolean generateWorkflowsStructure,
                                                                      String versionTED,
                                                                      String solutionName,
                                                                      String description,
                                                                      String operatorName,
                                                                      String dataloadVersion,
                                                                      String outputPath) throws DataloadGeneratorException {


        logger.info("\n   ----------------------------------------------------------------------------------------------------------------------------------------------------------------------- \n\n    DATALOAD GENERATOR \n\n    " +
                "Start Building dataload for the current intermediate file :\n\n " + xmlFile + "\n\n");

        validateXmlFile(xmlFile);
        outputPath = validateOutputPath(outputPath);
        validateSolutionName(solutionName);
        checkDataloadVersionIntegrity(dataloadVersion);
        DataSetItem dataSetItemObjectFromExt = getDataSetTypeObjectFromString(xmlFile);
        DataSetItem dataSetItemObject = manageDuplicatedBlocknameIntoSameItem(dataSetItemObjectFromExt);

        TD td = buildDataload(dataSetItemObject,
                generateActionStructure,
                generateWorkflowsStructure,
                versionTED,
                solutionName,
                dataloadVersion,
                null,
                null,
                null);

        return DataloadGenUtils.saveResultIntoExternalFile(
                td,
                outputPath,
                solutionName,
                dataloadVersion,
                versionTED,
                description,
                operatorName,
                generateActionStructure,
                generateWorkflowsStructure,
                true);
    }

    /**
     *
     * The main method that Coordinate and perform the creation of datalod
     *
     *
     * @param dataSetItemObject
     * @param generateActionStructure
     * @param generateWorkflowsStructure
     * @param versionTED
     * @param solutionName
     * @param dataloadVersion
     * @param groupOrderMap
     * @return
     * @throws DataloadGeneratorException
     */
    public static TD buildDataload(DataSetItem dataSetItemObject,
                                   boolean generateActionStructure,
                                   boolean generateWorkflowsStructure,
                                   String versionTED,
                                   String solutionName,
                                   String dataloadVersion,
                                   String groupName,
                                   String groupOrder,
                                   Map<String, Integer> groupOrderMap) throws DataloadGeneratorException {

        try{
            buildDetailsActionLists(getConfigurationFile().getActionDetailsPathFile());
            actionsName = new HashSet<>();
            List<String> currentStateItem = getStateItemList(dataSetItemObject);
            Map<String, String> mapNextGroupActionValue = buildMapNextGroup(dataSetItemObject);

            currentStateItem.remove("EXIT");

            Map<String, Integer> indexesListOfStateItems = getIndexMap(currentStateItem);

            TD tdType = new TD();
            switch (versionTED){
                case EnumTedVersion.version_8_0:
                    break;
                case EnumTedVersion.version_8_1:
                    tdType.setVersion(new Integer(dataloadVersion));
                    break;
                default:
                    logger.severe("Error - Impossible to generate dataload. Unrecognized TD version : [" + versionTED + "]. Try to use one of the allowed version [" +EnumTedVersion.version_8_0 +" - " + EnumTedVersion.version_8_1 +"] and try again");
                    throw new DataloadGeneratorException("Error - Impossible to generate dataload. Unrecognized TD version : [" + versionTED + "]");
            }

            tdType.setSolution(solutionName);
            tdType.setDefaultBundle(HPETD_BUNDLE);

            TD.Groups groupsType = new TD.Groups();
            GroupType groupType = new GroupType();
            groupType.setOrder(GROUP_ORDER_0);
            groupType.setName(solutionName);

            TD.ServiceTypes serviceTypes = new TD.ServiceTypes();
            List<ServiceTypeType> serviceTypeTypes = new ArrayList<>();

            ServiceTypeType serviceTypeType = new ServiceTypeType();
            serviceTypeType.setType(ANY);
            serviceTypeTypes.add(serviceTypeType);
            serviceTypes.getServiceType().add(serviceTypeType);

            GroupType.States statesType = new GroupType.States();

            statesType.getName().addAll(currentStateItem);
            groupType.setStates(statesType);

            groupType.setIndexes(buildIndexesList(dataSetItemObject, indexesListOfStateItems));

            List<GroupType> groupTypesList = new ArrayList<>();
            groupTypesList.add(groupType);
            groupsType.getGroup().addAll(groupTypesList);

            tdType.setGroups(groupsType);
            tdType.setServiceTypes(serviceTypes);

            List<ActionType> actionTypeToAddList = new ArrayList<>();
            List<ActionType> actionTypeListFromRepo = TbsOrmBuilder.getTbsOrmImplementation().getActionTypeList();

            List<WorkflowTemplateType> workflowTemplateListFromRepo = TbsOrmBuilder.getTbsOrmImplementation().getWorkflowsTemplateList();
            for(WorkflowTemplateType workflowTemplateType : workflowTemplateListFromRepo){
                workflowTemplateType.setActionSolution(solutionName);
            }
            List<WorkflowTemplateType> workflowTemplateToAddList = new ArrayList<>();

            List<HelpTextType> helpTextTypeListFromRepo = TbsOrmBuilder.getTbsOrmImplementation().getListHelpText();
            List<HelpTextType> helpTextTypeListToAdd = new ArrayList<>();

            if(groupOrder != null && groupName != null){
                tdType = refactorNextGroupOccurrences(tdType,
                        solutionName,
                        mapNextGroupActionValue,
                        groupName,
                        groupOrder,
                        groupOrderMap);
            }

            Iterator<String> actionIterator = actionsName.iterator();
            while(actionIterator.hasNext()){
                String currentActionName = actionIterator.next();
                if(duplicatedBlockNameMap.containsKey(currentActionName)){
                    if(generateActionStructure) {
                        ActionType actionType = retrieveActionTypeBySavedNameFromSavedList(actionTypeListFromRepo, duplicatedBlockNameMap.get(currentActionName), currentActionName);
                        actionTypeToAddList.add(actionType);
                    }
                    if(generateWorkflowsStructure){
                        WorkflowTemplateType workflowTemplateType = retrieveWorkflowTemplateTypeBySavedActionNameFromWfRepoList(workflowTemplateListFromRepo, duplicatedBlockNameMap.get(currentActionName), currentActionName);
                        workflowTemplateToAddList.add(workflowTemplateType);
                    }
                }else{
                    ActionType actionType = retrieveActionTypeBySavedNameFromSavedList(actionTypeListFromRepo, currentActionName, currentActionName);
                    if(generateActionStructure) {
                        actionTypeToAddList.add(actionType);
                    }
                    if(generateWorkflowsStructure) {
                        workflowTemplateToAddList.add(retrieveWorkflowTemplateTypeBySavedActionNameFromWfRepoList(workflowTemplateListFromRepo, currentActionName, currentActionName));
                    }
                    List<HelpTextType> helpTextTypeList = retrieveHelpTextListByActionHelpTextDefinition(helpTextTypeListFromRepo, actionType);
                    if(helpTextTypeList != null &&
                            !helpTextTypeList.isEmpty()){
                        helpTextTypeListToAdd.addAll(helpTextTypeList);
                    }
                }
            }
            TD.Actions tdActionToAdd = new TD.Actions();
            tdActionToAdd.getAction().addAll(actionTypeToAddList);
            tdType.setActions(tdActionToAdd);
            TD.WorkflowTemplates workflowTemplates = new TD.WorkflowTemplates();
            workflowTemplates.getWorkflowTemplate().addAll(workflowTemplateToAddList);
            tdType.setWorkflowTemplates(workflowTemplates);
            TD.HelpTexts helpTexts = new TD.HelpTexts();
            helpTexts.getHelpText().addAll(helpTextTypeListToAdd);
            tdType.setHelpTexts(helpTexts);

            for(GroupType groupTypeToRefGroup : tdType.getGroups().getGroup()){
                groupTypeToRefGroup.setName(groupName);
                if(groupOrder != null){
                    if(groupOrderMap.containsKey(groupName)){
                        groupTypeToRefGroup.setOrder(groupOrderMap.get(groupName));
                    }else{
                        logger.warning("Setting group order '0' to group [" + groupName + "].");
                        groupTypeToRefGroup.setOrder(0);
                    }
                }
            }
            return  tdType;

        } catch (DataloadGeneratorException e){
            logger.severe("    Process is been interrupted due error detected.\n\n    #### " + e.getMessage() +" ####\n\n   ----------------------------------------------------------------------------------------------------------------------------------------------------------------------- ");
            throw new DataloadGeneratorException(e);
        }
    }

    private static Map<String, String> buildMapNextGroup(DataSetItem dataSetItemObject) {
        Map<String, String> stringStringMap = new HashMap<>();

        for(ItemType itemType : dataSetItemObject.getItem()){
            BlocksExecutionGroupType blocksExecutionGroup = itemType.getBlocksExecutionGroup();
            for(BlockExecutionFlowType blockExecutionFlowType : blocksExecutionGroup.getBlockExecutionFlow()){
                if(blockExecutionFlowType.getBlockName().toUpperCase().contains("NEXT_G_")){
                    if(!ELSE.equalsIgnoreCase(blockExecutionFlowType.getMatchCond())){
                        stringStringMap.put(blockExecutionFlowType.getBlockName(), blockExecutionFlowType.getMatchCond());
                    }
                }
            }
        }
        return stringStringMap;
    }

    private static List<HelpTextType> retrieveHelpTextListByActionHelpTextDefinition(List<HelpTextType> helpTextTypeListFromRepo,
                                                                                     ActionType currentAction) throws DataloadGeneratorException {

        List<String> helpTextNameList = new ArrayList<>();
        List<HelpTextType> toReturn = new ArrayList<>();
        if(currentAction.getInputParameters() != null){
            if(currentAction.getInputParameters().getInputParameter() != null
                    && !currentAction.getInputParameters().getInputParameter().isEmpty()){
                for(ActionType.InputParameters.InputParameter inputGroup : currentAction.getInputParameters().getInputParameter()){
                    if(inputGroup.getHelpTexts() != null){
                        if(inputGroup.getHelpTexts().getHelpText() != null && !inputGroup.getHelpTexts().getHelpText().isEmpty()){
                            for(String helpTextName : inputGroup.getHelpTexts().getHelpText()){
                                if(!EMPTY_STRING.equalsIgnoreCase(helpTextName)){
                                    helpTextNameList.add(helpTextName);
                                }
                            }
                        }
                    }
                }
            }
        }
        if(currentAction.getOutputParameters() != null){
            if(currentAction.getOutputParameters().getOutputParameter() != null
                    && !currentAction.getOutputParameters().getOutputParameter().isEmpty()){
                for(ActionType.OutputParameters.OutputParameter outputParameter : currentAction.getOutputParameters().getOutputParameter()){
                    if(outputParameter.getHelpTexts() != null){
                        if(outputParameter.getHelpTexts().getHelpText() != null
                                && !outputParameter.getHelpTexts().getHelpText().isEmpty()){
                            for(String helpTextNameOut : outputParameter.getHelpTexts().getHelpText()){
                                if(!EMPTY_STRING.equalsIgnoreCase(helpTextNameOut)){
                                    helpTextNameList.add(helpTextNameOut);
                                }
                            }
                        }
                    }
                }
            }
        }

        List<String> helpTextNameSaved = new ArrayList<>();
        for(HelpTextType helpText : helpTextTypeListFromRepo){
            helpTextNameSaved.add(helpText.getName());
        }

        if(helpTextNameList != null &&
                !helpTextNameList.isEmpty()){

            for(String helpTextName : helpTextNameList){
                if(!helpTextNameSaved.contains(helpTextName)){
                    throw new DataloadGeneratorException("Error - The current action [" + currentAction.getName() + "] has configured HelpText [" + helpTextName + "] but there isn't any configurated HelpText Template for that. Configure an help text with [" + helpTextName +"] and try again ");
                }
                for(HelpTextType helpTextType : helpTextTypeListFromRepo){
                    if(helpTextType.getName().equalsIgnoreCase(helpTextName)){
                        toReturn.add(helpTextType);
                    }
                }
            }
        }
        return toReturn;

        //TODO se serve la rimetto - da qui importiamo gli help text non trovati da un file esterno
        //                                    if(retrieveStructureFromExternalFile){
//                                        TD dataload = getDataloadFromPath(getConfigurationFile().getPATH_GENERATED_EXTERNAL());
//                                        for(HelpTextType helpTextType : dataload.getHelpTexts().getHelpText()){
//                                            if(helpTextType.getName().equalsIgnoreCase(helpTextName)){
//                                                List<HelpTextType> helpTextTypes = new ArrayList<>();
//                                                helpTextTypes.add(helpTextType);
//                                                TbsOrmBuilder.getTbsOrmImplementation().saveHelpText(helpTextTypes);
//                                                return helpTextType;
//                                            }
//                                        }
//                                    }
    }
    /**
     *
     * This method manage the complexity about the nextgroup logic
     *
     *
     * @param tdType
     * @param solutionName
     * @param mapNextGroupActionValue
     * @return
     */
    public static TD refactorNextGroupOccurrences(TD tdType,
                                                  String solutionName,
                                                  Map<String, String> mapNextGroupActionValue,
                                                  String groupName,
                                                  String groupOrder,
                                                  Map<String, Integer> groupOrderMap) throws DataloadGeneratorException {

        Set<String> hashSetNextGroup = getHashSetNextGroup();
        List<GroupType.Indexes.Index> indexToRemoveList = new ArrayList<>();
        List<GroupType.Indexes.Index> indexToAddSet = new ArrayList<>();

        for(GroupType groupType : tdType.getGroups().getGroup()){
            for(GroupType.Indexes.Index intedex : groupType.getIndexes().getIndex()){
                for(String nextGroupName : hashSetNextGroup) {

                    if(intedex.getStates().getValue().contains(nextGroupName)){

                        if(isNextGroupOrderGraterThanCallerGroup(nextGroupName, groupName, groupOrder, groupOrderMap)){
                            indexToRemoveList.add(intedex);
                            GroupType.Indexes.Index indexToAdd = buildNextGroupIndexItem(nextGroupName, intedex);
                            indexToAddSet.add(indexToAdd);
                            buildActionAndWorkflowStructureForNextGroupCase(groupType.getStates().getName(), nextGroupName, indexToAdd, solutionName, mapNextGroupActionValue);
                            populateCheckpointList(groupType, indexToAdd, groupName, groupOrder, nextGroupName);
                        }else{
                            indexToRemoveList.add(intedex);
                            String actionName = "redirect_from_" + groupName + "_to_" + nextGroupName;
                            GroupType.Indexes.Index indexToAdd = buildNextGroupIndexItemForLowerOrderGroupCase(nextGroupName, actionName, intedex);
                            indexToAddSet.add(indexToAdd);
                            populateNextGroupReminder(groupName,groupOrder, indexToAdd, actionName, nextGroupName);
                        }
                    }
                }
            }
            if(indexToAddSet != null && !indexToAddSet.isEmpty() && indexToAddSet.size() > 0){
                List<GroupType.Indexes.Index> indexes = refactorIndexListToAdd(indexToAddSet, hashSetNextGroup);
                groupType.getIndexes().getIndex().addAll(indexes);
            }
            if(indexToRemoveList != null && !indexToRemoveList.isEmpty()){
                groupType.getIndexes().getIndex().removeAll(indexToRemoveList);
            }
        }
        return tdType;
    }


    /**
     * This method chek if the group that will manage the next group call has to reach a group with order grater or lower
     *
     * @param nextGroupString
     * @param groupName
     * @param groupOrder
     * @param groupOrderMap
     * @return
     */
    private static boolean isNextGroupOrderGraterThanCallerGroup(String nextGroupString, String groupName, String groupOrder, Map<String, Integer> groupOrderMap) throws DataloadGeneratorException {
        checkNextGroupIncludedIntoDataloadBuildProcess(nextGroupString, groupOrderMap, groupName, groupOrder);
        if(groupOrderMap.get(nextGroupString) > Integer.valueOf(groupOrder)){
            return true;
        }else{
            return false;
        }
    }

    private static void checkNextGroupIncludedIntoDataloadBuildProcess(String nextGroupString, Map<String, Integer> groupOrderMap, String groupName, String groupOrder) throws DataloadGeneratorException {
        if (!groupOrderMap.containsKey(nextGroupString)) {
            throw new DataloadGeneratorException("Error - Current group [" + groupName +"] with order [" + groupOrder +"] has configured NextGroup Call to [" + nextGroupString + "] that is not been included into groups list. Include/Create a group with name [" + nextGroupString + "] and try again.");
        }
    }

    /**
     * This object keep track of:
     *
     *   - group that call the next group (we get the stateVector from this element)
     *   - index from where the next group is called
     *   - group name (caller)
     *   - group order (caller)
     *   - nextGroupName
     *
     *  ex: we got this 'stateVector' (groupType.getStates().getName()) and is valorized like 'indexToAdd' to call the group with name 'nextGroupName'
     *
     * @param groupType
     * @param indexToAdd
     * @param groupName
     * @param groupOrder
     * @param nextGroupName
     */
    private static void populateCheckpointList(GroupType groupType,
                                               GroupType.Indexes.Index indexToAdd,
                                               String groupName,
                                               String groupOrder,
                                               String nextGroupName) {

        if(!doCheckpointAlreadyContainsCheckpointForTheCurrentNextGroup(checkpoints, groupName, groupOrder, nextGroupName)){
            Checkpoint checkpoint = new Checkpoint();
            checkpoint.setId(String.valueOf(UUID.randomUUID()));

            checkpoint.setStateVector(groupType.getStates().getName());
            checkpoint.setGroupName(groupName);
            checkpoint.setGroupOrder(Integer.parseInt(groupOrder));
            checkpoint.setIndex(indexToAdd);
            checkpoint.setNextGroupName(nextGroupName);

            checkpoints.add(checkpoint);
        }

    }

    private static boolean doCheckpointAlreadyContainsCheckpointForTheCurrentNextGroup(List<Checkpoint> checkpoints,
                                                                                       String groupName,
                                                                                       String groupOrder,
                                                                                       String nextGroupName) {
        if(checkpoints.isEmpty()){
            return false;
        }
        for(Checkpoint checkpoint : checkpoints){
            if(checkpoint.getGroupName().equalsIgnoreCase(groupName) &&
                    checkpoint.getGroupOrder() == new Integer(groupOrder) &&
                    checkpoint.getNextGroupName().equalsIgnoreCase(nextGroupName)){
                return true;
            }
        }
        return false;
    }

    private static void populateNextGroupReminder(String groupName,
                                                  String groupOrder,
                                                  GroupType.Indexes.Index indexToAdd,
                                                  String actionName,
                                                  String nextGroupName) {

        if(!nexgGroupReminderListAlreadyContainsCallFromGroupToNextGroup(nextGroupName, groupName)){
            NextGroupReminder nextGroupReminder = new NextGroupReminder();
            nextGroupReminder.setCallerGroupName(groupName);
            nextGroupReminder.setCallerGroupOrder(new Integer(groupOrder));
            nextGroupReminder.setCallerIndex(indexToAdd);
            nextGroupReminder.setActionName(actionName);
            nextGroupReminder.setNextGroupName(nextGroupName);

            nexgGroupReminderList.add(nextGroupReminder);
        }
    }

    private static boolean nexgGroupReminderListAlreadyContainsCallFromGroupToNextGroup(String nextGroupName, String callerGroupName) {
        if(nexgGroupReminderList != null) {
            for(NextGroupReminder nextGroupReminder : nexgGroupReminderList){
                if(nextGroupReminder.getNextGroupName().equalsIgnoreCase(nextGroupName) &&
                        nextGroupReminder.getCallerGroupName().equalsIgnoreCase(callerGroupName)){
                    return true;
                }
            }
        }
        return false;
    }


    public static List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public static List<NextGroupReminder> getNexgGroupReminderList() {
        return nexgGroupReminderList;
    }

    public static void restoreUtilsList() {
        nexgGroupReminderList.clear();
        checkpoints.clear();
    }


    /**
     * This method create an action structure for the current NextGroup Action
     * Create also a workflows structure with the action name and workflow name
     * The workflow name will be used to create a workflowf.xml (saved on file system) to integrate into the solution
     * The solutionName will be used to create wf and to give it a name
     * From the indexToAdd it will be taken the position and the name of the action to create the action structure
     * @param name
     * @param nextGroupString
     * @param indexToAdd
     * @param solutionName
     * @param mapNextGroupActionValue
     */
    private static void buildActionAndWorkflowStructureForNextGroupCase(List<String> name,
                                                                        String nextGroupString,
                                                                        GroupType.Indexes.Index indexToAdd,
                                                                        String solutionName,
                                                                        Map<String, String> mapNextGroupActionValue) throws DataloadGeneratorException {
        Set<String> keySet = mapNextGroupActionValue.keySet();
        for(String actionName : keySet){
            if(mapNextGroupActionValue.get(actionName).equalsIgnoreCase(nextGroupString)){
                buildActionTemplateTypeForNextGroup(actionName, mapNextGroupActionValue.get(actionName), solutionName);
                buildWorkflowTemplateTypeForNextGroup(actionName, mapNextGroupActionValue.get(actionName), solutionName);
            }
        }
    }

    public static void buildActionTemplateTypeForNextGroup(String actionName, String stateVectorName, String solutionName) throws DataloadGeneratorException {
        System.out.println("Building action template for Action [" + actionName + "] - Soution [" + solutionName + "]");
        List<ActionType> actionTypes = new ArrayList<>();
        actionTypes.add(buildActionTypeFroNextGroupAction(actionName,stateVectorName));
        List<ActionType> actionTypeList = TbsOrmBuilder.getTbsOrmImplementation().getActionTypeList();
        for(ActionType actionType : actionTypeList){
            if(actionName.equalsIgnoreCase(actionType.getName())){
                TbsOrmBuilder.getTbsOrmImplementation().deleteActionType(actionType);
            }
        }
        TbsOrmBuilder.getTbsOrmImplementation().saveActions(actionTypes);
    }

    /**
     * This method build an action template that implement the logic for the ne'nextGroup'
     *
     * @param actionName
     * @param stateVectorName
     * @return
     */
    private static ActionType buildActionTypeFroNextGroupAction(String actionName, String stateVectorName) {
        ActionType actionType = new ActionType();
        actionType.setName(actionName);
        actionType.setLabel("!!" + actionName+"!Group "+ stateVectorName);
        actionType.setDescription("!!" + actionName +"!Group "+ stateVectorName);
        actionType.setType("internal");
        actionType.setActionMode("Open Loop");
        actionType.setOutputParser("None");
        actionType.setDispatchType("HPSA");
        ActionType.InputParameters inputParameters = new ActionType.InputParameters();
        ActionType.InputParameters.InputParameter inputParameter = new ActionType.InputParameters.InputParameter();
        inputParameter.setName("echoInput");
        inputParameter.setType("String");
        inputParameter.setDefaultValue(stateVectorName);
        inputParameter.setEditable(false);
        inputParameter.setHidden(true);

        List<ActionType.InputParameters.InputParameter> inputParametersList = new ArrayList<>();
        inputParametersList.add(inputParameter);
        inputParameters.getInputParameter().addAll(inputParametersList);
        actionType.setInputParameters(inputParameters);

        ActionType.OutputParameters outputParameters = new ActionType.OutputParameters();
        ActionType.OutputParameters.OutputParameter outputParameter = new ActionType.OutputParameters.OutputParameter();
        outputParameter.setName(actionName);
        outputParameter.setType("String");
        outputParameter.setLabel("!!result!");
        outputParameter.setEditable(false);
        outputParameter.setHidden(true);
        outputParameter.setIncludeInStateVector(true);
        outputParameter.setStateVectorName(actionName);

        List<ActionType.OutputParameters.OutputParameter> outputParametersList = new ArrayList<>();
        outputParametersList.add(outputParameter);
        outputParameters.getOutputParameter().addAll(outputParametersList);
        actionType.setOutputParameters(outputParameters);
        return actionType;
    }

    public static void buildWorkflowTemplateTypeForNextGroup(String actionName, String stateIndexValue, String solutionName) throws DataloadGeneratorException {

        String workflowName = "HPETD_" + solutionName + "_" + actionName;

        System.out.println("Building workflow template for Action [" + actionName + "] - Solution [" + solutionName + "]. WorkflowName [" + workflowName + "]");

        WorkflowTemplateType workflowTemplateType = new WorkflowTemplateType();
        workflowTemplateType.setActionName(actionName);
        workflowTemplateType.setServiceType(ANY);
        workflowTemplateType.setWorkflow(workflowName);
        workflowTemplateType.setActionSolution(solutionName);

        List<WorkflowTemplateType> workflowTemplateTypesToAdd = new ArrayList<>();
        workflowTemplateTypesToAdd.add(workflowTemplateType);
        System.out.println("Saving workflow Template [" + buildStringXmlFromWorkflowTemplate(workflowTemplateType) + "]");

        List<WorkflowTemplateType> workflowsTemplateList = TbsOrmBuilder.getTbsOrmImplementation().getWorkflowsTemplateList();
        for(WorkflowTemplateType workflowTemplateType1 : workflowsTemplateList){
            if(actionName.equalsIgnoreCase(workflowTemplateType1.getActionName())){
                TbsOrmBuilder.getTbsOrmImplementation().deleteWorkflowType(workflowTemplateType1);
            }
        }
        TbsOrmBuilder.getTbsOrmImplementation().saveWorkflows(workflowTemplateTypesToAdd);
        String wfToSaveStringXml = buildWorkflowToDriveProcessThroughtGroups("HPETD_" + solutionName + "_" + actionName, solutionName, actionName, stateIndexValue);
        saveWfDriveGroupAsXmlFile(wfToSaveStringXml, workflowName, solutionName);

    }

    private static List<GroupType.Indexes.Index> refactorIndexListToAdd(List<GroupType.Indexes.Index> indexToAddSet, Set<String> hashSetNextGroup) {
        // TODO Costruire workflow per gestire l'update dello stateVector

        List<GroupType.Indexes.Index> toAdd = new LinkedList<>();
        for(String nextGroupName : hashSetNextGroup){
            for(GroupType.Indexes.Index index : indexToAddSet){
                if(index.getStates().getValue().contains(nextGroupName)){
                    if(!isAlreadyNextActionContained(toAdd, nextGroupName)){
                        toAdd.add(index);
                    }
                }
            }
        }
        return toAdd;
    }

    private static boolean isAlreadyNextActionContained(List<GroupType.Indexes.Index> toAdd, String nextGroupName) {
        for(GroupType.Indexes.Index index : toAdd){
            if(index.getStates().getValue().contains(nextGroupName)){
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to set the state item to go into a next group
     *
     * @param nextGroupName
     * @param indexRoot
     * @return
     */
    private static GroupType.Indexes.Index buildNextGroupIndexItem(String nextGroupName, GroupType.Indexes.Index indexRoot) {
        GroupType.Indexes.Index index = new GroupType.Indexes.Index();
        index.setStates(buildState(nextGroupName, indexRoot));
        index.setNextGroup(nextGroupName);
        return index;
    }

    /**
     * This method is used to set the state item to call an action that will set the stateVector value to reach the group called (in case that called group has order lower than the 'caller'
     *
     * @param nextGroupName
     * @param indexRoot
     * @return
     */

    private static GroupType.Indexes.Index buildNextGroupIndexItemForLowerOrderGroupCase(String nextGroupName, String actionName, GroupType.Indexes.Index indexRoot) {

        GroupType.Indexes.Index index = new GroupType.Indexes.Index();
        index.setStates(buildState(nextGroupName, indexRoot));
        GroupType.Indexes.Index.Actions actions = new GroupType.Indexes.Index.Actions();
        GroupType.Indexes.Index.Actions.Recommended recommended = new GroupType.Indexes.Index.Actions.Recommended();
        ActionRefType actionRefType = new ActionRefType();
        actionRefType.setName(actionName);
        recommended.getAction().add(actionRefType);
        actions.setRecommended(recommended);
        actions.setProceed(true);
        index.setActions(actions);
        return index;
    }

    private static GroupType.Indexes.Index.States buildState(String nextGroupName, GroupType.Indexes.Index indexRoot) {
        GroupType.Indexes.Index.States states = new GroupType.Indexes.Index.States();
        List<String> stringListIndexesToAdd = new ArrayList<>();
        for(String s : indexRoot.getStates().getValue()){
            if(!s.equalsIgnoreCase(nextGroupName)){
                stringListIndexesToAdd.add(STAR_CHAR);
            }else{
                stringListIndexesToAdd.add(nextGroupName);
            }
        }
        states.getValue().addAll(stringListIndexesToAdd);
        return states;
    }

    private static Collection<? extends String> putLowerCase(List<String> currentStateItem) {
        ListIterator<String> iterator = currentStateItem.listIterator();
        while (iterator.hasNext())
        {
            iterator.set(iterator.next().toLowerCase());
        }
        return currentStateItem;
    }

    public static DataSetItem manageDuplicatedBlocknameIntoSameItem(DataSetItem dataSetItemObjectFromExt) {

        duplicatedBlockNameMap = new HashMap<>();
//        logger.info("    Blocks List (" + dataSetItemObjectFromExt.getItem().size() + " items) \n");

        for(int i = 0; i < dataSetItemObjectFromExt.getItem().size(); i++){
            int counter = 1;
            HashSet<String> blockNameSet = new HashSet<>();
            ItemType itemType = dataSetItemObjectFromExt.getItem().get(i);

//            logger.info("      -   "  + itemType.getBlocksExecutionGroup().getBlockExecutionFlow());
            List<BlockExecutionFlowType> blockExecutionFlow = itemType.getBlocksExecutionGroup().getBlockExecutionFlow();

            for(int j = 0; j < blockExecutionFlow.size(); j++) {

                if(blockNameSet.contains(blockExecutionFlow.get(j).getBlockName())){
                    String currentBlockName = blockExecutionFlow.get(j).getBlockName();
                    String buildedBlockName = currentBlockName + "_" + counter;
                    dataSetItemObjectFromExt.getItem().get(i).getBlocksExecutionGroup().getBlockExecutionFlow().get(j).setBlockName(buildedBlockName);
                    counter ++;
                    blockNameSet.add(buildedBlockName);
                    duplicatedBlockNameMap.put(buildedBlockName, currentBlockName);
                }else{
                    blockNameSet.add(blockExecutionFlow.get(j).getBlockName());
                }
            }
        }
//        logger.info("\n");

        return dataSetItemObjectFromExt;
    }


    private static WorkflowTemplateType retrieveWorkflowTemplateTypeBySavedActionNameFromWfRepoList(List<WorkflowTemplateType> workflowTemplateListFromRepo,
                                                                                                    String actionNameInside,
                                                                                                    String currentActionName) throws DataloadGeneratorException {
        for(WorkflowTemplateType workflowTemplateType : workflowTemplateListFromRepo){
            if(actionNameInside.toLowerCase().equalsIgnoreCase(workflowTemplateType.getActionName())){
                return buildNewInstanceFromWorkflowRetrievedAndName(currentActionName, workflowTemplateType);
            }
        }
        if(retrieveStructureFromExternalFile) {
            TD dataload = getDataloadFromPath(getConfigurationFile().getPATH_GENERATED_EXTERNAL());
            for (WorkflowTemplateType workflowTemplateType : dataload.getWorkflowTemplates().getWorkflowTemplate()) {
                return buildNewInstanceFromWorkflowRetrievedAndName(currentActionName, workflowTemplateType);
            }
        }
        System.out.println("Error - The current action [" + actionNameInside + "] need a workflow configured to work. Create a workflow which have as action name [" + actionNameInside + "] and try again");
        throw new DataloadGeneratorException("Error - The current action [" + actionNameInside + "] need a workflow configured to work. Create a workflow which have as action name [" + actionNameInside + "] and try again");
    }

    private static ActionType retrieveActionTypeBySavedNameFromSavedList(List<ActionType> actionTypeListFromRepo, String actionNameInside, String currentActionName) throws DataloadGeneratorException {

        for(ActionType actionType : actionTypeListFromRepo) {
            if(actionNameInside.toLowerCase().equalsIgnoreCase(actionType.getName().toLowerCase())){
                return buildNewInstanceFromActionRetrievedAndName(currentActionName, actionType);
            }
        }
        if(retrieveStructureFromExternalFile){
            TD dataload = getDataloadFromPath(getConfigurationFile().getPATH_GENERATED_EXTERNAL());
            for(ActionType actionType : dataload.getActions().getAction()) {
                if (actionNameInside.equalsIgnoreCase(actionType.getName())) {
                    List<ActionType> actionTypes = new ArrayList<>();
                    actionTypes.add(actionType);
                    for(WorkflowTemplateType workflowTemplateType : dataload.getWorkflowTemplates().getWorkflowTemplate()){
                        if(workflowTemplateType.getActionName().equalsIgnoreCase(actionNameInside.toLowerCase())){
                            List<WorkflowTemplateType> workflowTemplateTypes = new ArrayList<>();
                            workflowTemplateTypes.add(workflowTemplateType);
                            TbsOrmBuilder.getTbsOrmImplementation().saveActions(actionTypes);
                            TbsOrmBuilder.getTbsOrmImplementation().saveWorkflows(workflowTemplateTypes);
                            System.out.println("Adding [" + actionType.getName() +"] from external file");
                            return actionType;
                        }
                    }
                }
            }
        }
        System.out.println("Error - The current action [" + actionNameInside + "] is not been configured. Configure it and try again.");
        throw new DataloadGeneratorException("Error - The current action [" + actionNameInside + "] is not been configured. Configure it and try again.");
    }

    private static ActionType buildNewInstanceFromActionRetrievedAndName(String currentActionName, ActionType actionType) throws DataloadGeneratorException {
        String s = buildStringXmlFromActionType(actionType);
        ActionType actionType1 = buildActionTypeObjectFromXmlString(s);
        String actionName = actionType1.getName();

        for(ActionType.OutputParameters.OutputParameter outputParameter : actionType1.getOutputParameters().getOutputParameter()){
            if(outputParameter.getStateVectorName() != null){
                if(outputParameter.getStateVectorName().equalsIgnoreCase(actionName)){
                    outputParameter.setStateVectorName(currentActionName);
                }
            }
        }
        actionType1.setName(currentActionName);
        return actionType1;
    }

    private static WorkflowTemplateType buildNewInstanceFromWorkflowRetrievedAndName(String currentActionName, WorkflowTemplateType workflowTemplateType) throws DataloadGeneratorException {
        String workFlowTemplateStringXml = buildStringXmlFromWorkflowTemplate(workflowTemplateType);
        WorkflowTemplateType workflowTemplateTypeToAdd = buildWorkflowTypeObjectFromXmlString(workFlowTemplateStringXml);
        workflowTemplateTypeToAdd.setActionName(currentActionName);
        return workflowTemplateTypeToAdd;
    }

//    private static TD.Actions getActionStructure(Set<String> actionNameList) throws DataloadGeneratorException {
//        TD.Actions actionTypeList = new TD.Actions();
//
//        Iterator<String> iterator = actionNameList.iterator();
//        List<ActionType> actionTypes = new ArrayList<>();
//        ActionType actionType;
//        while(iterator.hasNext()){
//            String currentActionName = iterator.next();
//            actionType = new ActionType();
//            actionType.setServiceValidation(true);
//            if(duplicatedBlockNameMap.containsKey(currentActionName)){
//                actionType.setLabel(duplicatedBlockNameMap.get(currentActionName));
//            }else {
//                actionType.setLabel(currentActionName);
//            }
//
//            actionTypes.add(actionType);
//        }
//        actionTypeList.getAction().addAll(actionTypes);
//        return actionTypeList;
//    }

    private static GroupType.Indexes buildIndexesList(DataSetItem dataSetItemObject,
                                                      Map<String, Integer> indexesListOfStateItems) throws DataloadGeneratorException {



        GroupType.Indexes indexesType = new GroupType.Indexes();
        Iterator<ItemType> dataSetItemsIterator = dataSetItemObject.getItem().iterator();

        List<GroupType.Indexes.Index> listIndexTypeByDataSetItem = new ArrayList<>();
        while (dataSetItemsIterator.hasNext()){
            ItemType currentItemFromDataSet = dataSetItemsIterator.next();
            validateItemTypeObject(currentItemFromDataSet);
            if(!hasElse(currentItemFromDataSet)){
                listIndexTypeByDataSetItem.addAll(getListIndexTypeByDataSetItem(currentItemFromDataSet, indexesListOfStateItems));
            }
        }
        indexesType.getIndex().addAll(removeDuplicatesFromListOfIndexType(listIndexTypeByDataSetItem));
        return indexesType;
    }

    private static boolean hasElse(ItemType currentItemFromDataSet) {
        Iterator<BlockExecutionFlowType> iterator = currentItemFromDataSet.getBlocksExecutionGroup().getBlockExecutionFlow().iterator();

        while (iterator.hasNext()){
            BlockExecutionFlowType next = iterator.next();
            if("ELSE".equalsIgnoreCase(next.getMatchCond())){
                return true;
            }
        }
        return false;
    }

    private static List<GroupType.Indexes.Index> getListIndexTypeByDataSetItem(ItemType currentItemFromDataSet, Map<String, Integer> indexesListOfStateItems) throws DataloadGeneratorException {

        List<GroupType.Indexes.Index> indexTypes = new ArrayList<>();
        List<BlockExecutionFlowType> blockExecutionFlowList = currentItemFromDataSet.getBlocksExecutionGroup().getBlockExecutionFlow();

        for(int i=0; i < blockExecutionFlowList.size()-1; i++){
            GroupType.Indexes.Index indexType = new GroupType.Indexes.Index();
            BlockExecutionFlowType currentBlockFlow = blockExecutionFlowList.get(i);
            GroupType.Indexes.Index.States statesType = new GroupType.Indexes.Index.States();
            if(i==0) {
                indexTypes.add(buildPatterInputStateIndexFirstAction(indexesListOfStateItems, currentBlockFlow.getBlockName())); //to remove once we reach data from database
            }
            if(i < blockExecutionFlowList.size()-1) {

                if(EXIT.equalsIgnoreCase(blockExecutionFlowList.get(i+1).getBlockName())){
//                    indexType.setActions(getActionByNameAndType(buildPatternOutputName(currentItemFromDataSet),DataloadProperties.patternBuilderReccomended,DataloadProperties.patterBuilderPossible,DataloadProperties.patternBuilderProceed));
                    GroupType.Indexes.Index.EndState endState = new GroupType.Indexes.Index.EndState();
                    indexType.setEndState(endState);
                }else{
                    indexType.setActions(getActionByNameAndType(blockExecutionFlowList.get(i+1).getBlockName(),true,false,false, true));
                }
            }
            if(i == blockExecutionFlowList.size()-1) {
//                GroupType.Indexes.Index.EndState endState = new GroupType.Indexes.Index.EndState();
//                indexType.setEndState(endState);
            }
            if(indexTypes.size()!=0 && indexTypes.get(indexTypes.size()-1) != null){
                statesType.getValue().addAll(getOrderedListOfValues(indexTypes.get(indexTypes.size()-1), currentItemFromDataSet, currentBlockFlow, indexesListOfStateItems));
            }else{
                statesType.getValue().addAll(getOrderedListOfValues(null, currentItemFromDataSet, currentBlockFlow, indexesListOfStateItems));

            }
            indexType.setStates(statesType);
            indexTypes.add(indexType);
        }
        return indexTypes;
    }

    private static GroupType.Indexes.Index buildPatterInputStateIndexFirstAction(Map<String, Integer> indexesListOfStateItems, String blockName) throws DataloadGeneratorException {

        GroupType.Indexes.Index indexType = new GroupType.Indexes.Index();
        String[] currentArrayOfString = buildEmptyArrayWithDefinedSize(indexesListOfStateItems.size());
        GroupType.Indexes.Index.States statesType = new GroupType.Indexes.Index.States();
        statesType.getValue().addAll(new ArrayList<>(Arrays.asList(currentArrayOfString)));
        indexType.setStates(statesType);
        indexType.setActions(getActionByNameAndType(blockName,
                DataloadProperties.chooseActionReccomended,
                DataloadProperties.chooseActionPossible,
                DataloadProperties.chooseActionExcluded,
                DataloadProperties.proceedChoosInputAction));

        return indexType;
    }

    private static GroupType.Indexes.Index.Actions getActionByNameAndType(String currentActionName, boolean recommended, boolean possible, boolean excluded, boolean proceed) throws DataloadGeneratorException {
        GroupType.Indexes.Index.Actions actionsType = new GroupType.Indexes.Index.Actions();

        if(proceedTrueActions.contains(duplicatedBlockNameMap.get(currentActionName)) ||
                proceedTrueActions.contains(currentActionName)){
            actionsType.setProceed(true);
        }else{
            actionsType.setProceed(false);
        }
        if(reccomendedActions.contains(duplicatedBlockNameMap.get(currentActionName)) ||
                reccomendedActions.contains(currentActionName)){
            recommended = true;
            possible = false;
            excluded = false;
        }
        if(possibleActions.contains(duplicatedBlockNameMap.get(currentActionName)) ||
                possibleActions.contains(currentActionName)){
            possible = true;
            recommended = false;
            excluded = false;
        }
        if(excludedActions.contains(duplicatedBlockNameMap.get(currentActionName)) ||
                excludedActions.contains(currentActionName)){
            excluded = true;
            possible = false;
            recommended = false;
        }

        List<ActionRefType> actionTypeList = new ArrayList<>();
        ActionRefType actionRefType = new ActionRefType();

        if(recommended && possible && excluded) {
            logger.severe("Error. Method: getActionByNameAndType - Cannot generate ActionsType object because Recomended is ["+recommended+"],Possible is ["+possible+"] and Exclded [" + excluded + "]. You can have just one parameter set as true per time.");
            throw new DataloadGeneratorException("Error. Method: getActionByNameAndType - Cannot generate ActionsType object because Recomended is ["+recommended+"],Possible is ["+possible+"] and Exclded [" + excluded + "]. You can have just one parameter set as true per time.");
        }
        if(recommended){
            GroupType.Indexes.Index.Actions.Recommended recommendedType = new GroupType.Indexes.Index.Actions.Recommended();
            actionRefType.setName(currentActionName);
            actionTypeList.add(actionRefType);
            recommendedType.getAction().addAll(actionTypeList);
            actionsType.setRecommended(recommendedType);
        }else if(possible){
            GroupType.Indexes.Index.Actions.Possible possibleType = new GroupType.Indexes.Index.Actions.Possible();
            actionRefType.setName(currentActionName);
            actionTypeList.add(actionRefType);
            possibleType.getAction().addAll(actionTypeList);
            actionsType.setPossible(possibleType);
        } else if(excluded) {
            GroupType.Indexes.Index.Actions.Excluded possibleType = new GroupType.Indexes.Index.Actions.Excluded();
            actionRefType.setName(currentActionName);
            actionTypeList.add(actionRefType);
            possibleType.getAction().addAll(actionTypeList);
            actionsType.setExcluded(possibleType);
        }else{
            logger.severe("Error. Method: getActionByNameAndType - Cannot generate ActionsType object if type are undefined. At least one of the input boolean parameter must be true. Change configuration and try again");
            throw new DataloadGeneratorException("Error. Method: getActionByNameAndType - Cannot generate ActionsType object if type are undefined. At least one of the input boolean parameter must be true. Change configuration and try again");
        }
        actionsName.add(currentActionName);

        return actionsType;
    }

    private static List<String> getOrderedListOfValues(GroupType.Indexes.Index lastIndexType , ItemType currentItemFromDataSet, BlockExecutionFlowType currentBlockFlow, Map<String, Integer> indexesListOfStateItems) {
        String[] currentArrayOfString;

        if(lastIndexType == null){
            currentArrayOfString = buildEmptyArrayWithDefinedSize(indexesListOfStateItems.size());
            currentArrayOfString[indexesListOfStateItems.get(TECHNOLOGY)] = currentItemFromDataSet.getPatternFilter().getTechnology();
            currentArrayOfString[indexesListOfStateItems.get(SERVICE)] = currentItemFromDataSet.getPatternFilter().getService();
            currentArrayOfString[indexesListOfStateItems.get(IS_UNIQUE_CPE)] = currentItemFromDataSet.getPatternFilter().getIsUniqueCpe();
            currentArrayOfString[indexesListOfStateItems.get(PATTERN_NUMBER)] = STAR_CHAR;
            currentArrayOfString[indexesListOfStateItems.get(PROBLEM_TYPE)] = currentItemFromDataSet.getPatternFilter().getProblemType();
            currentArrayOfString[indexesListOfStateItems.get(RG_TYPE)] = currentItemFromDataSet.getPatternFilter().getRgType();

            currentArrayOfString[indexesListOfStateItems.get(CLASS_VALUE)] = currentItemFromDataSet.getPatternFilter().getClassValue();
            currentArrayOfString[indexesListOfStateItems.get(SEGMENT)] = currentItemFromDataSet.getPatternFilter().getSegment();
        }else{
            currentArrayOfString = lastIndexType.getStates().getValue().toArray(new String[0]);
        }

        if(currentBlockFlow.getBlockName().equalsIgnoreCase(EXIT)){
        }else{
            if(!ELSE.equalsIgnoreCase(currentBlockFlow.getMatchCond())){
                currentArrayOfString[indexesListOfStateItems.get(currentBlockFlow.getBlockName())] = currentBlockFlow.getMatchCond();
            }else{
                currentArrayOfString[indexesListOfStateItems.get(currentBlockFlow.getBlockName())] = STAR_CHAR;
            }
        }
        return new ArrayList<>(Arrays.asList(currentArrayOfString));
    }


    /**
     * This method give back the stateItemList from DataSetItem Object
     *
     */
    private static List<String> getStateItemList(DataSetItem dataSetItem) throws DataloadGeneratorException {
        List<String> stateItemList = new ArrayList<>();
        Iterator<ItemType> iterator = dataSetItem.getItem().iterator();

        while (iterator.hasNext()){
            ItemType currentItemType = iterator.next();

            fieldsAsStringList = getFieldsAsStringList(currentItemType.getPatternFilter());
            Iterator<BlockExecutionFlowType> blockExecutionFlowTypeIterator = currentItemType.getBlocksExecutionGroup().getBlockExecutionFlow().iterator();

            while (blockExecutionFlowTypeIterator.hasNext()){

                BlockExecutionFlowType currentBlockExec = blockExecutionFlowTypeIterator.next();

                validateStringValueBySpecialCharPresence(currentBlockExec.getBlockName().toUpperCase(), SPECIAL_CHAR_EX);
                stateItemList.add(currentBlockExec.getBlockName());

            }
        }
        //place the exit block in the end of his section
        for (int i = 0; i < stateItemList.size(); i++) {
            if(EXIT.equalsIgnoreCase(stateItemList.get(i))) {
                stateItemList.remove(i);
                stateItemList.add(EXIT);
            }
        }
        return removeDuplicatedValueFromListOfString(stateItemList);
    }

    /**
     * this method give back a list of String which represents the Field[] of input Object
     *
     * @param objectClass
     * @return
     */
    private static Collection<? extends String> getFieldsAsStringList(Object objectClass) throws DataloadGeneratorException {
        if(objectClass instanceof PatternFilterType) {
            List<String> classFieldList = new ArrayList<>();
            Field[] declaredFields = PatternFilterType.class.getDeclaredFields();


            for (int i = 0; i < declaredFields.length; i++) {
                validateStringValueBySpecialCharPresence(declaredFields[i].getName(),SPECIAL_CHAR_EX);
                classFieldList.add(declaredFields[i].getName());
            }
            return classFieldList;
        }
        else{
            logger.severe("Error - Impossible to build the stateItem List because the structure of input file cannot be cast to PatternFilterType Object. Check the intermediate file.");
            throw new DataloadGeneratorException("Error - Impossible to build the stateItem List because the structure of input file cannot be cast to PatternFilterType Object. Check the intermediate file.");
        }
    }

    private static void buildDetailsActionLists(String actionDetailsPathFile) throws DataloadGeneratorException {
        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();

        proceedTrueActions = new ArrayList<>();
        reccomendedActions = new ArrayList<>();
        possibleActions = new ArrayList<>();
        excludedActions = new ArrayList<>();

        try (FileReader reader = new FileReader(actionDetailsPathFile)) {
            Object obj = jsonParser.parse(reader);
            JSONArray actionDetailsStringJson = (JSONArray) obj;
            List<ActionDetails> actionDetailsList = mapper.readValue(actionDetailsStringJson.toJSONString(), new TypeReference<List<ActionDetails>>() {});

            for(ActionDetails currentActionDetails : actionDetailsList) {
                if(currentActionDetails.isProceed()){
                    proceedTrueActions.add(currentActionDetails.getName());
                }
                if(currentActionDetails.isReccomended()){
                    reccomendedActions.add(currentActionDetails.getName());
                }
                if(currentActionDetails.isPossible()){
                    possibleActions.add(currentActionDetails.getName());
                }
                if(currentActionDetails.isExcluded()){
                    excludedActions.add(currentActionDetails.getName());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - No configuration file found. Path [" + actionDetailsPathFile + "]. " + e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot stream configuration file. " + e);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Unable to parse action details." + e);
        }
    }
}
