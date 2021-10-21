package com.hpe.dataload.generator.ws;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.model.dataloadmodel.ActionType;
import com.hpe.dataload.generator.model.dataloadmodel.GroupType;
import com.hpe.dataload.generator.model.dataloadmodel.HelpTextType;
import com.hpe.dataload.generator.model.dataloadmodel.WorkflowTemplateType;
import com.hpe.dataload.generator.orm.TbsOrmBuilder;
import com.hpe.dataload.generator.orm.model.ActionDetails;
import com.hpe.dataload.generator.orm.model.BlockDefEntityWrap;
import com.hpe.dataload.generator.orm.model.GroupTypeWrap;
import com.hpe.dataload.generator.orm.model.TbsPatternSavedTEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static com.hpe.dataload.generator.engine.GroupBuilder.buildGroupFromIntermediateFile;
import static com.hpe.dataload.generator.engine.GroupMergeEngine.buildDataloadFromTbsPatternSavedEntity;
import static com.hpe.dataload.generator.model.intfilemodel.utils.ConverterIntermediateFile.getFatFromSlimModelConverter;
import static com.hpe.dataload.generator.model.intfilemodel.utils.ConverterIntermediateFile.getSlimFromFatConverter;
import static com.hpe.dataload.generator.utils.DataloadGenUtils.*;

@Api(value = "service", description = "RESTful API to integrate dataload generator tool utility")
@Path("/service")
public class DataloadGenServiceImpl implements DataloadGenServiceInterface {

    private static final String EMPTY_STRING = "";

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/patterSaved/retrieveAll")
    public Response retrieveAllPatternSaved() {
        try {
            return Response.status(Response.Status.OK)
                    .status(200)
                    .entity(TbsOrmBuilder.getTbsOrmImplementation().retrieveTbsPatternSavedTEnt(null)).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @ApiOperation(value = "Get all pattern saved searching by solution", notes = "Get all Pattern Saved items by sol", responseContainer = "List", response = Response.class)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/patternSaved/retrieveBySolution/{solution}")
    public Response retrievePatternSavedBySolution(@PathParam("solution") String solution) {
        if(solution == null ||
                EMPTY_STRING.equalsIgnoreCase(solution)){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Exception", "Invalid Solution name. Cannot retrieve PatternSaved")
                    .status(401)
                    .build();
        }else{
            try {
                List<TbsPatternSavedTEntity> tbsPatternSavedTEntities = TbsOrmBuilder.getTbsOrmImplementation().retrieveTbsPatternSavedTEnt(solution);
                if(tbsPatternSavedTEntities == null
                        || tbsPatternSavedTEntities.size() == 0){
                    return Response.status(Response.Status.OK)
                            .header("Exception", "There aren't pattern saved into db.")
                            .status(204)
                            .build();
                }else{
                    return Response.status(Response.Status.OK)
                            .status(200)
                            .entity(tbsPatternSavedTEntities).build();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Response
                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                        .status(500)
                        .header("Exception", e.getMessage())
                        .build();
            }
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteGroup")
    public Response deletePatternSaved(TbsPatternSavedTEntity tbsPatternSavedTEntity) {
        try {
            if(tbsPatternSavedTEntity == null){
                throw new DataloadGeneratorException("Error - Input TbsPatternSaved to delete is null!");
            }
            TbsOrmBuilder.getTbsOrmImplementation().deleteTbsPatternSavedEntity(tbsPatternSavedTEntity);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .build();
        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .status(500)
                    .header("Exception", e.getMessage())
                    .build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getblock")
    public Response retrieveBlockDefinition() {
        System.out.println("Retrieving block definition..");
        try {
            return Response.status(Response.Status.OK)
                    .status(200)
                    .entity(TbsOrmBuilder.getTbsOrmImplementation().retrieveBlockDefTEntity())
                    .build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/uploadBlockDiagCodeMapping")
    public Response updateBlockDiagMappingT(BlockDefEntityWrap blockDefEntityWrap) {
        try {
            TbsOrmBuilder.getTbsOrmImplementation().editBlockDiagCodeMapping(blockDefEntityWrap);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .build();
        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .status(500)
                    .header("Exception", e.getMessage())
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createBlockConfiguration")
    public Response createBlockDefConfiguration(BlockDefEntityWrap blockDefEntityWrap) {
        try {
            TbsOrmBuilder.getTbsOrmImplementation().createBlockDefConfigurationOnDb(blockDefEntityWrap);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .build();
        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .status(500)
                    .header("Exception", e.getMessage())
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteBlockConfiguration")
    public Response deleteBlockConfiguration(BlockDefEntityWrap blockDefEntityWrap) {
        try {
            TbsOrmBuilder.getTbsOrmImplementation().deleteBlockConfigurationFromDB(blockDefEntityWrap);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .build();
        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .status(500)
                    .header("Exception", e.getMessage())
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/slimToFat")
    public Response slimToFat(String intermediateFile) {
        try {
            checkIntermediateFile(intermediateFile);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity(getFatFromSlimModelConverter(intermediateFile))
                    .build();

        } catch (DataloadGeneratorException e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .status(500)
                    .header("Exception", e.getMessage())
                    .build();
        }

    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/fatToSlim")
    public Response fatToSlim(String intermediateFile) {
        try {
            checkIntermediateFile(intermediateFile);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity(getSlimFromFatConverter(intermediateFile))
                    .build();

        } catch (DataloadGeneratorException e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .status(500)
                    .header("Exception", e.getMessage())
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/generateGroupFromIntermediateFile")
    public Response generateGroupFromIntermediateFile(String intermediateFile) {
        try {
            checkIntermediateFile(intermediateFile);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity(buildGroupFromIntermediateFile(intermediateFile))
                    .build();

        } catch (DataloadGeneratorException e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/generateDataload")
    public Response generateDataloadFromTbsPatternSavedTEntityList(List<TbsPatternSavedTEntity> tbsPatternSavedTEntities) {
        try {
            buildDataloadFromTbsPatternSavedEntity(tbsPatternSavedTEntities);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Dataload generated!")
                    .build();

        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getGroupsList")
    public Response retrieveGroupsList() {
        try {
            List<GroupType> groupTypesList = TbsOrmBuilder.getTbsOrmImplementation().getGroupTypeList();
            GroupTypeWrap groupTypeWrap = new GroupTypeWrap();
            List<GroupTypeWrap> groupTypeWrapList = new ArrayList<>();

            for(GroupType groupType : groupTypesList){
                groupTypeWrap.setName(groupType.getName());
                groupTypeWrap.setOrder(groupType.getOrder());
                groupTypeWrap.setStates(groupType.getStates());
                groupTypeWrap.setStates(groupType.getStates());
                groupTypeWrap.setGroupXmlString(buildStringXmlFromGroupType(groupType));
                groupTypeWrapList.add(groupTypeWrap);
            }
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity(groupTypeWrapList)
                    .build();

        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getActionsList")
    public Response retrieveActionsList() {
        try {
            List<ActionType> actionTypes = TbsOrmBuilder.getTbsOrmImplementation().getActionTypeList();
            for(ActionType actionType : actionTypes){
                actionType.setTDSynchQueue(buildStringXmlFromActionType(actionType));
            }
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity(actionTypes)
                    .build();

        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getWorkflowsList")
    public Response retrieveWorkflowTemplateList() {
        try {
            List<WorkflowTemplateType> workflowTemplateTypes = TbsOrmBuilder.getTbsOrmImplementation().getWorkflowsTemplateList();
            for(WorkflowTemplateType templateType : workflowTemplateTypes){
                templateType.setExecutionNode(buildStringXmlFromWorkflowTemplate(templateType));
            }
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity(workflowTemplateTypes)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/editGroup")
    public Response editGroup(String groupXml) {
        System.out.println("Edit Group method called! Group Xml [" + groupXml + "]");
        try {
            GroupType groupType = getGroupTypeObjectFromXmlString(groupXml);
            System.out.println("Updating object on database");
            TbsOrmBuilder.getTbsOrmImplementation().updateGroupType(groupType);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Group [" + groupType.getName() + "] updated!")
                    .build();

        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Exception", e.getMessage())
                    .status(400)
                    .build();
        } catch (Exception e){
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/editAction")
    public Response editAction(String actionTypeXml) {
        System.out.println("Edit Action method called! ActionXml [" + actionTypeXml + "]");
        try {
//            addNameSpaceToIncomingString(actionTypeXml);
            ActionType actionType = buildActionTypeObjectFromXmlString(actionTypeXml);
            System.out.println("Updating object on database");
            TbsOrmBuilder.getTbsOrmImplementation().updateActionType(actionType);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("ActionType [" + actionType.getName() + "] updated!")
                    .build();

        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Exception", e.getMessage())
                    .status(400)
                    .build();
        } catch (Exception e){
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }


    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/deleteAction")
    public Response deleteAction(String actionTypeXml) {
        System.out.println("Delete Action method called! ActionXml [" + actionTypeXml + "]");
        try {
            ActionType actionType = buildActionTypeObjectFromXmlString(actionTypeXml);
            System.out.println("Deleting object on database");
            TbsOrmBuilder.getTbsOrmImplementation().deleteActionType(actionType);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("ActionType [" + actionType.getName() + "] deleted!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/deleteWorkflow")
    public Response deleteWorkflow(String workflowXml) {
        System.out.println("Delete Workflow method called! WorkflowXml [" + workflowXml + "]");
        try {
            WorkflowTemplateType workflowTemplateType = buildWorkflowTypeObjectFromXmlString(workflowXml);
            System.out.println("Deleting object on database");
            TbsOrmBuilder.getTbsOrmImplementation().deleteWorkflowType(workflowTemplateType);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Workflow Template Type [" + workflowTemplateType.getWorkflow() + "] deleted!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/deleteHelpText")
    public Response deleteHelpText(String helpTextXml) {
        System.out.println("Delete HelpText method called! HelpText Xml [" + helpTextXml + "]");
        try {
            HelpTextType helpTextType = buildHelpTextTypeTypeObjectFromXmlString(helpTextXml);
            System.out.println("Deleting object on database");
            TbsOrmBuilder.getTbsOrmImplementation().deleteHelpTextType(helpTextType);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("HelpText Template Type [" + helpTextType.getName() + "] deleted!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/editWorkflow")
    public Response editWorkflow(String workflowXml) {
        System.out.println("Edit workflow method called! WorkflowXml [" + workflowXml + "]");
        try {
            WorkflowTemplateType workflowTemplateType = buildWorkflowTypeObjectFromXmlString(workflowXml);
            System.out.println("Updating object on database");
            TbsOrmBuilder.getTbsOrmImplementation().updateWorkflowTemplateType(workflowTemplateType);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Workflow[" + workflowTemplateType.getWorkflow() + "] updated!")
                    .build();

        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Exception", e.getMessage())
                    .status(400)
                    .build();
        } catch (Exception e){
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/editHelptext")
    public Response editHelpText(String helpTextXml) {
        System.out.println("Edit helpText method called! Helptext [" + helpTextXml + "]");
        try {
            HelpTextType helpTextType = buildHelpTextTypeTypeObjectFromXmlString(helpTextXml);
            System.out.println("Updating object on database");
            TbsOrmBuilder.getTbsOrmImplementation().updateHelpTextType(helpTextType);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Helptext [" + helpTextType.getName() + "] updated!")
                    .build();

        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Exception", e.getMessage())
                    .status(400)
                    .build();
        } catch (Exception e){
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getHelpTextList")
    public Response retrieveHelpTextList() {
        try {
            List<HelpTextType> listHelpText = TbsOrmBuilder.getTbsOrmImplementation().getListHelpText();
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity(listHelpText)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/addActions")
    public Response addActions(String actionsXmlList) {
        try {
            ActionType actionType = buildActionTypeObjectFromXmlString(actionsXmlList);
            List<ActionType> actionTypesList = new ArrayList<>();
            actionTypesList.add(actionType);
            TbsOrmBuilder.getTbsOrmImplementation().saveActions(actionTypesList);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Actions saved!")
                    .build();

        }catch (DataloadGeneratorException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Exception", e.getMessage())
                    .build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .build();
        }

    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/addWorkflows")
    public Response addWorkflows(String workflowTemplateTypes) {
        try {
            String replacedWf = workflowTemplateTypes.replace("workflowTemplate","workflowTemplateType");
            WorkflowTemplateType workflowTemplateType = buildWorkflowTypeObjectFromXmlString(replacedWf);
            List<WorkflowTemplateType> workflowTemplateTypeList = new ArrayList<>();
            workflowTemplateTypeList.add(workflowTemplateType);
            TbsOrmBuilder.getTbsOrmImplementation().saveWorkflows(workflowTemplateTypeList);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity(workflowTemplateType.getWorkflow())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/addHelptext")
    public Response addHelpTexts(String helpTextStringXml) {
        try {
            String replacedHelpText = helpTextStringXml.replace("helpText","helpTextType");
            HelpTextType helpTextType = buildHelpTextTypeTypeObjectFromXmlString(replacedHelpText);
            List<HelpTextType> helpTextTemplateTypeList = new ArrayList<>();
            helpTextTemplateTypeList.add(helpTextType);
            TbsOrmBuilder.getTbsOrmImplementation().saveHelpText(helpTextTemplateTypeList);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity(helpTextType.getName())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getActionsConfiguration")
    public Response getActionsConfiguration() {
        try {
            TimeUnit.SECONDS.sleep(5);
            List actionListDetails = getActionListDetails(getConfigurationFile().getActionDetailsPathFile());
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity(actionListDetails)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/refreshActionsDetailsConfiguration")
    public Response refreshtActionsConfiguration() {
        try {
            System.out.println("Refresh Action Details");
            TbsOrmBuilder.getTbsOrmImplementation().refreshActionsDetailsConfiguration();
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Actions details refreshed!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/actionsDetailsRestoreConfiguration")
    public Response restoreActionsConfiguration() {
        try {
            System.out.println("Restore Default Action Details");
            TbsOrmBuilder.getTbsOrmImplementation().restoreDefaultActionsDetailsConfiguration();
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Actions details restored to default configuration!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/saveActionsConfiguration")
    public Response saveActionsConfiguration(List<ActionDetails> actionDetails) {

        try {
            if(actionDetails == null || actionDetails.size() == 0){
                throw new DataloadGeneratorException("Input file is null or Empty");
            }
            TbsOrmBuilder.getTbsOrmImplementation().saveActionConfiguration(actionDetails);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Action Details list saved with success")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }


    @Override
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/deleteAllActions")
    public Response deleteAllActions() {
        System.out.println("Delete All Action method called!");
        try {
            System.out.println("Deleting object on database");
            TbsOrmBuilder.getTbsOrmImplementation().deleteAllActionType();
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("ActionType 'All' deleted!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/deleteAllWorkflows")
    public Response deleteAllWorkflows() {
        System.out.println("Delete All Workflows method called!");
        try {
            System.out.println("Deleting object on database");
            TbsOrmBuilder.getTbsOrmImplementation().deleteAllWorkflows();
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Workflows 'All' deleted!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/deleteAllHelptext")
    public Response deleteAllHelpTexts() {
        System.out.println("Delete All HelpText method called!");
        try {
            System.out.println("Deleting object on database");
            TbsOrmBuilder.getTbsOrmImplementation().deleteAllHelpText();
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Workflows 'All' deleted!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/uploadGroupsFromPath")
    public Response uploadGroupsFromPath(String dataloadXml) {
        try {
            if(dataloadXml == null || EMPTY_STRING.equalsIgnoreCase(dataloadXml)){
                throw new DataloadGeneratorException("Impossible to upload Groups. The path selected is empty or NULL");
            }
            TbsOrmBuilder.getTbsOrmImplementation().importGroupsFromPath(dataloadXml);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Groups uploaded!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/uploadActionsFromPath")
    public Response uploadActionsFromPath(String dataloadXml) {

        try {
            if(dataloadXml == null || EMPTY_STRING.equalsIgnoreCase(dataloadXml)){
                throw new DataloadGeneratorException("Impossible to upload Actions. The path selected is empty or NULL");
            }
            TbsOrmBuilder.getTbsOrmImplementation().importActionsFromPath(dataloadXml);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Actions uploaded!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/uploadWorkflowsFromPath")
    public Response uploadWorkflowsFromPath(String dataloadXml) {
        try {
            if(dataloadXml == null || EMPTY_STRING.equalsIgnoreCase(dataloadXml)){
                throw new DataloadGeneratorException("Impossible to upload Workflows. The file is empty or null");
            }
            TbsOrmBuilder.getTbsOrmImplementation().importWorkflowsFromPath(dataloadXml);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("Workflows uploaded!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/uploadHelpTextFromPath")
    public Response uploadHelpTExtFromPath(String dataloadXml) {
        try {
            if(dataloadXml == null || EMPTY_STRING.equalsIgnoreCase(dataloadXml)){
                throw new DataloadGeneratorException("Impossible to upload HelpText. The file is empty or null");
            }
            TbsOrmBuilder.getTbsOrmImplementation().importHelpTextFromPath(dataloadXml);
            return Response
                    .status(Response.Status.OK)
                    .status(200)
                    .entity("HelpText uploaded!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Exception", e.getMessage())
                    .status(500)
                    .build();
        }
    }

//    @Override
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/deleteGroupFromDataload")
//    public Response deleteGroupFromDataload(String path, String groupNameToRemove) {
//        try {
//            System.out.println("Deleting group [" + groupNameToRemove + "] from dataload");
//            removeGroupFromTD(path, groupNameToRemove);
//            return Response
//                    .status(Response.Status.OK)
//                    .status(200)
//                    .entity("ActionType 'All' deleted!")
//                    .build();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response
//                    .status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .header("Exception", e.getMessage())
//                    .status(500)
//                    .build();
//        }
//    }

    private void checkIntermediateFile(String intermediateFile) throws DataloadGeneratorException {
        if(intermediateFile == null || EMPTY_STRING.equalsIgnoreCase(intermediateFile)){
            throw new DataloadGeneratorException("Error - Intermediate file give as input is NULL or EMPTY");
        }
    }
}

