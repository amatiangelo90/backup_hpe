package com.hpe.dataload.generator.orm;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.model.dataloadmodel.ActionType;
import com.hpe.dataload.generator.model.dataloadmodel.GroupType;
import com.hpe.dataload.generator.model.dataloadmodel.HelpTextType;
import com.hpe.dataload.generator.model.dataloadmodel.WorkflowTemplateType;
import com.hpe.dataload.generator.orm.model.BlockDefEntityWrap;
import com.hpe.dataload.generator.orm.model.BlockDiagCodeMappingTEntity;
import com.hpe.dataload.generator.orm.model.TbsPatternSavedTEntity;

import java.util.List;

public interface TbsOrmInterface {
    List<TbsPatternSavedTEntity> retrieveTbsPatternSavedTEnt(String solution) throws DataloadGeneratorException;
    List<BlockDiagCodeMappingTEntity> retrieveBlockDiagCodeMapping() throws DataloadGeneratorException;;
    List<BlockDefEntityWrap> retrieveBlockDefTEntity() throws DataloadGeneratorException;

    void deleteTbsPatternSavedEntity(TbsPatternSavedTEntity tbsPatternSavedTEntity) throws DataloadGeneratorException;
    void updateTbsPatternSavedEntity(List<TbsPatternSavedTEntity> tbsPatternSavedTEntityList);

    List<GroupType> getGroupTypeList() throws DataloadGeneratorException;
    List<ActionType> getActionTypeList() throws DataloadGeneratorException;
    List<WorkflowTemplateType> getWorkflowsTemplateList() throws DataloadGeneratorException;
    List<HelpTextType> getListHelpText() throws DataloadGeneratorException;

    void updateGroupType(GroupType groupType) throws DataloadGeneratorException;
    void updateActionType(ActionType actionType) throws DataloadGeneratorException;
    void updateWorkflowTemplateType(WorkflowTemplateType workflowTemplateType) throws DataloadGeneratorException;
    void updateHelpTextType(HelpTextType helpTextType) throws DataloadGeneratorException;

    void deleteActionType(ActionType actionType) throws DataloadGeneratorException;
    void deleteHelpTextType(HelpTextType helpTextType) throws DataloadGeneratorException;
    void deleteWorkflowType(WorkflowTemplateType workflowTemplateType) throws DataloadGeneratorException;

    void saveActions(List<ActionType> actionTypesList) throws DataloadGeneratorException;
    void saveWorkflows(List<WorkflowTemplateType> workflowTemplateTypeList) throws DataloadGeneratorException;
    void saveHelpText(List<HelpTextType> helpTextTemplateTypeList) throws  DataloadGeneratorException;

    void deleteAllActionType() throws DataloadGeneratorException;
    void deleteAllWorkflows() throws DataloadGeneratorException;
    void deleteAllHelpText() throws DataloadGeneratorException;

    void refreshActionsDetailsConfiguration() throws DataloadGeneratorException;
    void restoreDefaultActionsDetailsConfiguration() throws DataloadGeneratorException;

    void importGroupsFromPath(String dataloadXml) throws DataloadGeneratorException;
    void importActionsFromPath(String dataloadXml) throws DataloadGeneratorException;
    void importWorkflowsFromPath(String dataloadXml) throws DataloadGeneratorException;
    void importHelpTextFromPath(String dataloadXml) throws DataloadGeneratorException;

    void importGroupsListReplacingExisting(List<GroupType> groupTypes) throws  DataloadGeneratorException;
    void importActionListReplacingExisting(List<ActionType> actionTypesList) throws DataloadGeneratorException;
    void importWorkflowsListReplacingExisting(List<WorkflowTemplateType> workflowTemplate) throws DataloadGeneratorException;
    void importHelpTextListReplacingExisting(List<HelpTextType> helpTextTypeList) throws DataloadGeneratorException;

    void uploadBlockDefEntity(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException;

    void createBlockDefConfigurationOnDb(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException;
    void deleteBlockConfigurationFromDB(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException;
    void deleteBlockDiagCodeMapping(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException;
    void editBlockDiagCodeMapping(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException;
    void createBlockDiagCodeMapping(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException;

}
