package com.hpe.automatization.test.testSuiteDataloadService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.orm.model.TbsPatternSavedTEntity;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.hpe.dataload.generator.engine.GroupBuilder.buildGroupFromIntermediateFile;
import static com.hpe.dataload.generator.engine.GroupMergeEngine.buildDataloadFromTbsPatternSavedEntity;

public class TestGenerateGroupFromIntermediateFileTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void generateGroupFromSlimFile() throws DataloadGeneratorException {
        String s = buildGroupFromIntermediateFile(getSlimIntermediateFile());
        System.out.println(s);
    }

    @Test
    public void generatebuildDataloadFromTbsPatternSavedEntity() throws DataloadGeneratorException, IOException {
        buildDataloadFromTbsPatternSavedEntity(getPatternSavedEntityFileList());
    }

    @Test
    public void generateGroupFromFatFile() throws DataloadGeneratorException {
        System.out.println(buildGroupFromIntermediateFile(getFatIntermediateFile()));
    }

    @Test(expected = DataloadGeneratorException.class)
    public void generateGroupFromFatFile_EmptyIntermediateFile() throws DataloadGeneratorException {
        System.out.println(buildGroupFromIntermediateFile(""));
    }
    @Test(expected = DataloadGeneratorException.class)
    public void generateGroupFromFatFile_NULLIntermediateFile() throws DataloadGeneratorException {
        System.out.println(buildGroupFromIntermediateFile(null));
    }
    @Test(expected = DataloadGeneratorException.class)
    public void generateGroupFromFatFile_WRONGIntermediateFile() throws DataloadGeneratorException {
        System.out.println(buildGroupFromIntermediateFile(getWrongIntermediateFile()));
    }

    /**
     * Utils test method
     *
     * @return
     * @throws IOException
     */
    private List<TbsPatternSavedTEntity> getPatternSavedEntityFileList() throws IOException {
        List<TbsPatternSavedTEntity> tbsPatternSavedTEntities = new ArrayList<>();
        tbsPatternSavedTEntities.add(getTbsPatternSavedTEntityObject());
        return tbsPatternSavedTEntities;
    }
    private String getFatIntermediateFile() {
        return "<data-set><item><blocks_execution_group><block_execution_flow><block_name>retryOOcs</block_name><match_cond>00</match_cond><block_id>1585145262703</block_id><x_position>474</x_position><y_position>58</y_position></block_execution_flow><block_execution_flow><block_name>sleep</block_name><match_cond>00</match_cond><block_id>1585148399150</block_id><x_position>353</x_position><y_position>240</y_position></block_execution_flow><block_execution_flow><block_name>EXIT</block_name><block_id>1585145264623</block_id><x_position>319</x_position><y_position>373</y_position></block_execution_flow></blocks_execution_group><pattern_output><no_problem_detected>0</no_problem_detected><intervention_required>0</intervention_required><problem_not_solved>0</problem_not_solved><problem_solved>1</problem_solved><massive_disservice>0</massive_disservice></pattern_output><pattern_filter><segment>Dataload_TBS_RES</segment><technology>Rete alta</technology><class_value>2</class_value><service>*</service><is_unique_cpe>*</is_unique_cpe><rg_type>*</rg_type><problem_type>*</problem_type></pattern_filter></item><item><blocks_execution_group><block_execution_flow><block_name>retryOOcs</block_name><match_cond>00</match_cond><block_id>1585145262703</block_id><x_position>474</x_position><y_position>58</y_position></block_execution_flow><block_execution_flow><block_name>sleep</block_name><match_cond>ELSE</match_cond><block_id>1585148399150</block_id><x_position>353</x_position><y_position>240</y_position></block_execution_flow><block_execution_flow><block_name>EXIT</block_name><block_id>1585148399535</block_id><x_position>483</x_position><y_position>335</y_position></block_execution_flow></blocks_execution_group><pattern_output><no_problem_detected>0</no_problem_detected><intervention_required>0</intervention_required><problem_not_solved>0</problem_not_solved><problem_solved>1</problem_solved><massive_disservice>0</massive_disservice></pattern_output><pattern_filter><segment>Dataload_TBS_RES</segment><technology>Rete alta</technology><class_value>2</class_value><service>*</service><is_unique_cpe>*</is_unique_cpe><rg_type>*</rg_type><problem_type>*</problem_type></pattern_filter></item><item><blocks_execution_group><block_execution_flow><block_name>retryOOcs</block_name><match_cond>01</match_cond><block_id>1585145262703</block_id><x_position>474</x_position><y_position>58</y_position></block_execution_flow><block_execution_flow><block_name>sleep</block_name><match_cond>00</match_cond><block_id>1585148399150</block_id><x_position>353</x_position><y_position>240</y_position></block_execution_flow><block_execution_flow><block_name>EXIT</block_name><block_id>1585145264623</block_id><x_position>319</x_position><y_position>373</y_position></block_execution_flow></blocks_execution_group><pattern_output><no_problem_detected>0</no_problem_detected><intervention_required>0</intervention_required><problem_not_solved>0</problem_not_solved><problem_solved>1</problem_solved><massive_disservice>0</massive_disservice></pattern_output><pattern_filter><segment>Dataload_TBS_RES</segment><technology>Rete alta</technology><class_value>2</class_value><service>*</service><is_unique_cpe>*</is_unique_cpe><rg_type>*</rg_type><problem_type>*</problem_type></pattern_filter></item><item><blocks_execution_group><block_execution_flow><block_name>retryOOcs</block_name><match_cond>01</match_cond><block_id>1585145262703</block_id><x_position>474</x_position><y_position>58</y_position></block_execution_flow><block_execution_flow><block_name>sleep</block_name><match_cond>ELSE</match_cond><block_id>1585148399150</block_id><x_position>353</x_position><y_position>240</y_position></block_execution_flow><block_execution_flow><block_name>EXIT</block_name><block_id>1585148399535</block_id><x_position>483</x_position><y_position>335</y_position></block_execution_flow></blocks_execution_group><pattern_output><no_problem_detected>0</no_problem_detected><intervention_required>0</intervention_required><problem_not_solved>0</problem_not_solved><problem_solved>1</problem_solved><massive_disservice>0</massive_disservice></pattern_output><pattern_filter><segment>Dataload_TBS_RES</segment><technology>Rete alta</technology><class_value>2</class_value><service>*</service><is_unique_cpe>*</is_unique_cpe><rg_type>*</rg_type><problem_type>*</problem_type></pattern_filter></item><item><blocks_execution_group><block_execution_flow><block_name>retryOOcs</block_name><match_cond>ELSE</match_cond><block_id>1585145262703</block_id><x_position>474</x_position><y_position>58</y_position></block_execution_flow><block_execution_flow><block_name>EXIT</block_name><block_id>1585145262948</block_id><x_position>604</x_position><y_position>153</y_position></block_execution_flow></blocks_execution_group><pattern_output><no_problem_detected>0</no_problem_detected><intervention_required>0</intervention_required><problem_not_solved>0</problem_not_solved><problem_solved>1</problem_solved><massive_disservice>0</massive_disservice></pattern_output><pattern_filter><segment>Dataload_TBS_RES</segment><technology>Rete alta</technology><class_value>2</class_value><service>*</service><is_unique_cpe>*</is_unique_cpe><rg_type>*</rg_type><problem_type>*</problem_type></pattern_filter></item></data-set>";
    }
    private String getSlimIntermediateFile() {
        return "<tbs-flow xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'><pattern-input><segment>Dataload_TBS_RES</segment><technology>Rete alta</technology><class-value>2</class-value><service>*</service><is-unique-cpe>*</is-unique-cpe><rg-type>*</rg-type><problem-type>*</problem-type></pattern-input><nodes><node><id>1585145262703</id><name>retryOOcs</name><x-pos>474</x-pos><y-pos>58</y-pos></node><node><id>1585145262948</id><name>EXIT</name><x-pos>604</x-pos><y-pos>153</y-pos><pattern-output>00010</pattern-output></node><node><id>1585145264623</id><name>EXIT</name><x-pos>319</x-pos><y-pos>373</y-pos><pattern-output>00010</pattern-output></node><node><id>1585148399150</id><name>sleep</name><x-pos>353</x-pos><y-pos>240</y-pos></node><node><id>1585148399535</id><name>EXIT</name><x-pos>483</x-pos><y-pos>335</y-pos><pattern-output>00010</pattern-output></node></nodes><arches><arch><source>1585145262703</source><target>1585148399150</target><match-condition>00</match-condition></arch><arch><source>1585145262703</source><target>1585148399150</target><match-condition>01</match-condition></arch><arch><source>1585145262703</source><target>1585145262948</target><match-condition>ELSE</match-condition></arch><arch><source>1585148399150</source><target>1585145264623</target><match-condition>00</match-condition></arch><arch><source>1585148399150</source><target>1585148399535</target><match-condition>ELSE</match-condition></arch></arches></tbs-flow>";
    }
    private String getWrongIntermediateFile() {
        return "asas><zz<zxdf</tbs-flow>";
    }

    private TbsPatternSavedTEntity getTbsPatternSavedTEntityObject() throws IOException {
        TbsPatternSavedTEntity tbsPatternSavedTEntity = new TbsPatternSavedTEntity();
        tbsPatternSavedTEntity.setIntermediateXml(getSlimIntermediateFile());
        tbsPatternSavedTEntity.setSegment("Dataload_TBS_RES");
        tbsPatternSavedTEntity.setTechnology("Rete Alta");
        tbsPatternSavedTEntity.setClazz("0");
        tbsPatternSavedTEntity.setOperator("td_admin");
        tbsPatternSavedTEntity.setSolutionName("TBS_SELF");
//        return mapper.readValue(getPatterInputSavedString(), TbsPatternSavedTEntity.class);
        return tbsPatternSavedTEntity;
    }

    private String getPatterInputSavedString(){
        return "{\"clazz\":\"2\",\"isUcpe\":\"N\",\"problemType\":\"DATAGEN\",\"rgType\":\"*\",\"segment\":\"Dataload_TBS_RES\",\"service\":\"UBB BS NGA 100\",\"technology\":\"Rete alta\"," +
                "\"intermediateXml\":" +
                "\t<pattern-input>\n" +
                "\t\t<segment>Dataload_TBS_RES</segment>\n" +
                "\t\t<technology>Rete alta</technology>\n" +
                "\t\t<class-value>2</class-value>\n" +
                "\t\t<service>UBB BS NGA 100</service>\n" +
                "\t\t<is-unique-cpe>N</is-unique-cpe>\n" +
                "\t\t<rg-type>*</rg-type>\n" +
                "\t\t<problem-type>DATAGEN</problem-type>\n" +
                "\t</pattern-input>\n" +
                "\t<nodes>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585145262703</id>\n" +
                "\t\t\t<name>retryOOcs</name>\n" +
                "\t\t\t<x-pos>431</x-pos>\n" +
                "\t\t\t<y-pos>347</y-pos>\n" +
                "\t\t</node>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585145262948</id>\n" +
                "\t\t\t<name>EXIT</name>\n" +
                "\t\t\t<x-pos>561</x-pos>\n" +
                "\t\t\t<y-pos>442</y-pos>\n" +
                "\t\t\t<pattern-output>00010</pattern-output>\n" +
                "\t\t</node>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585145264623</id>\n" +
                "\t\t\t<name>EXIT</name>\n" +
                "\t\t\t<x-pos>177</x-pos>\n" +
                "\t\t\t<y-pos>829</y-pos>\n" +
                "\t\t\t<pattern-output>00010</pattern-output>\n" +
                "\t\t</node>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585148399150</id>\n" +
                "\t\t\t<name>sleep</name>\n" +
                "\t\t\t<x-pos>211</x-pos>\n" +
                "\t\t\t<y-pos>696</y-pos>\n" +
                "\t\t</node>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585148399535</id>\n" +
                "\t\t\t<name>EXIT</name>\n" +
                "\t\t\t<x-pos>341</x-pos>\n" +
                "\t\t\t<y-pos>791</y-pos>\n" +
                "\t\t\t<pattern-output>00010</pattern-output>\n" +
                "\t\t</node>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585228117743</id>\n" +
                "\t\t\t<name>deviceDataAndResetDevice</name>\n" +
                "\t\t\t<x-pos>615</x-pos>\n" +
                "\t\t\t<y-pos>44</y-pos>\n" +
                "\t\t</node>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585228118402</id>\n" +
                "\t\t\t<name>EXIT</name>\n" +
                "\t\t\t<x-pos>745</x-pos>\n" +
                "\t\t\t<y-pos>139</y-pos>\n" +
                "\t\t\t<pattern-output>00010</pattern-output>\n" +
                "\t\t</node>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585248270196</id>\n" +
                "\t\t\t<name>numeroDevice</name>\n" +
                "\t\t\t<x-pos>631</x-pos>\n" +
                "\t\t\t<y-pos>213</y-pos>\n" +
                "\t\t</node>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585248270644</id>\n" +
                "\t\t\t<name>EXIT</name>\n" +
                "\t\t\t<x-pos>761</x-pos>\n" +
                "\t\t\t<y-pos>308</y-pos>\n" +
                "\t\t\t<pattern-output>00010</pattern-output>\n" +
                "\t\t</node>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585248271476</id>\n" +
                "\t\t\t<name>numeroDevice</name>\n" +
                "\t\t\t<x-pos>467</x-pos>\n" +
                "\t\t\t<y-pos>472</y-pos>\n" +
                "\t\t</node>\n" +
                "\t\t<node>\n" +
                "\t\t\t<id>1585248272221</id>\n" +
                "\t\t\t<name>EXIT</name>\n" +
                "\t\t\t<x-pos>597</x-pos>\n" +
                "\t\t\t<y-pos>567</y-pos>\n" +
                "\t\t\t<pattern-output>00010</pattern-output>\n" +
                "\t\t</node>\n" +
                "\t</nodes>\n" +
                "\t<arches>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585145262703</source>\n" +
                "\t\t\t<target>1585148399150</target>\n" +
                "\t\t\t<match-condition>00</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585145262703</source>\n" +
                "\t\t\t<target>1585248271476</target>\n" +
                "\t\t\t<match-condition>01</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585145262703</source>\n" +
                "\t\t\t<target>1585145262948</target>\n" +
                "\t\t\t<match-condition>ELSE</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585148399150</source>\n" +
                "\t\t\t<target>1585145264623</target>\n" +
                "\t\t\t<match-condition>00</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585148399150</source>\n" +
                "\t\t\t<target>1585148399535</target>\n" +
                "\t\t\t<match-condition>ELSE</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585228117743</source>\n" +
                "\t\t\t<target>1585145262703</target>\n" +
                "\t\t\t<match-condition>00</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585228117743</source>\n" +
                "\t\t\t<target>1585248270196</target>\n" +
                "\t\t\t<match-condition>ERR</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585228117743</source>\n" +
                "\t\t\t<target>1585228118402</target>\n" +
                "\t\t\t<match-condition>ELSE</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585248270196</source>\n" +
                "\t\t\t<target>1585145262703</target>\n" +
                "\t\t\t<match-condition>00</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585248270196</source>\n" +
                "\t\t\t<target>1585145264623</target>\n" +
                "\t\t\t<match-condition>01</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585248270196</source>\n" +
                "\t\t\t<target>1585248270644</target>\n" +
                "\t\t\t<match-condition>ELSE</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585248271476</source>\n" +
                "\t\t\t<target>1585148399150</target>\n" +
                "\t\t\t<match-condition>00</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585248271476</source>\n" +
                "\t\t\t<target>1585148399150</target>\n" +
                "\t\t\t<match-condition>01</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t\t<arch>\n" +
                "\t\t\t<source>1585248271476</source>\n" +
                "\t\t\t<target>1585248272221</target>\n" +
                "\t\t\t<match-condition>ELSE</match-condition>\n" +
                "\t\t</arch>\n" +
                "\t</arches>\n" +
                "</tbs-flow>\"," +
                "\"operator\":\"td_admin\",\"isContainedInLastDataload\":\"N\",\"toDelete\":\"N\",\"recordInsertDate\":1585248294877,\"recordUpdateDate\":1585248294877,\"solutionName\":\"TBS_SELF\"}";
    }

}
