package com.hpe.automatization.test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.model.dataloadmodel.*;
import com.hpe.dataload.generator.model.intfilemodel.ObjectFactory;
import org.junit.Test;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.hpe.dataload.generator.utils.DataloadGenUtils.EMPTY_STRING;
import static com.hpe.dataload.generator.utils.DataloadGenUtils.saveResultIntoExternalFile;

public class Dataload_BonificaClassIndexes {

    private static final String TEMPLATE_WF_PATH = "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\echo_cleanVector_TEMPLATE.xml";

    private static final String SEPARATOR_CHAR = "_DUP_";

    private List<String> listExcludedStateIndexes = new ArrayList<>(Arrays.asList(
            "BILLINGACCOUNT",
            "ROCNAME",
            "ROCID",
            "getData"));

    private List<String> nextGroupList;
    //    private static String PATH = "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\dataloadGen_toRefact.xml";
    //    private static String PATH = "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\dataloadGen_SLIM-1.xml";

    private static String PATH = "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\dataloadGen_Refactored_2602.xml";
    private static String PATH_2 = "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\dataloadGen_GROUP_ADDED-1.xml";
    private static String TOOL_MILION_DATALOAD = "C:\\Users\\50050802\\Desktop\\Progetti\\DATALOAD_DEV_BRANCH\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\dataloadGen_TOOL-1.xml";
    private static final String PATH_NAVIGAZIONE_ASSENTE = "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\dataloadGen_NAVIGAZIONE_ASSENTE-1.xml";
    private static final String PATH_TO_NAVIGAZIONE_LENTA_WIFI = "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\dataloadGen_NAVIGAZIONE_LENTA_WIFI-1.xml";;

    public static TD getDataloadFromPath(String path) throws JAXBException {

        File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(TD.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        TD dataload = (TD) jaxbUnmarshaller.unmarshal(file);

        return dataload;
    }

    @Test
    public void testRefactoringDataload() throws JAXBException {
        List<String> stringsList = new ArrayList<>();
        TD dataload = getDataloadFromPath(TOOL_MILION_DATALOAD);
        List<String> statusGroup0 = new ArrayList<>();
        for(GroupType groupType : dataload.getGroups().getGroup()){
            if("0".equalsIgnoreCase(String.valueOf(groupType.getOrder()))){
                statusGroup0.addAll(groupType.getStates().getName());
            }
            for(String nameList : groupType.getStates().getName()){
                stringsList.add(nameList);
            }
        }
        System.out.println("#### - Counter All StateIndexs: " + stringsList.size());
        List<String> collect = stringsList.stream().distinct().collect(Collectors.toList());
        System.out.println("#### - Counter Keys For ClearVector Map: " + collect.size());
        System.out.println("#### - Group 0 Status Size: " + statusGroup0.size());
        System.out.println("#### - Group 0 Status : " + statusGroup0.toString());
        System.out.println("#### - List : " + stringsList.toString());

    }
    @Test
    public void mergeGroups() throws JAXBException, DataloadGeneratorException {

        TD dataload = getDataloadFromPath(PATH);
        TD dataloadToMerge = getDataloadFromPath(PATH_NAVIGAZIONE_ASSENTE);

        Collection<? extends GroupType> groupFromDataloadToMerge = getGroupFromDataloadToMerge(dataloadToMerge);
        checkIfDataloadAlreadyContainsGroup(dataload, groupFromDataloadToMerge);
        dataload.getGroups().getGroup().addAll(groupFromDataloadToMerge);

        Collection<? extends ActionType> actionFromDataloadToMerge = getActionFromDataloadToMerge(dataloadToMerge);
        checkIfDataloadAlreadyContainsActions(dataload, actionFromDataloadToMerge);
        dataload.getActions().getAction().addAll(actionFromDataloadToMerge);

        Collection<? extends WorkflowTemplateType> workflowTemplateFromDataloadToMerge = getWorkflowTemplateFromDataloadToMerge(dataloadToMerge);
        checkIfDataloadAlreadyContainsWorkflowTemplate(dataload, workflowTemplateFromDataloadToMerge);

        dataload.getWorkflowTemplates().getWorkflowTemplate().addAll(workflowTemplateFromDataloadToMerge);

        dataload.getHelpTexts().getHelpText().addAll(getHelpTextFromDataloadToMerge(dataload));

        saveResultIntoExternalFile(dataload,
                "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\",
                "GROUP_ADDED",
                "1",
                "8.1.1",
                "",
                "Amati Angelo",
                true,
                true,
                true,
                null);

    }

    @Test
    public void mergeGroupsBis() throws JAXBException, DataloadGeneratorException {

        TD dataload = getDataloadFromPath(PATH_2);
        TD dataloadToMerge = getDataloadFromPath(PATH_TO_NAVIGAZIONE_LENTA_WIFI);

        Collection<? extends GroupType> groupFromDataloadToMerge = getGroupFromDataloadToMerge(dataloadToMerge);
        checkIfDataloadAlreadyContainsGroup(dataload, groupFromDataloadToMerge);
        dataload.getGroups().getGroup().addAll(groupFromDataloadToMerge);

        Collection<? extends ActionType> actionFromDataloadToMerge = getActionFromDataloadToMerge(dataloadToMerge);
        checkIfDataloadAlreadyContainsActions(dataload, actionFromDataloadToMerge);
        dataload.getActions().getAction().addAll(actionFromDataloadToMerge);

        Collection<? extends WorkflowTemplateType> workflowTemplateFromDataloadToMerge = getWorkflowTemplateFromDataloadToMerge(dataloadToMerge);
        checkIfDataloadAlreadyContainsWorkflowTemplate(dataload, workflowTemplateFromDataloadToMerge);

        dataload.getWorkflowTemplates().getWorkflowTemplate().addAll(workflowTemplateFromDataloadToMerge);

        dataload.getHelpTexts().getHelpText().addAll(getHelpTextFromDataloadToMerge(dataload));

        saveResultIntoExternalFile(dataload,
                "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\",
                "GROUP_ADDED_2",
                "1",
                "8.1.1",
                "",
                "Amati Angelo",
                true,
                true,
                true,
                null);

    }

    private void checkIfDataloadAlreadyContainsWorkflowTemplate(TD dataload, Collection<? extends WorkflowTemplateType> workflowTemplateFromDataloadToMerge) throws DataloadGeneratorException {
//        List<WorkflowTemplateType> workflowTemplateOrigin = dataload.getWorkflowTemplates().getWorkflowTemplate();
//
//        List<String> workflowTemplateOriginNames = new ArrayList<>();
//        for(WorkflowTemplateType workflowTemplateType : workflowTemplateOrigin){
//            workflowTemplateOriginNames.add(workflowTemplateType.getActionName());
//        }
//
//        for (WorkflowTemplateType workflowTemplateType : workflowTemplateFromDataloadToMerge){
//            if(workflowTemplateOriginNames.contains(workflowTemplateType.getActionName())){
//                throw new DataloadGeneratorException("Error - The current workflow [" + workflowTemplateType.getWorkflow()+ "] is already is already defined for the current Action["+ "-TO ADD--" +"]. Aborting merge.");
//            }
//        }

    }

    private void checkIfDataloadAlreadyContainsActions(TD dataload, Collection<? extends ActionType> actionFromDataloadToMerge) throws DataloadGeneratorException {
        List<String> actionTypesExisting = new ArrayList<>();
        for(ActionType actionType : dataload.getActions().getAction()) {
            actionTypesExisting.add(actionType.getName());
        }
        for(ActionType actionType : actionFromDataloadToMerge){
            if(actionTypesExisting.contains(actionType.getName())){
                throw new DataloadGeneratorException("Error - The current action with name [" + actionType.getName() + "] is already contained into a dataload. Aborting merge.");
            }
        }
    }

    private void checkIfDataloadAlreadyContainsGroup(TD dataload, Collection<? extends GroupType> groupFromDataloadToMerge) throws DataloadGeneratorException {
        List<String> groupNameExisting = new ArrayList<>();

        for(GroupType groupType : dataload.getGroups().getGroup()) {
            groupNameExisting.add(groupType.getName());
        }
        for(GroupType groupType : groupFromDataloadToMerge) {
            if (groupNameExisting.contains(groupType.getName())) {
                throw new DataloadGeneratorException("Error - The current group with name [" + groupType.getName() + "] is already contained into a dataload. Aborting merge.");
            }
        }
    }

    private Collection<? extends HelpTextType> getHelpTextFromDataloadToMerge(TD dataload) {
        List<HelpTextType> helpTextTypesToAdd = new ArrayList<>();
        System.out.println("### HELP TEXT TO ADD ###");
        for(HelpTextType helpTextType : dataload.getHelpTexts().getHelpText()){
            helpTextTypesToAdd.add(helpTextType);
            System.out.println("       - ["+helpTextType.getName()+"]");

        }
        System.out.println("### HELP TEXT TO ADD END###\n");
        return helpTextTypesToAdd;
    }

    private Collection<? extends WorkflowTemplateType> getWorkflowTemplateFromDataloadToMerge(TD dataloadToMerge) {
        List<WorkflowTemplateType> workflowTemplateTypesToAdd = new ArrayList<>();
        System.out.println("### WORKFLOWS TO ADD ###");
        for(WorkflowTemplateType workflowTemplateType : dataloadToMerge.getWorkflowTemplates().getWorkflowTemplate()){
            workflowTemplateTypesToAdd.add(workflowTemplateType);
            System.out.println("       - ["+workflowTemplateType.getWorkflow()+"] for the current action ["+workflowTemplateType.getActionName()+"]");
        }
        System.out.println("### WORKFLOWS TO ADD END ###\n");
        return workflowTemplateTypesToAdd;

    }

    private Collection<? extends ActionType> getActionFromDataloadToMerge(TD dataloadToMerge) {

        List<ActionType> actionTypesToAdd = new ArrayList<>();
        System.out.println("### ACTION TYPE TO ADD ###");
        for (ActionType actionType : dataloadToMerge.getActions().getAction()){
            actionTypesToAdd.add(actionType);
            System.out.println("       - [" + actionType.getName() +"]");
        }
        System.out.println("### ACTION TYPE TO ADD END###\n");
        return actionTypesToAdd;
    }

    private Collection<? extends GroupType> getGroupFromDataloadToMerge(TD dataloadToMerge) {
        TD.Groups groups = dataloadToMerge.getGroups();
        for(GroupType groupType : groups.getGroup()){
            if(groupType.getOrder() == 0){
                groupType.setOrder(1);
            }
            System.out.println("Group added  ["+ groupType.getName()+"] with order [" + groupType.getOrder()+"]");
        }
        return groups.getGroup();
    }

    @Test
    public void refactorDataloadFede() throws JAXBException, DataloadGeneratorException, IOException {
        TD dataload = getDataloadFromPath("C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\4marzo2020\\dataloadGen_Refactored.xml");

        List<String> namesList = new ArrayList<>();
        List<String> namesListGroup0 = new ArrayList<>();

        for(GroupType groupType : dataload.getGroups().getGroup()){
            if(groupType.getOrder() == 0 ){
                namesListGroup0.addAll(groupType.getStates().getName());
            }
            namesList.addAll(groupType.getStates().getName());
        }

//        System.out.println(namesList);
//        System.out.println(namesList.size());
        namesList.removeAll(listExcludedStateIndexes);
//        System.out.println(namesList);
//        System.out.println(namesList.size());

        List<String> otherList = new ArrayList<>(namesList);
        otherList.removeAll(namesListGroup0);
//        System.out.println(otherList);
//        System.out.println(otherList.size());

        for(ActionType actionType : dataload.getActions().getAction()){
            if("M_clearVector".equals(actionType.getName())){
//                actionType.getOutputParameters().getOutputParameter().clear();
//                actionType.getOutputParameters().getOutputParameter().addAll(generateOutputParametersByList(namesList));
                System.out.println("###############################");
                System.out.println("###############################");
                System.out.println("###############################");
                printSome(namesList);
                System.out.println("###############################");
                System.out.println("###############################");
                System.out.println("---------------------------------");
            }else{
                if((actionType.getName().contains("M_clearVector"))){
                    System.out.println("---------------000000000------------------");
//                    actionType.getOutputParameters().getOutputParameter().clear();
//                    actionType.getOutputParameters().getOutputParameter().addAll(generateOutputParametersByList(otherList));
                    printSome(otherList);

                    System.out.println("---------------000000000------------------");
                }
            }
        }
        saveResultIntoExternalFile(dataload,
                "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\4Marzo2020\\",
                "REFACTORED",
                "1",
                "8.1.1",
                "",
                "Amati Angelo",
                true,
                true,
                true,
                null);
    }

    private void printSome(List<String> namesList) {
        String before = "<outputParameters>";
        String last = " </outputParameters>";

        StringBuffer stringBuffer = new StringBuffer(before);

        for (String s : namesList){
            stringBuffer.append("       <outputParameter>" +
                    "                    <name>"+s+"</ns2:name>" +
                    "                    <type>String</ns2:type>" +
                    "                    <label>!!result!</ns2:label>" +
                    "                    <editable>false</ns2:editable>" +
                    "                    <hidden>true</ns2:hidden>" +
                    "                    <includeInStateVector>true</ns2:includeInStateVector>" +
                    "                    <stateVectorName>"+s+"</ns2:stateVectorName>" +
                    "                </outputParameter>");
        }
        stringBuffer.append(last);

        System.out.println(stringBuffer.toString());
    }

    private Collection<? extends ActionType.OutputParameters.OutputParameter> generateOutputParametersByList(List<String> namesList) {
        List<ActionType.OutputParameters.OutputParameter> outputParameters = new ArrayList<>();

        for(String name : namesList){
            outputParameters.add(buildOutputParameterItem(name));
        }
        return outputParameters;
    }

    @Test
    public void refactorDataload() throws JAXBException, DataloadGeneratorException, IOException {
        TD dataload = getDataloadFromPath("C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\4marzo2020\\dataloadGen_Refactored.xml");

        int size = dataload.getGroups().getGroup().size();
        System.out.println("There are " + size + " groups to refactorize");
        List<String> allStateIndexes = new ArrayList<>();
        List<String> stateIndexesGroup0 = null;

        for(WorkflowTemplateType workflowTemplateType : dataload.getWorkflowTemplates().getWorkflowTemplate()){
            if(workflowTemplateType.getActionName().contains("M_clearVector")){
                System.out.println("WF : " + workflowTemplateType.getWorkflow());
            }
        }
        for (int i = 0; i < size; i++){
            GroupType groupType = dataload.getGroups().getGroup().get(i);
            if(groupType.getOrder() == 0){
                stateIndexesGroup0 = groupType.getStates().getName();
            }
            allStateIndexes.addAll(groupType.getStates().getName());
        }
        generateOutputForCleanVectorWfwithStateIndexesList(allStateIndexes);

        System.out.println("Size : "+stateIndexesGroup0.size()+" stateIndexesGroup0 " + stateIndexesGroup0);
        System.out.println("Size : "+ allStateIndexes.size()+ " allStateIndexes" + allStateIndexes);

        allStateIndexes.removeAll(listExcludedStateIndexes);
        stateIndexesGroup0.removeAll(listExcludedStateIndexes);

        System.out.println("After - Size : "+stateIndexesGroup0.size()+" stateIndexesGroup0 " + stateIndexesGroup0);
        System.out.println("After - Size : "+ allStateIndexes.size()+ " allStateIndexes" + allStateIndexes);

        List<String> outputlistForGroup0 = new ArrayList<>(allStateIndexes);
        allStateIndexes.removeAll(stateIndexesGroup0);


        for(ActionType actionType : dataload.getActions().getAction()){
            if(actionType.getName().equals("M_clearVector")){
                System.out.println("Build clear vector for group 0");
                System.out.println("LISTA M_ClearVector for group0 : " + outputlistForGroup0 + " - size: " + outputlistForGroup0.size());
                actionType.getOutputParameters().getOutputParameter().clear();
                actionType.setOutputParameters(buildOutPutParametersList(outputlistForGroup0));
                System.out.println("Size output list of " +actionType.getName() + " : " + actionType.getOutputParameters().getOutputParameter().size());

            }else{
                if(actionType.getName().contains("M_clearVector")){
                    System.out.println("BEFORE - Size output list of " + actionType.getName() +" : " +actionType.getOutputParameters().getOutputParameter().size());
                    actionType.getOutputParameters().getOutputParameter().clear();
                    actionType.setOutputParameters(buildOutPutParametersList(allStateIndexes));
                    System.out.println("AFTER - Size output list of " + actionType.getName() +" : " +actionType.getOutputParameters().getOutputParameter().size());
                }
            }
        }



//        Multimap<String, String> mapKeyRefactoredStateIndexes = buildMapWithDuplicatesStateIndexes(stringLongMap);
//        generateOutputForCleanVectorWfwithStateIndexesList(mapKeyRefactoredStateIndexes);
//        System.out.println("Map out: " + mapKeyRefactoredStateIndexes);
//
//        TD td_refactored = refactorDataload(dataload, mapKeyRefactoredStateIndexes);
////        td_refactored = addActions(td_refactored, mapKeyRefactoredStateIndexes);
//        td_refactored = refactorGroups(td_refactored, buildMapWithDuplicatesStateIndexes(stringLongMap));
//        td_refactored = replaceM_cleanVectorActionIncludingAllStatesVectors(td_refactored, buildMapWithDuplicatesStateIndexes(stringLongMap));
//
//        td_refactored = removeGroupFromTD(td_refactored,"navigazioneLentaWifi");
//
        saveResultIntoExternalFile(dataload,
                "C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\4Marzo2020\\",
                "OSS_TED",
                "1",
                "8.1.1",
                "",
                "Amati Angelo",
                true,
                true,
                true,
                null);
    }

    private ActionType.OutputParameters buildOutPutParametersList(List<String> strings) {
        ActionType.OutputParameters outputParameters = new ActionType.OutputParameters();
        for(String name : strings){
            outputParameters.getOutputParameter().add(buildOutputParameterItem(name));
        }

        return outputParameters;
    }


    private ActionType.OutputParameters.OutputParameter buildOutputParameterItem(String s) {
        ActionType.OutputParameters.OutputParameter outputParameter = new ActionType.OutputParameters.OutputParameter();
        outputParameter.setType("String");
        outputParameter.setName(s);
        outputParameter.setLabel("!!result!");
        outputParameter.setEditable(false);
        outputParameter.setHidden(true);
        outputParameter.setIncludeInStateVector(true);
        outputParameter.setStateVectorName(s);

        return outputParameter;
    }

    private List<String> getStateIndexesGroup0(TD dataload) {
        for(GroupType groupType : dataload.getGroups().getGroup()){
            if(groupType.getOrder() == 0){
                return groupType.getStates().getName();
            }
        }
        return null;
    }


    private TD refactorGroups(TD td_refactored, Multimap<String, String> mapKeyRefactoredStateIndexes) throws DataloadGeneratorException {
        nextGroupList = new ArrayList<>();
        for(GroupType groupType : td_refactored.getGroups().getGroup()){
            System.out.println("$$$$$ Group Name : " + groupType.getName() +" - Order : " + groupType.getOrder() +" $$$$$");
            for(GroupType.Indexes.Index index : groupType.getIndexes().getIndex()){
                if(index.getActions() != null){
                    for(ActionRefType actionRefType : index.getActions().getRecommended().getAction()){
                        String toReplace = calculateValueToReplace(actionRefType.getName(), mapKeyRefactoredStateIndexes, groupType.getStates().getName());
                        System.out.println("Action NAme: " + actionRefType.getName() + " - Value to replace: " + toReplace );
                        actionRefType.setName(toReplace);
                    }
                }else{
                    if(index.getNextGroup() != null || EMPTY_STRING.equalsIgnoreCase(index.getNextGroup())){
                        nextGroupList.add(index.getNextGroup());
                    }
                }
            }

        }
        return td_refactored;
    }

    private String calculateValueToReplace(String name, Multimap<String, String> mapKeyRefactoredStateIndexes, List<String> statesNames) throws DataloadGeneratorException {
        if(mapKeyRefactoredStateIndexes.containsKey(name)){
            Collection<String> listRefactored = mapKeyRefactoredStateIndexes.get(name);
            if(listRefactored.size() == 0){
                return name;
            }else{
                for(String currentItem : listRefactored){
                    if(statesNames.contains(currentItem)){
                        return currentItem;
                    }
                }
                throw new DataloadGeneratorException("Error - Could not refactor");
            }
        }else{
            return name;
        }
    }

    private List<String> getWfList(TD dataload) {
        Iterator<WorkflowTemplateType> iterator = dataload.getWorkflowTemplates().getWorkflowTemplate().iterator();
        List<String> toRet = new ArrayList<>();

        while (iterator.hasNext()){
            WorkflowTemplateType next = iterator.next();
            toRet.add(next.getActionName());
        }
        return toRet;
    }

    private TD addWorkflows(TD td_refactored, Multimap<String, String> mapKeyRefactoredStateIndexes) {

        System.out.println("Size Before : " + td_refactored.getWorkflowTemplates().getWorkflowTemplate().size());
        List<WorkflowTemplateType> workflowTemplate = td_refactored.getWorkflowTemplates().getWorkflowTemplate();
        List<WorkflowTemplateType> workflowTemplateToAdd = new ArrayList<>();

        for(int t = 0; t < workflowTemplate.size(); t++) {
            String currentWfName = workflowTemplate.get(t).getActionName();

            if(mapKeyRefactoredStateIndexes.containsKey(currentWfName)){
                Collection<String> strings = mapKeyRefactoredStateIndexes.get(currentWfName);

                for(String currentKey : strings) {
                    WorkflowTemplateType currentWfTemplate = new WorkflowTemplateType();
                    currentWfTemplate.setActionName(currentKey);
                    currentWfTemplate.setWorkflow(workflowTemplate.get(t).getWorkflow());
                    currentWfTemplate.setServiceType(workflowTemplate.get(t).getServiceType());
                    currentWfTemplate.setActionSolution(workflowTemplate.get(t).getActionSolution());
                    workflowTemplateToAdd.add(currentWfTemplate);
                }
            }else{
                WorkflowTemplateType currentWfTemplateToAdd = new WorkflowTemplateType();
                currentWfTemplateToAdd.setActionName(workflowTemplate.get(t).getActionName());
                currentWfTemplateToAdd.setWorkflow(workflowTemplate.get(t).getWorkflow());
                currentWfTemplateToAdd.setServiceType(workflowTemplate.get(t).getServiceType());
                currentWfTemplateToAdd.setActionSolution(workflowTemplate.get(t).getActionSolution());
                workflowTemplateToAdd.add(workflowTemplate.get(t));
            }

        }

//        seeDifferences(workflowTemplate, workflowTemplateToAdd,mapKeyRefactoredStateIndexes);
        td_refactored.getWorkflowTemplates().getWorkflowTemplate().clear();
        System.out.println("Size after refactoring: " + workflowTemplateToAdd.size());
        td_refactored.getWorkflowTemplates().getWorkflowTemplate().addAll(workflowTemplateToAdd);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        return td_refactored;
    }

//    private void seeDifferences(List<WorkflowTemplateType> workflowTemplate, List<WorkflowTemplateType> workflowTemplateToAdd,Multimap<String, String> mapKeyRefactoredStateIndexes) {
//        System.out.println(workflowTemplate.size() +" ---- "+ workflowTemplateToAdd.size());
//        workflowTemplateToAdd.removeAll(workflowTemplate);
//        int size = workflowTemplateToAdd.size();
//        System.out.println("[][][][][][][][][][][][] ----> " + mapKeyRefactoredStateIndexes.keySet().size());
//        System.out.println("[][][][][][][][][][][][] ----> " + mapKeyRefactoredStateIndexes.size());
//
//        Iterator<String> iterator = mapKeyRefactoredStateIndexes.keySet().iterator();
//        while(iterator.hasNext()){
//            String next = iterator.next();
//            System.out.println("Size for current key ["+ next +"] --> Size : "+ mapKeyRefactoredStateIndexes.get(next).size());
//
//        }
//    }

    private TD addActions(TD td_refactored, Multimap<String, String> mapKeyRefactoredStateIndexes) throws DataloadGeneratorException, JAXBException {
        List<ActionType> actionsList = td_refactored.getActions().getAction();
        List<ActionType> actionTypesListToAdd = new ArrayList<>();

        int size = actionsList.size();
        System.out.println("Actions list size : " + size);

        for(ActionType currentActionType : actionsList) {

            String currentActionName = currentActionType.getName();
            System.out.println("Current Action Name: " + currentActionName);

            if(mapKeyRefactoredStateIndexes.get(currentActionName).size()>1) {
                List<String> actionsNameList = (List<String>) mapKeyRefactoredStateIndexes.get(currentActionName);
                for(String currentActionNameToAdd : actionsNameList){
                    ActionType actionTypeToAdd = replaceActionName(getNewInstanceOfActionType(currentActionType), currentActionNameToAdd, currentActionName);
                    actionTypesListToAdd.add(actionTypeToAdd);
                }
            }else{
                ActionType actionTypeToAdd = currentActionType;
                actionTypesListToAdd.add(actionTypeToAdd);
            }
            System.out.println("mapKeyRefactoredStateIndexes: " + mapKeyRefactoredStateIndexes.get(currentActionName).size());
        }

        td_refactored.getActions().getAction().clear();
        td_refactored.getActions().getAction().addAll(actionTypesListToAdd);

        return td_refactored;
    }

    private ActionType getNewInstanceOfActionType(ActionType currentActionType) throws JAXBException {
        String actionTypeTostring = jaxbObjectToXML(currentActionType);
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(ActionType.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(actionTypeTostring);
            ActionType actionType = (ActionType) unmarshaller.unmarshal(reader);

            return actionType;
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private String jaxbObjectToXML(ActionType actionType) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ActionType.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(actionType, sw);
        return sw.toString();
    }

    private ActionType replaceActionName(ActionType currentActionType, String currentActionNameToAdd, String currentActionName) throws DataloadGeneratorException {
        ActionType actionType = currentActionType;
        if(currentActionName.equalsIgnoreCase(actionType.getName())){

            actionType.setName(currentActionNameToAdd);
            actionType.getOutputParameters().getOutputParameter();

            for(int i = 0; i < actionType.getOutputParameters().getOutputParameter().size(); i++){
                if(actionType.getOutputParameters().getOutputParameter().get(i).getStateVectorName() != null &&
                        !EMPTY_STRING.equalsIgnoreCase(actionType.getOutputParameters().getOutputParameter().get(i).getStateVectorName()) &&
                        currentActionName.equalsIgnoreCase(actionType.getOutputParameters().getOutputParameter().get(i).getStateVectorName())){
                    actionType.getOutputParameters().getOutputParameter().get(i).setStateVectorName(currentActionNameToAdd);
                }
            }
        }else{
            throw new DataloadGeneratorException("Current action name : ["+ currentActionName+" is not been found into the action. Impossible to replace with ["+currentActionNameToAdd+"]");
        }
        return actionType;
    }

    private TD refactorDataload(TD dataload, Multimap<String, String> mapKeyRefactoredStateIndexes) throws DataloadGeneratorException, JAXBException {
        int size = dataload.getGroups().getGroup().size();
        dataload = addWorkflows(dataload, mapKeyRefactoredStateIndexes);
        dataload = addActions(dataload, mapKeyRefactoredStateIndexes);
        for (int i = 0; i < size; i++){
            List<String> names = dataload.getGroups().getGroup().get(i).getStates().getName();
            List<String> replacedStateIndexes = new ArrayList<>();

            for(String nameItem : names){
                replacedStateIndexes.add(((List<String>)mapKeyRefactoredStateIndexes.get(nameItem)).get(0));
                ((List<String>)mapKeyRefactoredStateIndexes.get(nameItem)).remove(0);
            }
            dataload.getGroups().getGroup().get(i).getStates().getName().clear();
            dataload.getGroups().getGroup().get(i).getStates().getName().addAll(replacedStateIndexes);
            System.out.println(replacedStateIndexes);
        }

        return dataload;
    }

    public Map<String, Long> createMapDuplicatesIndexes(List<String> list) throws DataloadGeneratorException {
        Map<String, Long> counter = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Map stateItem with occurrences : " + counter);

        Long total = new Long(0);
        Iterator<String> iterator = counter.keySet().iterator();

        while (iterator.hasNext()){
            String key = iterator.next();
            total = total + counter.get(key);
        }
        System.out.println("Total: " + total);
        System.out.println("Size map: " + counter.size());


//        if(!total.equals(counter.size())){
//            throw new DataloadGeneratorException("Error - Duplicates item are present into state indexes");
//        }
        return counter;
    }

    public Multimap<String, String> buildMapWithDuplicatesStateIndexes(Map <String, Long> stringLongMap){
        Iterator<String> iterator = stringLongMap.keySet().iterator();
        Multimap<String, String> multimap = ArrayListMultimap.create();
        while(iterator.hasNext()){
            String next = iterator.next();
            if(stringLongMap.get(next)!=1) {
                multimap.put(next,next);
                Long aLong = stringLongMap.get(next);
                for(int i = 1; i < aLong; i++){
                    multimap.put(next, next + SEPARATOR_CHAR + i);
                }
            }else{
                multimap.put(next,next);
            }
        }
        return multimap;
    }

    private String getTemplate(String filePath) throws DataloadGeneratorException {
        File file = new File(filePath);

        if(Files.notExists(file.toPath())){
            System.err.println("Error - The file specified as inputIntermediate file doesn't exist: File: [" + filePath +"]. ");
            throw new DataloadGeneratorException("Error - The file specified as inputIntermediate file doesn't exist: File: [" + filePath +"]. ");
        }

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            String wfTemplate = (String) jaxbUnmarshaller.unmarshal(file);

            return wfTemplate;
        } catch (JAXBException e) {
            System.err.println("Error " + e);
            throw new DataloadGeneratorException("Error -" + e);
        }
    }

    public void generateOutputForCleanVectorWfwithStateIndexesList(List<String> stateIndexes) throws IOException, DataloadGeneratorException {

//        Iterator<String> iterator1 = multimap.keySet().iterator();
//        List<String> stateIndexes = new ArrayList<>();
//        while (iterator1.hasNext()){
//            String next = iterator1.next();
//            stateIndexes.addAll(multimap.get(next));
//        }

        String firstHalf = "<Parameter>\n" +
                "\t\t<attribute>";
        String secondHalf = "</attribute>\n" +
                "\t\t<value>-</value>\n" +
                "\t\t<type>String</type>\n" +
                "\t</Parameter>";

        Iterator<String> iterator = stateIndexes.iterator();
        StringBuffer stringBuffer = new StringBuffer("<Parameters>");
        while (iterator.hasNext()){
            String currentStateItem = iterator.next();

            if(!listExcludedStateIndexes.contains(currentStateItem)){
                stringBuffer.append(firstHalf + currentStateItem + secondHalf);
            }
        }
        stringBuffer.append("</Parameters>");

//        String cleanVectorTemplate = getWfTemplate(TEMPLATE_WF_PATH);
//        String cleanVectorTemplate = getTemplate(TEMPLATE_WF_PATH);
//
//        cleanVectorTemplate.replace("TO_REPLACE", stringBuffer);
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\50050802\\Desktop\\Progetti\\GIT\\TED\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\test\\resources\\CheckList\\ref\\4Marzo2020\\cleanVector_output.xml"));
        writer.write(String.valueOf(stringBuffer));
        writer.close();
    }



    private TD replaceM_cleanVectorActionIncludingAllStatesVectors(TD td_refactored, Multimap<String, String> multimap) {

        Iterator<String> iterator1 = multimap.keySet().iterator();
        List<String> stateIndexes = new ArrayList<>();
        while (iterator1.hasNext()){
            String next = iterator1.next();
            stateIndexes.addAll(multimap.get(next));
        }

        List<ActionType> action = td_refactored.getActions().getAction();
        List<ActionType> toRemove = new ArrayList<>();
        List<ActionType> toAdd = new ArrayList<>();

        List<String> actionNameToCreateList = new ArrayList<>();
        for(ActionType actionType : action){
            if(actionType.getName().contains("M_clearVector")){
                actionNameToCreateList.add(actionType.getName());
                toRemove.add(actionType);
            }
        }
        td_refactored.getActions().getAction().removeAll(toRemove);
        for(String actionName : actionNameToCreateList){
            toAdd.add(createNewActionType(actionName, stateIndexes));
        }
        td_refactored.getActions().getAction().addAll(toAdd);
        return td_refactored;
    }

    private ActionType createNewActionType(String actionName, List<String> stateIndexes) {
        ActionType actionType = new ActionType();
        actionType.setName(actionName);
        actionType.setLabel("Clear vector");
        actionType.setDescription("!!M_clearVector!Clear vector");
        actionType.setOutputParameters(createOutputParametersList(stateIndexes));
        return actionType;
    }

    private ActionType.OutputParameters createOutputParametersList(List<String> stateIndexes) {

        ActionType.OutputParameters outputParameters = new ActionType.OutputParameters();
        List<ActionType.OutputParameters.OutputParameter> outputParameters1 = new ArrayList<>();
        for(int t = 0; t < stateIndexes.size(); t++) {
            if(!listExcludedStateIndexes.contains(stateIndexes.get(t))){
                outputParameters1.add(createNewOutputParameterItem(stateIndexes.get(t)));
            }
        }
        outputParameters.getOutputParameter().addAll(outputParameters1);
        return outputParameters;
    }

    private ActionType.OutputParameters.OutputParameter createNewOutputParameterItem(String outParName) {
        ActionType.OutputParameters.OutputParameter outputParameter = new ActionType.OutputParameters.OutputParameter();
        outputParameter.setName(outParName);
        outputParameter.setType("String");
        outputParameter.setLabel("!!result!");
        outputParameter.setEditable(false);
        outputParameter.setHidden(true);
        outputParameter.setIncludeInStateVector(true);
        outputParameter.setStateVectorName(outParName);
        return outputParameter;
    }

    //    @Test
//    public void testCheckDuplicates() throws JAXBException {
//        TD dataload = getDataloadFromPath(PATH);
//        Map<String, Long> wfNames = createMapDuplicatesIndexes(getWfList(dataload));
//
//        for(String key : wfNames.keySet()){
//            if(wfNames.get(key) > 1){
//                System.err.println("ERROR - Duplicated wf name into Workflow definitions --> key: ["+ key+"] - occurrences: " + wfNames.get(key));
//            }
//        }
//
//        Map<String, Long> actionNames = createMapDuplicatesIndexes(getActionsList(dataload));
//
//        for(String key : actionNames.keySet()){
//            if(actionNames.get(key) > 1){
//                System.err.println("ERROR - Duplicated action name into Action definitions --> key: ["+ key+"] - occurrences: " + actionNames.get(key));
//            }
//        }
//    }
//
//    private List<String> getActionsList(TD dataload) {
//        List<ActionType> action = dataload.getActions().getAction();
//        Iterator<ActionType> iterator = action.iterator();
//        List<String> toRet = new ArrayList<>();
//        while (iterator.hasNext()){
//            ActionType next = iterator.next();
//            toRet.add(next.getName());
//        }
//        return toRet;
//    }


}