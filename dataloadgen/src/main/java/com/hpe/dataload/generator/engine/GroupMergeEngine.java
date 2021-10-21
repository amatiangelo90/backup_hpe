package com.hpe.dataload.generator.engine;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.model.dataloadmodel.*;
import com.hpe.dataload.generator.model.intfilemodel.DataSetItem;
import com.hpe.dataload.generator.orm.TbsOrmBuilder;
import com.hpe.dataload.generator.orm.model.TbsPatternSavedTEntity;
import com.hpe.dataload.generator.utils.Checkpoint;
import com.hpe.dataload.generator.utils.ConverterIntermediateFileEngine;
import com.hpe.dataload.generator.utils.EnumTedVersion;
import com.hpe.dataload.generator.utils.NextGroupReminder;
import com.hpe.ted.modelconverter.exception.ConverterException;
import java.util.*;
import java.util.logging.Logger;
import static com.hpe.dataload.generator.engine.Builder.*;
import static com.hpe.dataload.generator.utils.DataloadGenUtils.*;
import static com.hpe.dataload.generator.utils.WorkflowTemplateUtils.buildAndWriteOnFileSystemWfFileForMClearVector;
import static com.hpe.dataload.generator.utils.XmlSourceReader.getDataSetTypeObjectFromString;

public class GroupMergeEngine {

    private static final Logger logger = Logger.getLogger(GroupMergeEngine.class.getName());
    private static final String BILLING_ACCOUNT                     = "BILLINGACCOUNT";
    private static final String ROC_NAME                            = "ROCNAME";
    private static final String ROC_ID                              = "ROCID";
    private static final String RES_CHOOSE_INPUT                    = "RES_chooseInput";
    private static final String STAR_CHAR                           = "*";
    private static boolean addBillingAndRocNameId                   = true;

    public static List<String> actionTypeEnumValue = new ArrayList<>(Arrays.asList(
            "String",
            "Integer",
            "Float",
            "Boolean"
    ));

    public static void buildDataloadFromTbsPatternSavedEntity(List<TbsPatternSavedTEntity> tbsPatternSavedTEntitieList) throws DataloadGeneratorException {
        try {
            logger.info("MergeGroup Method - starting..");
            if(tbsPatternSavedTEntitieList == null
                    || tbsPatternSavedTEntitieList.isEmpty()){
                throw new DataloadGeneratorException("Error - Merging process aborted due receive Intermediate Files List EMPTY OR NULL");
            }else{
                Map<String, Integer> mapGroupOrder = buildMapGroupOrderFromTbsPattSavedListObj(tbsPatternSavedTEntitieList);
                List<TD> tdListWithOrderGraterThanZero = new ArrayList<>();
                TD dataload = null;
                for(TbsPatternSavedTEntity tbsPatternSavedTEntity : tbsPatternSavedTEntitieList) {

                    if("td_admin".equalsIgnoreCase(tbsPatternSavedTEntity.getOperator())){
                        if("0".equalsIgnoreCase(tbsPatternSavedTEntity.getClazz())) {
                            if(dataload == null){
                                dataload = buildDataloadFromSlimFile(tbsPatternSavedTEntity.getIntermediateXml(),
                                        tbsPatternSavedTEntity.getSolutionName(),
                                        tbsPatternSavedTEntity.getTechnology(),
                                        tbsPatternSavedTEntity.getClazz(),
                                        mapGroupOrder);
                            }else {
                                throw new DataloadGeneratorException("Error - You can add just one group with order [0]");
                            }
                        }else{
                            tdListWithOrderGraterThanZero.add(buildDataloadFromSlimFile(tbsPatternSavedTEntity.getIntermediateXml(),
                                    tbsPatternSavedTEntity.getSolutionName(),
                                    tbsPatternSavedTEntity.getTechnology(),
                                    tbsPatternSavedTEntity.getClazz(),
                                    mapGroupOrder));
                        }
                    }
                    if("DataloadInMem".equalsIgnoreCase(tbsPatternSavedTEntity.getSegment())){
                        if("0".equalsIgnoreCase(tbsPatternSavedTEntity.getClazz())) {
                            if(dataload == null){
                                dataload = buildDataloadWithIncomingGroup(tbsPatternSavedTEntity.getIntermediateXml());
                            }else {
                                throw new DataloadGeneratorException("Error - You can add just one group with order [0]");
                            }
                        }else{
                            tdListWithOrderGraterThanZero.add(buildDataloadWithIncomingGroup(tbsPatternSavedTEntity.getIntermediateXml()));
                        }
                    }
                }
                if(dataload == null){
                    throw new DataloadGeneratorException("Error - Cannot go over with the generation because is missing a gourp with order [0]. A dataload must have one group with that order.");
                }else{
                    dataload = addBillingRocNameAndId(dataload);
                }
                for(TD dataloadToMerge : tdListWithOrderGraterThanZero) {
                    Collection<? extends GroupType> groupFromDataloadToMerge = getGroupFromDataloadToMerge(dataloadToMerge);
                    checkIfDataloadAlreadyContainsGroup(dataload, groupFromDataloadToMerge);

                    dataload.getGroups().getGroup().addAll(groupFromDataloadToMerge);

                    if(dataloadToMerge.getActions() != null){
                        Collection<? extends ActionType> actionFromDataloadToMerge = getActionFromDataloadToMerge(dataloadToMerge);
//                    checkIfDataloadAlreadyContainsActions(dataload, actionFromDataloadToMerge);
                        dataload.getActions().getAction().addAll(actionFromDataloadToMerge);
                    }

                    if(dataloadToMerge.getWorkflowTemplates() != null){
                        Collection<? extends WorkflowTemplateType> workflowTemplateFromDataloadToMerge = getWorkflowTemplateFromDataloadToMerge(dataloadToMerge);
                        checkIfDataloadAlreadyContainsWorkflowTemplate(dataload, workflowTemplateFromDataloadToMerge);
                        dataload.getWorkflowTemplates().getWorkflowTemplate().addAll(workflowTemplateFromDataloadToMerge);
                    }

                    if(dataloadToMerge.getHelpTexts() != null){
                        if(dataloadToMerge.getHelpTexts().getHelpText() != null){
                            dataload.getHelpTexts().getHelpText().addAll(TbsOrmBuilder.getTbsOrmImplementation().getListHelpText());
                        }else{
                            TD.HelpTexts helpTexts = new TD.HelpTexts();
                            helpTexts.getHelpText().addAll(TbsOrmBuilder.getTbsOrmImplementation().getListHelpText());
                            dataload.setHelpTexts(helpTexts);
                        }
                    }
                }
            dataload = manageMClearVectorCase(dataload);

                // TODO potrebbe non servire - Non ci sono mai azioni o wf duplicati (il nome delle actions dipende dal gruppo e dall'ordine dalle quali vengono richiamati)
                dataload = refactorDataloadFromDuplicatedItems(dataload);
                dataload = addActionAndWorkflowStructureForLowerOrderGroupCallLogic(dataload, getCheckpoints(), getNexgGroupReminderList());
                saveResultIntoExternalFile(dataload,
                        getConfigurationFile().getPATH_GENERATED_SAVE(),
                        "TOOL",
                        "1",
                        "8.1.1",
                        "",
                        "Amati Angelo",
                        true,
                        true,
                        true,
                        null);
            }
        } catch (DataloadGeneratorException e) {
            throw e;
        } finally {
            restoreUtilsList();
        }
    }

    private static Map<String, Integer> buildMapGroupOrderFromTbsPattSavedListObj(List<TbsPatternSavedTEntity> tbsPatternSavedTEntities) {
        Map<String, Integer> groupNameOrderMap = new HashMap<>();
        for(TbsPatternSavedTEntity tbsPatternSavedTEntity : tbsPatternSavedTEntities){
            groupNameOrderMap.put(tbsPatternSavedTEntity.getTechnology(), Integer.valueOf(tbsPatternSavedTEntity.getClazz()));
        }
        return groupNameOrderMap;
    }

    private static TD manageMClearVectorCase(TD dataload) throws DataloadGeneratorException {
        String solutionName = dataload.getSolution();
        System.out.println("Refactoring action type for M_clearVector..");
        List<ActionType> actionToRefactor = new ArrayList<>();
        for(ActionType actionType : dataload.getActions().getAction()){
            if(actionType.getName().contains("M_clearVector")){
                actionToRefactor.add(actionType);
            }
        }
        dataload.getActions().getAction().removeAll(actionToRefactor);

        List<String> fullStateVectorList = getFullStateVectorList(dataload);
        List<ActionType> actionTypeListMClearVector = new ArrayList<>();

        for(ActionType actionType : actionToRefactor){
            actionTypeListMClearVector.add(buildActionTypeDriveToLowerOrderGroupByFullStateVectorList(actionType.getName(), fullStateVectorList));
        }
        dataload.getActions().getAction().addAll(actionTypeListMClearVector);

        System.out.println("Refactoring workflow type for M_clearVector..");
        List<WorkflowTemplateType> workflowToRefactor = new ArrayList<>();
        for(WorkflowTemplateType workflowTemplateType : dataload.getWorkflowTemplates().getWorkflowTemplate()){
            if(workflowTemplateType.getActionName().contains("M_clearVector")){
                workflowToRefactor.add(workflowTemplateType);
            }
        }
        dataload.getWorkflowTemplates().getWorkflowTemplate().removeAll(workflowToRefactor);

        List<WorkflowTemplateType> workflowTemplateTypeToAdd = new ArrayList<>();
        for(WorkflowTemplateType workflowTemplateType : workflowToRefactor){
            workflowTemplateTypeToAdd.add(getWorkflowStructureByActionWfAndSolution("HPETD_m_clear_vector", workflowTemplateType.getActionName(), solutionName));
        }
        dataload.getWorkflowTemplates().getWorkflowTemplate().addAll(workflowTemplateTypeToAdd);

        buildAndWriteOnFileSystemWfFileForMClearVector("HPETD_m_clear_vector", fullStateVectorList, solutionName, null);
        return dataload;
    }

    private static TD refactoringDataloadToIntegrateCleanVectorActions(TD dataload) throws DataloadGeneratorException {
        List<ActionType> actionTypes = new ArrayList<>();
        List<WorkflowTemplateType> workflowTemplateTypes = new ArrayList<>();

        for(GroupType groupType : dataload.getGroups().getGroup()){
            if(groupType.getOrder() == 0){
                return dataload;
            }
            Map<String, String> stringStringMap = buildMapFromGroupIndexAndStateList(groupType.getName(), groupType.getOrder(), groupType.getStates().getName());

            List<String> stateVectorNames = buildRefactoredStateVectorNames(groupType.getStates().getName(), stringStringMap);
            groupType.getStates().getName().clear();
            groupType.getStates().getName().addAll(stateVectorNames);

            for (String currentKey : stringStringMap.keySet()){

                if(groupType.getIndexes() != null){
                    if(groupType.getIndexes().getIndex() != null && !groupType.getIndexes().getIndex().isEmpty()){

                        for(GroupType.Indexes.Index index : groupType.getIndexes().getIndex()){
                            if(index.getActions() != null){
                                if(index.getActions().getRecommended() != null){
                                    if(index.getActions().getRecommended().getAction() != null
                                            && !index.getActions().getRecommended().getAction().isEmpty()){
                                        for(ActionRefType action : index.getActions().getRecommended().getAction()){
                                            if(currentKey.equalsIgnoreCase(action.getName())){
                                                action.setName(stringStringMap.get(currentKey));
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        for(GroupType.Indexes.Index index : groupType.getIndexes().getIndex()){
                            if(index.getActions() != null){
                                if(index.getActions().getPossible() != null){
                                    if(index.getActions().getPossible().getAction() != null
                                            && !index.getActions().getPossible().getAction().isEmpty()){
                                        for(ActionRefType action : index.getActions().getPossible().getAction()){
                                            if(currentKey.equalsIgnoreCase(action.getName())){
                                                action.setName(stringStringMap.get(currentKey));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        for(GroupType.Indexes.Index index : groupType.getIndexes().getIndex()){
                            if(index.getActions() != null){
                                if(index.getActions().getExcluded() != null){
                                    if(index.getActions().getExcluded().getAction() != null
                                            && !index.getActions().getExcluded().getAction().isEmpty()){
                                        for(ActionRefType action : index.getActions().getExcluded().getAction()){
                                            if(currentKey.equalsIgnoreCase(action.getName())){
                                                action.setName(stringStringMap.get(currentKey));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                ActionType actionRefactored = getActionRefactored(dataload.getActions().getAction(), currentKey, stringStringMap.get(currentKey));
                if(actionRefactored != null){
                    actionTypes.add(actionRefactored);
                }
                WorkflowTemplateType workflowTemplateType = getWorkflowTemplateRefactored(dataload.getWorkflowTemplates().getWorkflowTemplate(),currentKey, stringStringMap.get(currentKey));
                if(workflowTemplateType != null){
                    workflowTemplateTypes.add(workflowTemplateType);
                }
            }
            dataload.getGroups().getGroup().clear();
            dataload.getActions().getAction().clear();
            dataload.getWorkflowTemplates().getWorkflowTemplate().clear();

            dataload.getGroups().getGroup().add(groupType);
            dataload.getActions().getAction().addAll(actionTypes);
            dataload.getWorkflowTemplates().getWorkflowTemplate().addAll(workflowTemplateTypes);
        }
        return dataload;
    }

    private static List<String> buildRefactoredStateVectorNames(List<String> stateVTorefactor, Map<String, String> stringStringMap) throws DataloadGeneratorException {
        List<String> listWithNoDuplicateItem = removeDuplicatedValueFromListOfString(stateVTorefactor);
        if(listWithNoDuplicateItem.size() != stateVTorefactor.size()){
            throw new DataloadGeneratorException("Error - Cannot Refactor state vector name because the list have duplicated items inside. To let dataload works, build a group with state vector list with unique value");
        }

        String[] definedSizeArray = buildEmptyArrayWithDefinedSize(stateVTorefactor.size());
        Map<String, Integer> positionMap = new HashMap<>();

        for(int i = 0; i < stateVTorefactor.size(); i++) {
            positionMap.put(stateVTorefactor.get(i), i);
        }
        for(String stateVName : stateVTorefactor){
            definedSizeArray[positionMap.get(stateVName)] = stringStringMap.get(stateVName);
        }
        List<String> resultList = Arrays.asList(definedSizeArray);
        return resultList;
    }

    private static WorkflowTemplateType getWorkflowTemplateRefactored(List<WorkflowTemplateType> workflowTemplate, String currentKey, String toReplace) {
        for(WorkflowTemplateType workflowTemplateType : workflowTemplate){
            if(workflowTemplateType.getActionName().equalsIgnoreCase(currentKey)){
                workflowTemplateType.setActionName(toReplace);
                return workflowTemplateType;
            }
        }
        return null;
    }

    private static ActionType getActionRefactored(List<ActionType> action, String currentKey, String toReplace) {
        for(ActionType actionType : action){
            if(actionType.getName().equalsIgnoreCase(currentKey)){
                actionType.setName(toReplace);
                for(ActionType.OutputParameters.OutputParameter outputParameter : actionType.getOutputParameters().getOutputParameter()){
                    if(outputParameter.getStateVectorName() != null){
                        if(outputParameter.getStateVectorName().equalsIgnoreCase(currentKey)){
                            outputParameter.setStateVectorName(toReplace);
                        }
                    }
                }
                return actionType;
            }
        }
        return null;
    }


    private static Map<String, String> buildMapFromGroupIndexAndStateList(String groupName, int i, List<String> name) {
        Map<String, String> stringStringMap = new HashMap<>();
        for(String nameItem : name){
            stringStringMap.put(nameItem, nameItem + "_" + groupName + "_" + i);
        }
        return stringStringMap;
    }

    /**
     *
     * This methdd clean the dataload from:
     *  - duplicated actions definition
     *  - duplicatede workflows definition
     *  - duplicated helpText definitions
     *
     * @param dataload
     * @return
     */
    private static TD refactorDataloadFromDuplicatedItems(TD dataload) throws DataloadGeneratorException {

        List<String> actionNameList = new ArrayList<>();
        List<ActionType> actionTypesToAdd = new ArrayList<>();

        for(ActionType action : dataload.getActions().getAction()){
            if(!actionNameList.contains(action.getName())){
                actionTypesToAdd.add(action);
            }
            actionNameList.add(action.getName());
        }

        List<String> workflowsNameList = new ArrayList<>();
        List<WorkflowTemplateType> wfTypesToAdd = new ArrayList<>();
        for(WorkflowTemplateType workflowTemplateType : dataload.getWorkflowTemplates().getWorkflowTemplate() ){
            if(!workflowsNameList.contains(workflowTemplateType.getActionName())){
                wfTypesToAdd.add(workflowTemplateType);
            }
            workflowsNameList.add(workflowTemplateType.getActionName());
        }

        List<String> helpTextNameList = new ArrayList<>();
        List<HelpTextType> helpTextTypeToAdd = new ArrayList<>();

        for(HelpTextType helpTextType : dataload.getHelpTexts().getHelpText()){
            if(!helpTextNameList.contains(helpTextType.getName())){
                if(!EMPTY_STRING.equalsIgnoreCase(helpTextType.getText())) {
                    helpTextTypeToAdd.add(helpTextType);
                }
            }
            helpTextNameList.add(helpTextType.getName());
        }

        dataload.getActions().getAction().clear();
        dataload.getWorkflowTemplates().getWorkflowTemplate().clear();
        dataload.getHelpTexts().getHelpText().clear();

        dataload.getActions().getAction().addAll(actionTypesToAdd);
        dataload.getWorkflowTemplates().getWorkflowTemplate().addAll(wfTypesToAdd);
        dataload.getHelpTexts().getHelpText().addAll(helpTextTypeToAdd);

        if(actionTypesToAdd.size() != wfTypesToAdd.size()){
            throw new DataloadGeneratorException("Error - There are different numbers of [Actions Definition Type] than [Workflow Definition Type]. The dataload, to work, need to have for each action the workflow definition associated");
        }
        return dataload;
    }

    private static TD addBillingRocNameAndId(TD tdType) throws DataloadGeneratorException {
        int size = 0;
        if(addBillingAndRocNameId){
            for(GroupType groupType : tdType.getGroups().getGroup()){
                if("ReteAlta".equalsIgnoreCase(groupType.getName()) && "0".equalsIgnoreCase(String.valueOf(groupType.getOrder()))){
                    groupType.getStates().getName().add(0, ROC_ID);
                    groupType.getStates().getName().add(0, ROC_NAME);
                    groupType.getStates().getName().add(0, BILLING_ACCOUNT);

                    for(GroupType.Indexes.Index index : groupType.getIndexes().getIndex()){
                        index.getStates().getValue().add(0, STAR_CHAR);
                        index.getStates().getValue().add(0, STAR_CHAR);
                        index.getStates().getValue().add(0, STAR_CHAR);
                        size = index.getStates().getValue().size();
                    }
                    groupType.getIndexes().getIndex().add(0, buildIndexRES_chooseInput(size, RES_CHOOSE_INPUT));
                    tdType.getActions().getAction().add(retrieveHPETDChooseInputActionDefinition());
                    tdType.getWorkflowTemplates().getWorkflowTemplate().add(retrieveHPETDChooseInputWorkflowDefinition());
                    return tdType;
                }
            }
        }
        return tdType;
    }

    private static WorkflowTemplateType retrieveHPETDChooseInputWorkflowDefinition() throws DataloadGeneratorException {
        List<WorkflowTemplateType> workflowsTemplateList = TbsOrmBuilder.getTbsOrmImplementation().getWorkflowsTemplateList();
        for(WorkflowTemplateType workflowTemplateType : workflowsTemplateList){
            if(RES_CHOOSE_INPUT.equalsIgnoreCase(workflowTemplateType.getActionName())){
                workflowTemplateType.setActionName(RES_CHOOSE_INPUT);
                return workflowTemplateType;
            }
        }
        System.out.println("Error - The current action [" + RES_CHOOSE_INPUT + "] need a workflow configured to work. Create a workflow which have as action name [" + RES_CHOOSE_INPUT + "] and try again");
        throw new DataloadGeneratorException("Error - The current action [" + RES_CHOOSE_INPUT + "] need a workflow configured to work. Create a workflow which have as action name [" + RES_CHOOSE_INPUT + "] and try again");
    }

    private static ActionType retrieveHPETDChooseInputActionDefinition() throws DataloadGeneratorException {
        List<ActionType> actionTypeList = TbsOrmBuilder.getTbsOrmImplementation().getActionTypeList();
        for(ActionType actionType : actionTypeList){
            if(RES_CHOOSE_INPUT.equalsIgnoreCase(actionType.getName())){
                actionType.setName(RES_CHOOSE_INPUT);
                return actionType;
            }

        }
        System.out.println("Error - The current action [" + RES_CHOOSE_INPUT + "] is not been configured. Configure it and try again.");
        throw new DataloadGeneratorException("Error - The current action [" + RES_CHOOSE_INPUT + "] is not been configured. Configure it and try again.");
    }

    private static GroupType.Indexes.Index buildIndexRES_chooseInput(int size, String resChooseInput) {
        GroupType.Indexes.Index index = new GroupType.Indexes.Index();
        GroupType.Indexes.Index.Actions actions = new GroupType.Indexes.Index.Actions();
        GroupType.Indexes.Index.Actions.Recommended recommended = new GroupType.Indexes.Index.Actions.Recommended();
        ActionRefType actionRefType = new ActionRefType();
        actionRefType.setName(resChooseInput);
        recommended.getAction().add(actionRefType);
        actions.setRecommended(recommended);
        index.setActions(actions);
        GroupType.Indexes.Index.States states = new GroupType.Indexes.Index.States();
        states.getValue().addAll(Arrays.asList(buildEmptyArrayWithDefinedSize(size)));
        index.setStates(states);
        return index;
    }

    private static TD buildDataloadWithIncomingGroup(String groupTypeStringXml) throws DataloadGeneratorException {
        TD td = new TD();
        List<GroupType> groupTypes = new ArrayList<>();
        GroupType groupTypeObjectFromXmlString = getGroupTypeObjectFromXmlString(groupTypeStringXml);
        groupTypes.add(groupTypeObjectFromXmlString);
        TD.Groups groups = new TD.Groups();
        groups.getGroup().addAll(groupTypes);
        td.setGroups(groups);
        List<String> actionList = retrieveActionListFromGroupsObject(groups);

        if(actionList != null
                && actionList.size() != 0){
            td.setActions(retrieveActionsStructureForHandMadeGroup(actionList));
            td.setWorkflowTemplates(buildWorkflowStructureForHandMadeGroup(actionList));
        }

        return td;
    }

    private static TD.WorkflowTemplates buildWorkflowStructureForHandMadeGroup(List<String> actionList) throws DataloadGeneratorException {
        List<WorkflowTemplateType> workflowTemplateTypes = TbsOrmBuilder.getTbsOrmImplementation().getWorkflowsTemplateList();
        List<WorkflowTemplateType> workflowTypeToAdd = new ArrayList<>();
        List<String> workflowActionNameInMem = retrieveActionListNameFromWorkflowList(workflowTemplateTypes);
        if(actionList != null
                && !actionList.isEmpty()){
            for(String actionName : actionList){
                if(!workflowActionNameInMem.contains(actionName)){
                    System.out.println("Error - The current action [" + actionName + "] need a workflow configured to work. Create a workflow which have as action name [" + actionName + "] and try again");
                    throw new DataloadGeneratorException("Error - The current action [" + actionName + "] need a workflow configured to work. Create a workflow which have as action name [" + actionName + "] and try again");
                }
                for(WorkflowTemplateType workflowTemplateType : workflowTemplateTypes){
                    if(workflowTemplateType.getActionName().equalsIgnoreCase(actionName)){
                        workflowTypeToAdd.add(workflowTemplateType);
                    }
                }
            }
        }

        TD.WorkflowTemplates workflowTemplates = new TD.WorkflowTemplates();
        workflowTemplates.getWorkflowTemplate().addAll(workflowTypeToAdd);
        return workflowTemplates;
    }

    private static TD.Actions retrieveActionsStructureForHandMadeGroup(List<String> actionList) throws DataloadGeneratorException {

        List<ActionType> actionTypeList = TbsOrmBuilder.getTbsOrmImplementation().getActionTypeList();
        List<ActionType> actionsListToAdd = new ArrayList<>();
        List<String> actionNameInMem = retrieveActionListNameFromActionTypeList(actionTypeList);
        if(actionList != null
                && !actionList.isEmpty()){

            for(String actionName : actionList){
                if(!actionNameInMem.contains(actionName)){
                    System.out.println("Error - The current action [" + actionName + "] is not been configured. Configure it and try again.");
                    throw new DataloadGeneratorException("Error - The current action [" + actionName + "] is not been configured. Configure it and try again.");
                }
                for(ActionType actionType : actionTypeList){
                    if(actionType.getName().equalsIgnoreCase(actionName)){
                        actionsListToAdd.add(actionType);
                    }
                }
            }
        }
        TD.Actions actions = new TD.Actions();
        actions.getAction().addAll(actionsListToAdd);
        return actions;
    }

    private static List<String> retrieveActionListNameFromActionTypeList(List<ActionType> actionTypeList) {
        List<String> actionNameList = new ArrayList<>();
        for(ActionType actionType : actionTypeList){
            actionNameList.add(actionType.getName());
        }
        return actionNameList;
    }
    private static List<String> retrieveActionListNameFromWorkflowList(List<WorkflowTemplateType> workflowTemplateTypes) {
        List<String> workflowActionNameList = new ArrayList<>();
        for(WorkflowTemplateType workflowTemplateType : workflowTemplateTypes){
            workflowActionNameList.add(workflowTemplateType.getActionName());
        }
        return workflowActionNameList;
    }

    public static TD buildDataloadFromSlimFile(String intermediateSlimFileXml,
                                               String solution,
                                               String groupName,
                                               String groupOrder,
                                               Map<String, Integer> groupOrderMap) throws DataloadGeneratorException {
        String replace = intermediateSlimFileXml.replace("ROCUpdate", "rocUpdate");
        ConverterIntermediateFileEngine converterIntermediateFileEngine = new ConverterIntermediateFileEngine();

        try {
            String fatXmlFile = converterIntermediateFileEngine.performSlimToFatModelConverter(replace);

            DataSetItem dataSetItemObjectFromExt = getDataSetTypeObjectFromString(fatXmlFile);
            DataSetItem dataSetItemObject = manageDuplicatedBlocknameIntoSameItem(dataSetItemObjectFromExt);

            TD td = buildDataload(dataSetItemObject,
                    true,
                    true,
                    EnumTedVersion.version_8_1,
                    solution,
                    "1",
                    groupName,
                    groupOrder,
                    groupOrderMap);

            td = refactoringDataloadToIntegrateCleanVectorActions(td);
            return td;


        } catch (Exception e) {
            logger.severe("Error - Impossible to convert intermediate file from Slim to Fat. Check Intermediate file structure. " + e);
            throw new DataloadGeneratorException(e);
        }
    }

    private static TD addActionAndWorkflowStructureForLowerOrderGroupCallLogic(TD td,
                                                                               List<Checkpoint> checkpoints,
                                                                               List<NextGroupReminder> nexgGroupReminderList) throws DataloadGeneratorException {

        if(checkpoints == null
                || nexgGroupReminderList == null
                || checkpoints.isEmpty()
                || nexgGroupReminderList.isEmpty()){
            return td;
        }

        for(NextGroupReminder nextGroupReminder : nexgGroupReminderList){

            System.out.println("Build structure and workflow template for [" + nextGroupReminder.getActionName() + "] action.");

            for(Checkpoint checkpoint : checkpoints){
                if(checkpoint.getNextGroupName().equalsIgnoreCase(nextGroupReminder.getNextGroupName())){
                    td = buildActionAndWorkflowStructureForCallingLowerOrderGroup(
                            td,
                            nextGroupReminder.getActionName(),
                            nextGroupReminder.getNextGroupName(),

                            checkpoint.getGroupName(),
                            checkpoint.getGroupOrder(),
                            checkpoint.getStateVector(),
                            checkpoint.getIndex());
                }
            }
        }
        return td;
    }

    private static TD buildActionAndWorkflowStructureForCallingLowerOrderGroup(TD td,
                                                                               String actionName,
                                                                               String nextGroupName,

                                                                               String checkpointGroupName,
                                                                               int checkpointGroupOrder,
                                                                               List<String> checkpointStateVector,
                                                                               GroupType.Indexes.Index checkPointIndex) throws DataloadGeneratorException {
        Map<String, String> stateVectorValueMap = new HashMap<>();

        for(int i = 0; i < checkpointStateVector.size(); i ++){
            if(!checkPointIndex.getStates().getValue().get(i).equalsIgnoreCase(STAR_CHAR)){
                stateVectorValueMap.put(checkpointStateVector.get(i), checkPointIndex.getStates().getValue().get(i));
            }
        }

        for(GroupType groupType : td.getGroups().getGroup()){
            if(groupType.getOrder() > checkpointGroupOrder){
                for(String currentStateItem : groupType.getStates().getName()){
                    stateVectorValueMap.put(currentStateItem, "-");
                }
            }
        }


        td.getActions().getAction().add(buildActionTypeDriveToLowerOrderGroupByFullStateVectorList(actionName,
                new ArrayList<>(stateVectorValueMap.keySet())));

        td.getWorkflowTemplates().getWorkflowTemplate().add(buildWorkflowTemplateAndCreateWorkflowObjectForLowerGroupCall(stateVectorValueMap, nextGroupName, td.getSolution(), actionName));

        return td;
    }

    private static WorkflowTemplateType buildWorkflowTemplateAndCreateWorkflowObjectForLowerGroupCall(Map<String, String> stateVectorValueMap,
                                                                                                      String nextGroupName,
                                                                                                      String solution,
                                                                                                      String actionName) throws DataloadGeneratorException {


        buildAndWriteOnFileSystemWfFileForMClearVector("HPETD_" + actionName,
                new ArrayList<>(stateVectorValueMap.keySet()),
                solution,
                stateVectorValueMap);
        WorkflowTemplateType workflowStructureByActionNameForMClearVector = getWorkflowStructureByActionWfAndSolution("HPETD_" + actionName, actionName, solution);
        return workflowStructureByActionNameForMClearVector;
    }

    private static void checkIfDataloadAlreadyContainsActions(TD dataload,
                                                              Collection<? extends ActionType> actionFromDataloadToMerge) throws DataloadGeneratorException {
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

    private static void checkIfDataloadAlreadyContainsGroup(TD dataload, Collection<? extends GroupType> groupFromDataloadToMerge) throws DataloadGeneratorException {
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

    private static Collection<? extends GroupType> getGroupFromDataloadToMerge(TD dataloadToMerge) {
        TD.Groups groups = dataloadToMerge.getGroups();
        for(GroupType groupType : groups.getGroup()){
            if(groupType.getOrder() == 0){
                groupType.setOrder(1);
            }
            System.out.println("Group added  ["+ groupType.getName()+"] with order [" + groupType.getOrder()+"]");
        }
        return groups.getGroup();
    }

    private static Collection<? extends ActionType> getActionFromDataloadToMerge(TD dataloadToMerge) {

        List<ActionType> actionTypesToAdd = new ArrayList<>();
        System.out.println("### ACTION TYPE TO ADD ###");
        for (ActionType actionType : dataloadToMerge.getActions().getAction()){
            if(actionType != null){
                actionTypesToAdd.add(actionType);
                System.out.println("       - [" + actionType.getName() +"]");
            }
        }
        System.out.println("### ACTION TYPE TO ADD END###\n");
        return actionTypesToAdd;
    }

//    private static void checkIfDataloadAlreadyContainsActions(TD dataload, Collection<? extends ActionType> actionFromDataloadToMerge) throws DataloadGeneratorException {
//        List<String> actionTypesExisting = new ArrayList<>();
//        for(ActionType actionType : dataload.getActions().getAction()) {
//            actionTypesExisting.add(actionType.getName());
//        }
//        for(ActionType actionType : actionFromDataloadToMerge){
//            if(actionTypesExisting.contains(actionType.getName())){
//                throw new DataloadGeneratorException("Error - The current action with name [" + actionType.getName() + "] is already contained into a dataload. Aborting merge.");
//            }
//        }
//    }

    private static void checkIfDataloadAlreadyContainsWorkflowTemplate(TD dataload, Collection<? extends WorkflowTemplateType> workflowTemplateFromDataloadToMerge) throws DataloadGeneratorException {
//        List<WorkflowTemplateType> workflowTemplateOrigin = dataload.getWorkflowTemplates().getWorkflowTemplate();
//
//        List<String> workflowTemplateOriginNames = new ArrayList<>();
//        for(WorkflowTemplateType workflowTemplateType : workflowTemplateOrigin){
//            workflowTemplateOriginNames.add(workflowTemplateType.getActionName());
//        }
//        for (WorkflowTemplateType workflowTemplateType : workflowTemplateFromDataloadToMerge){
//            if(workflowTemplateOriginNames.contains(workflowTemplateType.getActionName())){
//                throw new DataloadGeneratorException("Error - The current workflow [" + workflowTemplateType.getWorkflow()+ "] is already is already defined for the current Action["+ "-TO ADD--" +"]. Aborting merge.");
//            }
//        }
    }

    private static Collection<? extends WorkflowTemplateType> getWorkflowTemplateFromDataloadToMerge(TD dataloadToMerge) {
        List<WorkflowTemplateType> workflowTemplateTypesToAdd = new ArrayList<>();
        System.out.println("### WORKFLOWS TO ADD ###");
        for(WorkflowTemplateType workflowTemplateType : dataloadToMerge.getWorkflowTemplates().getWorkflowTemplate()){
            workflowTemplateTypesToAdd.add(workflowTemplateType);
            System.out.println("       - ["+workflowTemplateType.getWorkflow()+"] for the current action ["+workflowTemplateType.getActionName()+"]");
        }
        System.out.println("### WORKFLOWS TO ADD END ###\n");
        return workflowTemplateTypesToAdd;
    }
}
