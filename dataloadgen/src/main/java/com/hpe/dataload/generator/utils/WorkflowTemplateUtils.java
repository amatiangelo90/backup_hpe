package com.hpe.dataload.generator.utils;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;

import java.util.List;
import java.util.Map;

import static com.hpe.dataload.generator.utils.DataloadGenUtils.getConfigurationFile;
import static com.hpe.dataload.generator.utils.DataloadGenUtils.writeObjectOnFile;

public class WorkflowTemplateUtils {

    private static final String TO_REPLACE_WORKFLOW_NAME            = "TO_REPLACE_WORKFLOW_NAME";
    private static final String TO_REPLACE_SOL_NAME                 = "TO_REPLACE_SOL_NAME";
    private static final String TO_REPLACE_GROUP_NAME               = "TO_REPLACE_GROUP_NAME";
    private static final String TO_REPLACE_STATE_VECTOR_VALUE       = "TO_REPLACE_STATE_VECTOR_VALUE";
    private static final String XML_EXT                             = ".xml";

    public static String buildWorkflowToDriveProcessThroughtGroups(String wfName,
                                                                   String solName,
                                                                   String stateVectorName,
                                                                   String groupName){
        String workFlowTemplateString = getWorkFlowTemplateForNextGroupActions();
        workFlowTemplateString = workFlowTemplateString.replace(TO_REPLACE_WORKFLOW_NAME, wfName);
        workFlowTemplateString = workFlowTemplateString.replace(TO_REPLACE_SOL_NAME, solName);
        workFlowTemplateString = workFlowTemplateString.replace(TO_REPLACE_STATE_VECTOR_VALUE, stateVectorName);
        workFlowTemplateString = workFlowTemplateString.replace(TO_REPLACE_GROUP_NAME, groupName);

        return workFlowTemplateString;
    }

    public static void saveWfDriveGroupAsXmlFile(String fileToSave, String wfName, String solName) throws DataloadGeneratorException {
        String fileName = wfName + XML_EXT;
        writeObjectOnFile(fileToSave, getConfigurationFile().getPATH_GENERATED_SAVE()  + fileName);
    }

    public static String getWorkFlowTemplateForNextGroupActions(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE Workflow SYSTEM \"workflow.dtd\">\n" +
                "\n" +
                "<Workflow Init-On-Startup=\"false\" Unique=\"false\" auditEnabled=\"true\" auditStateChangeEvent=\"false\" autoAuditEnabled=\"true\" disablePersistence=\"false\" statEnabled=\"true\">\n" +
                "   <Name>TO_REPLACE_WORKFLOW_NAME</Name>\n" +
                "   <Solution>TO_REPLACE_SOL_NAME</Solution>\n" +
                "   <Start-Node>GetInput</Start-Node>\n" +
                "   <Nodes>\n" +
                "      <Process-Node disablePersistence=\"true\">\n" +
                "         <Name>GetInput</Name>\n" +
                "         <Action>\n" +
                "            <Class-Name>com.hp.ov.activator.mwfm.component.builtin.XMLMapper</Class-Name>\n" +
                "            <Param name=\"ignore_missing_tags\" value=\"true\"/>\n" +
                "            <Param name=\"input\" value=\"/m:msg/m:body/m:Parameters/m:Parameter/m:value\"/>\n" +
                "            <Param name=\"validate\" value=\"false\"/>\n" +
                "            <Param name=\"xml_var\" value=\"message_data\"/>\n" +
                "         </Action>\n" +
                "         <Next-Node>Log Clean Vector</Next-Node>\n" +
                "      </Process-Node>\n" +
                "      <Process-Node disablePersistence=\"true\">\n" +
                "         <Name>Log Clean Vector</Name>\n" +
                "         <Action>\n" +
                "            <Class-Name>com.hp.ov.activator.mwfm.component.builtin.Log</Class-Name>\n" +
                "            <Param name=\"component_name\" value=\"variable:WORKFLOW_NAME\"/>\n" +
                "            <Param name=\"log_level\" value=\"INFORMATIVE\"/>\n" +
                "            <Param name=\"log_message\" value=\"Drive Process to TO_REPLACE_GROUP_NAME Group\"/>\n" +
                "         </Action>\n" +
                "         <Next-Node>VariableMapper</Next-Node>\n" +
                "      </Process-Node>\n" +
                "      <Process-Node disablePersistence=\"true\">\n" +
                "         <Name>VariableMapper</Name>\n" +
                "         <Action>\n" +
                "            <Class-Name>com.hp.ov.activator.mwfm.component.builtin.VariableMapper</Class-Name>\n" +
                "            <Param name=\"response_string\" value=\"&lt;Parameters&gt; \t&lt;Parameter&gt; \t\t&lt;attribute&gt;TO_REPLACE_STATE_VECTOR_VALUE&lt;/attribute&gt; \t\t&lt;value&gt;TO_REPLACE_GROUP_NAME&lt;/value&gt; \t\t&lt;type&gt;String&lt;/type&gt; \t&lt;/Parameter&gt; &lt;/Parameters&gt;\"/>\n" +
                "         </Action>\n" +
                "      </Process-Node>\n" +
                "   </Nodes>\n" +
                "   <Case-Packet>\n" +
                "      <Variable name=\"ANNOTATION\" type=\"String\"/>\n" +
                "      <Variable name=\"BREAK_POINT\" type=\"String\"/>\n" +
                "      <Variable name=\"DEADLOCK\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"DEFAULT_ROLE\" type=\"String\"/>\n" +
                "      <Variable name=\"EMPTY_STRING\" type=\"String\"/>\n" +
                "      <Variable name=\"ETC\" type=\"String\"/>\n" +
                "      <Variable name=\"EX_STEP_NAME\" type=\"String\"/>\n" +
                "      <Variable name=\"FILE_URL_PREFIX\" type=\"String\"/>\n" +
                "      <Variable name=\"HOST_NAME\" type=\"String\"/>\n" +
                "      <Variable name=\"JOB_ID\" type=\"Integer\"/>\n" +
                "      <Variable name=\"KILLED\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"KILL_ROLE\" type=\"String\"/>\n" +
                "      <Variable name=\"MACRO_OUTPUT\" type=\"Object\"/>\n" +
                "      <Variable name=\"MASTER_CHILD_JOBS\" type=\"Object\"/>\n" +
                "      <Variable name=\"MUTEX\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"NULL\" type=\"Object\"/>\n" +
                "      <Variable name=\"PRIORITY\" type=\"Integer\"/>\n" +
                "      <Variable name=\"RESERVATIONS\" type=\"Object\"/>\n" +
                "      <Variable name=\"RET_TEXT\" type=\"String\"/>\n" +
                "      <Variable name=\"RET_VALUE\" type=\"Integer\"/>\n" +
                "      <Variable name=\"RUNTIME\" type=\"Object\"/>\n" +
                "      <Variable name=\"SCHEDULED_INFO\" type=\"Object\"/>\n" +
                "      <Variable name=\"SERVICE_ID\" type=\"String\"/>\n" +
                "      <Variable name=\"SOLUTION_ETC\" type=\"String\"/>\n" +
                "      <Variable name=\"SOLUTION_NAME\" type=\"String\"/>\n" +
                "      <Variable name=\"SOLUTION_VAR\" type=\"String\"/>\n" +
                "      <Variable name=\"SOM_INSTANCE\" type=\"Object\"/>\n" +
                "      <Variable name=\"SOM_PATH\" type=\"String\"/>\n" +
                "      <Variable name=\"SOR_ID\" type=\"Integer\"/>\n" +
                "      <Variable name=\"START_ROLE\" type=\"String\"/>\n" +
                "      <Variable name=\"START_TIME\" type=\"Integer\"/>\n" +
                "      <Variable name=\"STATUS\" type=\"String\"/>\n" +
                "      <Variable name=\"STEP_NAME\" type=\"String\"/>\n" +
                "      <Variable name=\"SUBSTEP\" type=\"String\"/>\n" +
                "      <Variable name=\"THROW_EXCEP\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"TIMEOUT\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"TRACE_ROLE\" type=\"String\"/>\n" +
                "      <Variable name=\"UNIQUE_WORKFLOW\" type=\"Integer\"/>\n" +
                "      <Variable name=\"VAR\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_EXCEPTION\" type=\"Object\"/>\n" +
                "      <Variable name=\"WORKFLOW_EXECUTION_STATUS\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_NAME\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_ORDER_ID\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_STATE\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_TYPE\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_VERSION\" type=\"Integer\"/>\n" +
                "      <Variable name=\"accountId\" type=\"String\"/>\n" +
                "      <Variable name=\"action_name\" type=\"String\"/>\n" +
                "      <Variable name=\"activation_description\" type=\"String\"/>\n" +
                "      <Variable name=\"activation_major_code\" type=\"Integer\"/>\n" +
                "      <Variable name=\"activation_minor_code\" type=\"Integer\"/>\n" +
                "      <Variable name=\"anotherResult\" type=\"String\"/>\n" +
                "      <Variable name=\"diagnostics\" type=\"String\"/>\n" +
                "      <Variable name=\"input\" type=\"String\"/>\n" +
                "      <Variable name=\"log_level\" type=\"String\"/>\n" +
                "      <Variable name=\"log_manager\" type=\"String\"/>\n" +
                "      <Variable name=\"major_code\" type=\"String\"/>\n" +
                "      <Variable name=\"major_description\" type=\"String\"/>\n" +
                "      <Variable name=\"message_data\" type=\"String\"/>\n" +
                "      <Variable name=\"message_url\" type=\"String\"/>\n" +
                "      <Variable name=\"minor_code\" type=\"String\"/>\n" +
                "      <Variable name=\"minor_description\" type=\"String\"/>\n" +
                "      <Variable name=\"module_name\" type=\"String\"/>\n" +
                "      <Variable name=\"parent_job_id\" type=\"Integer\"/>\n" +
                "      <Variable name=\"problem_name\" type=\"String\"/>\n" +
                "      <Variable name=\"response_string\" type=\"String\"/>\n" +
                "      <Variable name=\"services\" type=\"Object\"/>\n" +
                "      <Variable name=\"skip_activation\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"sync\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"xmlList\" type=\"String\"/>\n" +
                "   </Case-Packet>\n" +
                "   <Initial-Case-Packet>\n" +
                "      <Variable-Value name=\"FILE_URL_PREFIX\" value=\"file:///\"/>\n" +
                "      <Variable-Value name=\"SOLUTION_NAME\" value=\"TO_REPLACE_SOL_NAME\"/>\n" +
                "      <Variable-Value name=\"WORKFLOW_NAME\" value=\"TO_REPLACE_WORKFLOW_NAME\"/>\n" +
                "      <Variable-Value name=\"WORKFLOW_VERSION\" value=\"-1\"/>\n" +
                "      <Variable-Value name=\"diagnostics\" value=\"looooooooooooooooog string\"/>\n" +
                "      <Variable-Value name=\"major_code\" value=\"200\"/>\n" +
                "      <Variable-Value name=\"major_description\" value=\"ok execution\"/>\n" +
                "      <Variable-Value name=\"response_string\" value=\"&lt;Parameters&gt;&lt;Parameter&gt;&lt;attribute&gt;result&lt;/attribute&gt;&lt;value&gt;%inpu%&lt;/value&gt;&lt;type&gt;String&lt;/type&gt;&lt;/Parameter&gt;&lt;/Parameters&gt;\"/>\n" +
                "   </Initial-Case-Packet>\n" +
                "   <Contract>\n" +
                "      <Input mandatory=\"7\">\n" +
                "         <Var>parent_job_id</Var>\n" +
                "         <Var>message_data</Var>\n" +
                "         <Var>problem_name</Var>\n" +
                "         <Var>action_name</Var>\n" +
                "         <Var>log_manager</Var>\n" +
                "         <Var>log_level</Var>\n" +
                "         <Var>sync</Var>\n" +
                "      </Input>\n" +
                "      <Output>\n" +
                "         <Var>major_code</Var>\n" +
                "         <Var>minor_code</Var>\n" +
                "         <Var>major_description</Var>\n" +
                "         <Var>minor_description</Var>\n" +
                "         <Var>diagnostics</Var>\n" +
                "         <Var>response_string</Var>\n" +
                "      </Output>\n" +
                "   </Contract>\n" +
                "   <Coordinates>\n" +
                "      <Position>\n" +
                "         <Name>GetInput</Name>\n" +
                "         <X>530</X>\n" +
                "         <Y>149</Y>\n" +
                "         <Width>61</Width>\n" +
                "         <Height>48</Height>\n" +
                "      </Position>\n" +
                "      <Position>\n" +
                "         <Name>Log Clean Vector</Name>\n" +
                "         <X>611</X>\n" +
                "         <Y>149</Y>\n" +
                "         <Width>107</Width>\n" +
                "         <Height>48</Height>\n" +
                "      </Position>\n" +
                "      <Position>\n" +
                "         <Name>VariableMapper</Name>\n" +
                "         <X>739</X>\n" +
                "         <Y>149</Y>\n" +
                "         <Width>100</Width>\n" +
                "         <Height>48</Height>\n" +
                "      </Position>\n" +
                "      <Arrows>\n" +
                "         <Name>GetInput</Name>\n" +
                "         <True-Arrow>\n" +
                "            <Type>0</Type>\n" +
                "            <DeltaX>0</DeltaX>\n" +
                "            <DeltaY>0</DeltaY>\n" +
                "         </True-Arrow>\n" +
                "         <False-Arrow>\n" +
                "            <Type>0</Type>\n" +
                "            <DeltaX>0</DeltaX>\n" +
                "            <DeltaY>0</DeltaY>\n" +
                "         </False-Arrow>\n" +
                "      </Arrows>\n" +
                "      <Arrows>\n" +
                "         <Name>Log Clean Vector</Name>\n" +
                "         <True-Arrow>\n" +
                "            <Type>0</Type>\n" +
                "            <DeltaX>0</DeltaX>\n" +
                "            <DeltaY>0</DeltaY>\n" +
                "         </True-Arrow>\n" +
                "         <False-Arrow>\n" +
                "            <Type>0</Type>\n" +
                "            <DeltaX>0</DeltaX>\n" +
                "            <DeltaY>0</DeltaY>\n" +
                "         </False-Arrow>\n" +
                "      </Arrows>\n" +
                "      <Arrows>\n" +
                "         <Name>VariableMapper</Name>\n" +
                "         <True-Arrow>\n" +
                "            <Type>0</Type>\n" +
                "            <DeltaX>0</DeltaX>\n" +
                "            <DeltaY>0</DeltaY>\n" +
                "         </True-Arrow>\n" +
                "         <False-Arrow>\n" +
                "            <Type>0</Type>\n" +
                "            <DeltaX>0</DeltaX>\n" +
                "            <DeltaY>0</DeltaY>\n" +
                "         </False-Arrow>\n" +
                "      </Arrows>\n" +
                "   </Coordinates>\n" +
                "</Workflow>";
    }


    public static void buildAndWriteOnFileSystemWfFileForMClearVector(String workflowName,
                                                                      List<String> fullStateVectorList,
                                                                      String solution,
                                                                      Map<String, String> stateVectorListDriver) throws DataloadGeneratorException {
        String stateVectorListToReplace = buildStateVectorList(fullStateVectorList, stateVectorListDriver);
        String workflowMClearVector = getWorkFlowTemplateForMClearVectorAction();

        workflowMClearVector = workflowMClearVector.replace("TO_REPLACE_WF_NAME", workflowName);
        workflowMClearVector = workflowMClearVector.replace("TO_REPLACE_SOLUTION_NAME", solution);
        workflowMClearVector = workflowMClearVector.replace("TO_REPLACE_STATE_VECTOR", stateVectorListToReplace);

        saveWfDriveGroupAsXmlFile(workflowMClearVector, workflowName, solution);
    }

    private static String buildStateVectorList(List<String> fullStateVectorList,
                                               Map<String, String> stateVectorListDriver){

        StringBuilder builderStateVector = new StringBuilder("&lt;Parameters&gt;");
        for(String currentStateVector : fullStateVectorList){
            if(stateVectorListDriver == null){
                builderStateVector.append(getOutputWfStructureToCleanVector(currentStateVector, "-"));
            }else{
                builderStateVector.append(getOutputWfStructureToCleanVector(currentStateVector, stateVectorListDriver.get(currentStateVector)));
            }
        }
        builderStateVector.append("&lt;/Parameters&gt;");
        return builderStateVector.toString();
    }

    private static String getOutputWfStructureToCleanVector(String stateVectorName, String stateVectorValue){
        String structure = "&lt;Parameter&gt;\n" +
                "\t\t&lt;attribute&gt;TO_REPLACE&lt;/attribute&gt;\n" +
                "\t\t&lt;value&gt;" + stateVectorValue + "&lt;/value&gt;\n" +
                "\t\t&lt;type&gt;String&lt;/type&gt;\n" +
                "\t&lt;/Parameter&gt;";
        structure = structure.replace("TO_REPLACE", stateVectorName);
        return structure;
    }

    public static String getWorkFlowTemplateForMClearVectorAction(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE Workflow SYSTEM \"workflow.dtd\">\n" +
                "\n" +
                "<Workflow Init-On-Startup=\"false\" Unique=\"false\" auditEnabled=\"true\" auditStateChangeEvent=\"false\" autoAuditEnabled=\"true\" disablePersistence=\"false\" statEnabled=\"true\">\n" +
                "   <Name>TO_REPLACE_WF_NAME</Name>\n" +
                "   <Solution>TO_REPLACE_SOLUTION_NAME</Solution>\n" +
                "   <Start-Node>GetInput</Start-Node>\n" +
                "   <Nodes>\n" +
                "      <Process-Node disablePersistence=\"true\">\n" +
                "         <Name>GetInput</Name>\n" +
                "         <Action>\n" +
                "            <Class-Name>com.hp.ov.activator.mwfm.component.builtin.XMLMapper</Class-Name>\n" +
                "            <Param name=\"ignore_missing_tags\" value=\"true\"/>\n" +
                "            <Param name=\"input\" value=\"/m:msg/m:body/m:Parameters/m:Parameter/m:value\"/>\n" +
                "            <Param name=\"validate\" value=\"false\"/>\n" +
                "            <Param name=\"xml_var\" value=\"message_data\"/>\n" +
                "         </Action>\n" +
                "         <Next-Node>Log Clean Vector</Next-Node>\n" +
                "      </Process-Node>\n" +
                "      <Process-Node disablePersistence=\"true\">\n" +
                "         <Name>Log Clean Vector</Name>\n" +
                "         <Action>\n" +
                "            <Class-Name>com.hp.ov.activator.mwfm.component.builtin.Log</Class-Name>\n" +
                "            <Param name=\"component_name\" value=\"variable:WORKFLOW_NAME\"/>\n" +
                "            <Param name=\"log_level\" value=\"INFORMATIVE\"/>\n" +
                "            <Param name=\"log_message\" value=\"Reset StateIndexes - The TO_REPLACE_SOLUTION_NAME process will start from begin..\"/>\n" +
                "         </Action>\n" +
                "         <Next-Node>VariableMapper</Next-Node>\n" +
                "      </Process-Node>\n" +
                "      <Process-Node disablePersistence=\"true\">\n" +
                "         <Name>VariableMapper</Name>\n" +
                "         <Action>\n" +
                "            <Class-Name>com.hp.ov.activator.mwfm.component.builtin.VariableMapper</Class-Name>\n" +
                "            <Param name=\"response_string\" value=\"TO_REPLACE_STATE_VECTOR\"/>\n" +
                "         </Action>\n" +
                "      </Process-Node>\n" +
                "   </Nodes>\n" +
                "   <Case-Packet>\n" +
                "      <Variable name=\"ANNOTATION\" type=\"String\"/>\n" +
                "      <Variable name=\"BREAK_POINT\" type=\"String\"/>\n" +
                "      <Variable name=\"DEADLOCK\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"DEFAULT_ROLE\" type=\"String\"/>\n" +
                "      <Variable name=\"DISABLE_PERSISTENCE\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"EMPTY_STRING\" type=\"String\"/>\n" +
                "      <Variable name=\"ETC\" type=\"String\"/>\n" +
                "      <Variable name=\"EX_STEP_NAME\" type=\"String\"/>\n" +
                "      <Variable name=\"FILE_URL_PREFIX\" type=\"String\"/>\n" +
                "      <Variable name=\"HOST_NAME\" type=\"String\"/>\n" +
                "      <Variable name=\"JOB_ID\" type=\"Integer\"/>\n" +
                "      <Variable name=\"KILLED\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"KILL_ROLE\" type=\"String\"/>\n" +
                "      <Variable name=\"MACRO_OUTPUT\" type=\"Object\"/>\n" +
                "      <Variable name=\"MASTER_CHILD_JOBS\" type=\"Object\"/>\n" +
                "      <Variable name=\"MUTEX\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"NULL\" type=\"Object\"/>\n" +
                "      <Variable name=\"PRIORITY\" type=\"Integer\"/>\n" +
                "      <Variable name=\"RESERVATIONS\" type=\"Object\"/>\n" +
                "      <Variable name=\"RET_TEXT\" type=\"String\"/>\n" +
                "      <Variable name=\"RET_VALUE\" type=\"Integer\"/>\n" +
                "      <Variable name=\"RUNTIME\" type=\"Object\"/>\n" +
                "      <Variable name=\"SCHEDULED_INFO\" type=\"Object\"/>\n" +
                "      <Variable name=\"SERVICE_ID\" type=\"String\"/>\n" +
                "      <Variable name=\"SOLUTION_ETC\" type=\"String\"/>\n" +
                "      <Variable name=\"SOLUTION_NAME\" type=\"String\"/>\n" +
                "      <Variable name=\"SOLUTION_VAR\" type=\"String\"/>\n" +
                "      <Variable name=\"SOM_INSTANCE\" type=\"Object\"/>\n" +
                "      <Variable name=\"SOM_PATH\" type=\"String\"/>\n" +
                "      <Variable name=\"SOR_ID\" type=\"Integer\"/>\n" +
                "      <Variable name=\"START_ROLE\" type=\"String\"/>\n" +
                "      <Variable name=\"START_TIME\" type=\"Integer\"/>\n" +
                "      <Variable name=\"STATUS\" type=\"String\"/>\n" +
                "      <Variable name=\"STEP_NAME\" type=\"String\"/>\n" +
                "      <Variable name=\"SUBSTEP\" type=\"String\"/>\n" +
                "      <Variable name=\"THROW_EXCEP\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"TIMEOUT\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"TRACE_ROLE\" type=\"String\"/>\n" +
                "      <Variable name=\"UNIQUE_WORKFLOW\" type=\"Integer\"/>\n" +
                "      <Variable name=\"VAR\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_EXCEPTION\" type=\"Object\"/>\n" +
                "      <Variable name=\"WORKFLOW_EXECUTION_STATUS\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_NAME\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_ORDER_ID\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_STATE\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_TYPE\" type=\"String\"/>\n" +
                "      <Variable name=\"WORKFLOW_VERSION\" type=\"Integer\"/>\n" +
                "      <Variable name=\"accountId\" type=\"String\"/>\n" +
                "      <Variable name=\"action_name\" type=\"String\"/>\n" +
                "      <Variable name=\"activation_description\" type=\"String\"/>\n" +
                "      <Variable name=\"activation_major_code\" type=\"Integer\"/>\n" +
                "      <Variable name=\"activation_minor_code\" type=\"Integer\"/>\n" +
                "      <Variable name=\"anotherResult\" type=\"String\"/>\n" +
                "      <Variable name=\"diagnostics\" type=\"String\"/>\n" +
                "      <Variable name=\"input\" type=\"String\"/>\n" +
                "      <Variable name=\"log_level\" type=\"String\"/>\n" +
                "      <Variable name=\"log_manager\" type=\"String\"/>\n" +
                "      <Variable name=\"major_code\" type=\"String\"/>\n" +
                "      <Variable name=\"major_description\" type=\"String\"/>\n" +
                "      <Variable name=\"message_data\" type=\"String\"/>\n" +
                "      <Variable name=\"message_url\" type=\"String\"/>\n" +
                "      <Variable name=\"minor_code\" type=\"String\"/>\n" +
                "      <Variable name=\"minor_description\" type=\"String\"/>\n" +
                "      <Variable name=\"module_name\" type=\"String\"/>\n" +
                "      <Variable name=\"parent_job_id\" type=\"Integer\"/>\n" +
                "      <Variable name=\"problem_name\" type=\"String\"/>\n" +
                "      <Variable name=\"response_string\" type=\"String\"/>\n" +
                "      <Variable name=\"services\" type=\"Object\"/>\n" +
                "      <Variable name=\"skip_activation\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"sync\" type=\"Boolean\"/>\n" +
                "      <Variable name=\"xmlList\" type=\"String\"/>\n" +
                "   </Case-Packet>\n" +
                "   <Initial-Case-Packet>\n" +
                "      <Variable-Value name=\"FILE_URL_PREFIX\" value=\"file://\"/>\n" +
                "      <Variable-Value name=\"SOLUTION_NAME\" value=\"TO_REPLACE_SOLUTION_NAME\"/>\n" +
                "      <Variable-Value name=\"WORKFLOW_NAME\" value=\"TO_REPLACE_WF_NAME\"/>\n" +
                "      <Variable-Value name=\"WORKFLOW_VERSION\" value=\"-1\"/>\n" +
                "      <Variable-Value name=\"diagnostics\" value=\"looooooooooooooooog string\"/>\n" +
                "      <Variable-Value name=\"major_code\" value=\"200\"/>\n" +
                "      <Variable-Value name=\"major_description\" value=\"ok execution\"/>\n" +
                "      <Variable-Value name=\"response_string\" value=\"&lt;Parameters&gt;&lt;Parameter&gt;&lt;attribute&gt;result&lt;/attribute&gt;&lt;value&gt;%inpu%&lt;/value&gt;&lt;type&gt;String&lt;/type&gt;&lt;/Parameter&gt;&lt;/Parameters&gt;\"/>\n" +
                "   </Initial-Case-Packet>\n" +
                "   <Contract>\n" +
                "      <Input mandatory=\"7\">\n" +
                "         <Var>parent_job_id</Var>\n" +
                "         <Var>message_data</Var>\n" +
                "         <Var>problem_name</Var>\n" +
                "         <Var>action_name</Var>\n" +
                "         <Var>log_manager</Var>\n" +
                "         <Var>log_level</Var>\n" +
                "         <Var>sync</Var>\n" +
                "      </Input>\n" +
                "      <Output>\n" +
                "         <Var>major_code</Var>\n" +
                "         <Var>minor_code</Var>\n" +
                "         <Var>major_description</Var>\n" +
                "         <Var>minor_description</Var>\n" +
                "         <Var>diagnostics</Var>\n" +
                "         <Var>response_string</Var>\n" +
                "      </Output>\n" +
                "   </Contract>\n" +
                "</Workflow>";
    }
}
