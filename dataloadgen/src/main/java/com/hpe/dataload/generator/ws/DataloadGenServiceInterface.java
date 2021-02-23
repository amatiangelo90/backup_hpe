package com.hpe.dataload.generator.ws;

import com.hpe.dataload.generator.orm.model.ActionDetails;
import com.hpe.dataload.generator.orm.model.BlockDefEntityWrap;
import com.hpe.dataload.generator.orm.model.TbsPatternSavedTEntity;

import javax.ws.rs.core.Response;
import java.util.List;

public interface DataloadGenServiceInterface {

    Response retrieveAllPatternSaved();
    Response retrievePatternSavedBySolution(String solution);
    Response deletePatternSaved(TbsPatternSavedTEntity tbsPatternSavedTEntity);

    Response retrieveBlockDefinition();
    Response updateBlockDiagMappingT(BlockDefEntityWrap blockDefEntityWrap);
    Response createBlockDefConfiguration(BlockDefEntityWrap blockDefEntityWrap);
    Response deleteBlockConfiguration(BlockDefEntityWrap blockDefEntityWrap);

    Response slimToFat(String intermediateFile);
    Response fatToSlim(String intermediateFile);

    Response generateGroupFromIntermediateFile(String intermediateFile);
    Response generateDataloadFromTbsPatternSavedTEntityList(List<TbsPatternSavedTEntity> tbsPatternSavedTEntities);

    Response retrieveGroupsList();
    Response retrieveActionsList();
    Response retrieveWorkflowTemplateList();
    Response retrieveHelpTextList();

    Response addActions(String actionTypesList);
    Response addWorkflows(String workflowTemplateTypes);
    Response addHelpTexts(String helpTextTypes);

    Response editGroup(String groupXml);
    Response editAction(String actionTypeXml);
    Response editWorkflow(String workflowXml);
    Response editHelpText(String actionTypeXml);

    Response deleteAction(String actionTypeXml);
    Response deleteWorkflow(String workflowXml);
    Response deleteHelpText(String workflowXml);

    Response getActionsConfiguration();
    Response refreshtActionsConfiguration();
    Response restoreActionsConfiguration();
    Response saveActionsConfiguration(List<ActionDetails> actionDetails);

    Response deleteAllActions();
    Response deleteAllWorkflows();
    Response deleteAllHelpTexts();

    Response uploadGroupsFromPath(String dataloadXml);
    Response uploadActionsFromPath(String dataloadXml);
    Response uploadWorkflowsFromPath(String dataloadXml);
    Response uploadHelpTExtFromPath(String dataloadXml);
//    Response deleteGroupFromDataload(String dataloadPath, String groupNameToRemove);

}
