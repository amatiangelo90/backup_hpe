package com.hpe.automatization.test;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.intergration.DataloadGeneratorConfiguration;
import com.hpe.dataload.generator.intergration.DataloadGeneratorImpl;
import com.hpe.dataload.generator.intergration.DataloadGeneratorInput;
import org.junit.Test;

public class GenDataload_intExternal {

    private static final String CURRENT_DATALOAD_VERSION                            = "1";
    private static final String DESCRIPTION                                         = "description" ;
    private static final String COORDASS                                            = "coordass";
    private static final String TBS_SELF_SOLUTION                                   = "TBS_SELF";
    private static final String TED_VERSION                                         = "8.1";
    private static final String TED_VERSION_WRONG                                   = "8.4";
    private static final String PATH_OUT                                            = "..\\dataloadgen\\src\\test\\resources\\";
    private static final String WRONG_PATH_OUT                                      = "..\\dataloadgdden\\src\\main\\resources\\";


    /**
     * This is not a test methos. With this method you can check the happyCase behaviour of the tool.
     *
     * To try it, take off the comment, let's make getIntermediateFile method return your own intermediate file and run it
     *
     * You gonna see the dataload generated into a PATH_OUT folder that you selected
     *          private static final String PATH_OUT = "..\\dataloadgen\\src\\test\\resources\\";
     *
     * @throws DataloadGeneratorException
     */
    @Test
    public void testIntegrationExtSystem() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFile())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());

    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_nullDataloadVersion() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFile())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(null)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());

    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_ExitBlockConfiguredNOTAsLAST() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFile_EXITBLOCKNotLast())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(null)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());

    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_wrongFormatDataloadVersion() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFile())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion("5,t")
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());

    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_ZeroDataloadVersion() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFile())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion("0")
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());

    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_nullOutputPath() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFile())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(null).build()).build());

    }



    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_wrongTedVersion() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFile())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION_WRONG)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());

    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_wrongPathOutput() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFile())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(WRONG_PATH_OUT).build()).build());
    }


    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_missingTag() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getDataload_missingTag())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());

    }


    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_emptyIntermediateFile() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml("")
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());

    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_nullIntermediateFile() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(null)
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_patternOutputMissing() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFileWithoutPatternOutput())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_patternOutputEmpty() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFileWitEmptyPatternOutput())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());
    }
    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_blockSectionEmpty() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFileWitEmptyBlocksSection())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_blockSectionWithMoreThanOneEXITBlock() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFileExitBlockReplicated())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_blockSection_noSenseBlocks() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFileNoSenseBlock())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(TBS_SELF_SOLUTION)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testIntegrationExtSystem_dataloadSolutionNULL() throws DataloadGeneratorException {
        DataloadGeneratorImpl dataloadGenerator = new DataloadGeneratorImpl();

        dataloadGenerator.generateDataload(
                DataloadGeneratorInput.builder()
                        .intermediateXml(getIntermediateFile())
                        .operatornName(COORDASS)
                        .description(DESCRIPTION)
                        .dataloadVersion(CURRENT_DATALOAD_VERSION)
                        .tedSolution(null)
                        .tedVersion(TED_VERSION)
                        .configuration(DataloadGeneratorConfiguration
                                .builder()
                                .outputPath(PATH_OUT).build()).build());
    }

    private String getIntermediateFileNoSenseBlock() {

        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<data-set xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "\t<item>\n" +
                "\t\t<pattern_filter>\n" +
                "\t\t\t<technology>SLU</technology>\n" +
                "\t\t\t<service>UBB</service>\n" +
                "\t\t\t<is_unique_cpe>Y</is_unique_cpe>\n" +
                "\t\t\t<rg_type>IAD</rg_type>\n" +
                "\t\t\t<problem_type>V</problem_type>\n" +
                "\t\t\t<class_value>RES</class_value>\n" +
                "\t\t\t<segment>FASTWEB</segment>\n" +
                "\t\t</pattern_filter>\n" +
                "\t\t<blocks_execution_group>\n" +
                "\t\t\t\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_id>3</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<block_name>EXIT</block_name>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t\t\n" +
                "\t\t</blocks_execution_group>\n" +
                "\t\t\n" +
                "\t\t<pattern_output>\n" +
                "\t\t\t<intervention_required>1</intervention_required>\n" +
                "\t\t\t<problem_not_solved>0</problem_not_solved>\n" +
                "\t\t\t<no_problem_detected>1</no_problem_detected>\n" +
                "\t\t\t<massive_disservice>0</massive_disservice>\n" +
                "\t\t\t<problem_solved>0</problem_solved>\n" +
                "\t\t</pattern_output>\n" +
                "\t</item>\n" +
                "</data-set>";
    }

    private String getIntermediateFileExitBlockReplicated() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<data-set xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "\t<item>\n" +
                "\t\t<pattern_filter>\n" +
                "\t\t\t<technology>SLU</technology>\n" +
                "\t\t\t<service>UBB</service>\n" +
                "\t\t\t<is_unique_cpe>Y</is_unique_cpe>\n" +
                "\t\t\t<rg_type>IAD</rg_type>\n" +
                "\t\t\t<problem_type>V</problem_type>\n" +
                "\t\t\t<class_value>RES</class_value>\n" +
                "\t\t\t<segment>FASTWEB</segment>\n" +
                "\t\t</pattern_filter>\n" +
                "\t\t<blocks_execution_group>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_name>STAL</block_name>\n" +
                "\t\t\t\t<block_id>1</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<match_cond>ELSE</match_cond>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_id>3</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<block_name>EXIT</block_name>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_id>3</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<block_name>EXIT</block_name>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t</blocks_execution_group>\n" +
                "\t\t\n" +
                "\t\t<pattern_output>\n" +
                "\t\t\t<intervention_required>1</intervention_required>\n" +
                "\t\t\t<problem_not_solved>0</problem_not_solved>\n" +
                "\t\t\t<no_problem_detected>1</no_problem_detected>\n" +
                "\t\t\t<massive_disservice>0</massive_disservice>\n" +
                "\t\t\t<problem_solved>0</problem_solved>\n" +
                "\t\t</pattern_output>\n" +
                "\t</item>\n" +
                "</data-set>";
    }

    private String getIntermediateFileWitEmptyBlocksSection() {

        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<data-set xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "\t<item>\n" +
                "\t\t<pattern_filter>\n" +
                "\t\t\t<technology>SLU</technology>\n" +
                "\t\t\t<service>UBB</service>\n" +
                "\t\t\t<is_unique_cpe>Y</is_unique_cpe>\n" +
                "\t\t\t<rg_type>IAD</rg_type>\n" +
                "\t\t\t<problem_type>V</problem_type>\n" +
                "\t\t\t<class_value>RES</class_value>\n" +
                "\t\t\t<segment>FASTWEB</segment>\n" +
                "\t\t</pattern_filter>\n" +
                "\t\n" +
                "\t\t\n" +
                "\t\t<pattern_output>\n" +
                "\t\t\t<intervention_required>1</intervention_required>\n" +
                "\t\t\t<problem_not_solved>0</problem_not_solved>\n" +
                "\t\t\t<no_problem_detected>1</no_problem_detected>\n" +
                "\t\t\t<massive_disservice>0</massive_disservice>\n" +
                "\t\t\t<problem_solved>0</problem_solved>\n" +
                "\t\t</pattern_output>\n" +
                "\t</item>\n" +
                "</data-set>";
    }


    private String getIntermediateFileWitEmptyPatternOutput() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<data-set xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "\t<item>\n" +
                "\t\t<pattern_filter>\n" +
                "\t\t\t<technology>SLU</technology>\n" +
                "\t\t\t<service>UBB</service>\n" +
                "\t\t\t<is_unique_cpe>Y</is_unique_cpe>\n" +
                "\t\t\t<rg_type>IAD</rg_type>\n" +
                "\t\t\t<problem_type>V</problem_type>\n" +
                "\t\t\t<class_value>RES</class_value>\n" +
                "\t\t\t<segment>FASTWEB</segment>\n" +
                "\t\t</pattern_filter>\n" +
                "\t\t<blocks_execution_group>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_name>STAL</block_name>\n" +
                "\t\t\t\t<block_id>1</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<match_cond>ELSE</match_cond>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_id>3</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<block_name>EXIT</block_name>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t</blocks_execution_group>\n" +
                "\t\t\n" +
                "\t\t<pattern_output>\n" +
                "\t\t\t\n" +
                "\t\t</pattern_output>\n" +
                "\t</item>\n" +
                "</data-set>";

    }
    private String getIntermediateFileWithoutPatternOutput() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"+
                "<data-set xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"+
                "\t<item>\n"+
                "\t\t<pattern_filter>\n"+
                "\t\t\t<technology>SLU</technology>\n"+
                "\t\t\t<service>UBB</service>\n"+
                "\t\t\t<is_unique_cpe>Y</is_unique_cpe>\n"+
                "\t\t\t<rg_type>IAD</rg_type>\n"+
                "\t\t\t<problem_type>V</problem_type>\n"+
                "\t\t\t<class_value>RES</class_value>\n"+
                "\t\t\t<segment>FASTWEB</segment>\n"+
                "\t\t</pattern_filter>\n"+
                "\t\t<blocks_execution_group>\n"+
                "\t\t\t<block_execution_flow>\n"+
                "\t\t\t\t<block_name>STAL</block_name>\n"+
                "\t\t\t\t<block_id>1</block_id>\n"+
                "\t\t\t\t<x_position>120</x_position>\n"+
                "\t\t\t\t<y_position>40</y_position>\n"+
                "\t\t\t\t<match_cond>ELSE</match_cond>\n"+
                "\t\t\t</block_execution_flow>\n"+
                "\t\t\t<block_execution_flow>\n"+
                "\t\t\t\t<block_id>3</block_id>\n"+
                "\t\t\t\t<x_position>120</x_position>\n"+
                "\t\t\t\t<y_position>40</y_position>\n"+
                "\t\t\t\t<block_name>EXIT</block_name>\n"+
                "\t\t\t</block_execution_flow>\n"+
                "\t\t</blocks_execution_group>\n"+
                "\t\t\n"+
                "\t\t\n"+
                "\t</item>\n"+
                "</data-set>";
    }

    private static String getDataload_missingTag(){
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<data-set xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "\t<item>\n" +
                "\t\t<pattern_filter>\n" +
                "\t\t\t<technology>SLU</technology>\n" +
                "\t\t\t<service>UBB</service>\n" +

                "\t\t\t\t<block_name>STAL</block_name>\n" +
                "\t\t\t\t<block_id>1</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<match_cond>ELSE</match_cond>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_id>3</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<block_name>EXIT</block_name>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t</blocks_execution_group>\n" +
                "\t\t\n" +
                "\t\t<pattern_output>\n" +
                "\t\t\t<intervention_required>1</intervention_required>\n" +
                "\t\t\t<problem_not_solved>0</problem_not_solved>\n" +
                "\t\t\t<no_problem_detected>1</no_problem_detected>\n" +
                "\t\t\t<massive_disservice>0</massive_disservice>\n" +
                "\t\t\t<problem_solved>0</problem_solved>\n" +
                "\t\t</pattern_output>\n" +
                "\t</item>\n" +
                "</data-set>";
    }

    private static String getIntermediateFile(){
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<data-set xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "\t<item>\n" +
                "\t\t<pattern_filter>\n" +
                "\t\t\t<technology>SLU</technology>\n" +
                "\t\t\t<service>UBB</service>\n" +
                "\t\t\t<is_unique_cpe>Y</is_unique_cpe>\n" +
                "\t\t\t<cpeType>FASTGATE</cpeType>\n" +
                "\t\t\t<rg_type>IAD</rg_type>\n" +
                "\t\t\t<problem_type>V</problem_type>\n" +
                "\t\t\t<class_value>RES</class_value>\n" +
                "\t\t\t<segment>FASTWEB</segment>\n" +
                "\t\t</pattern_filter>\n" +
                "\t\t<blocks_execution_group>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_name>STAL</block_name>\n" +
                "\t\t\t\t<block_id>1</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<match_cond>ELSE</match_cond>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_id>3</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<block_name>EXIT</block_name>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t</blocks_execution_group>\n" +
                "\t\t\n" +
                "\t\t<pattern_output>\n" +
                "\t\t\t<intervention_required>1</intervention_required>\n" +
                "\t\t\t<problem_not_solved>0</problem_not_solved>\n" +
                "\t\t\t<no_problem_detected>1</no_problem_detected>\n" +
                "\t\t\t<massive_disservice>0</massive_disservice>\n" +
                "\t\t\t<problem_solved>0</problem_solved>\n" +
                "\t\t</pattern_output>\n" +
                "\t</item>\n" +
                "</data-set>";
    }

    private static String getIntermediateFile_EXITBLOCKNotLast(){
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<data-set xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "\t<item>\n" +
                "\t\t<pattern_filter>\n" +
                "\t\t\t<technology>ULL-CONAN</technology>\n" +
                "\t\t\t<service>prova 162</service>\n" +
                "\t\t\t<is_unique_cpe>Y</is_unique_cpe>\n" +
                "\t\t\t<rg_type>IAD</rg_type>\n" +
                "\t\t\t<problem_type>D</problem_type>\n" +
                "\t\t\t<class_value>RES</class_value>\n" +
                "\t\t\t<segment>FASTWEB</segment>\n" +
                "\t\t</pattern_filter>\n" +
                "\t\t<blocks_execution_group>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_id>3</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<block_name>EXIT</block_name>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_name>RMD</block_name>\n" +
                "\t\t\t\t<block_id>1</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<match_cond>01</match_cond>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\n" +
                "\t\t</blocks_execution_group>\n" +
                "\t\t\n" +
                "\t\t<pattern_output>\n" +
                "\t\t\t<intervention_required>1</intervention_required>\n" +
                "\t\t\t<problem_not_solved>0</problem_not_solved>\n" +
                "\t\t\t<no_problem_detected>0</no_problem_detected>\n" +
                "\t\t\t<massive_disservice>0</massive_disservice>\n" +
                "\t\t\t<problem_solved>0</problem_solved>\n" +
                "\t\t</pattern_output>\n" +
                "\t</item>\n" +
                "\t\n" +
                "\t<item>\n" +
                "\t\t<pattern_filter>\n" +
                "\t\t\t<technology>ULL-CONAN</technology>\n" +
                "\t\t\t<service>prova 162</service>\n" +
                "\t\t\t<is_unique_cpe>Y</is_unique_cpe>\n" +
                "\t\t\t<rg_type>IAD</rg_type>\n" +
                "\t\t\t<problem_type>D</problem_type>\n" +
                "\t\t\t<class_value>RES</class_value>\n" +
                "\t\t\t<segment>FASTWEB</segment>\n" +
                "\t\t</pattern_filter>\n" +
                "\t\t<blocks_execution_group>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_name>RMD</block_name>\n" +
                "\t\t\t\t<block_id>1</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<match_cond>00</match_cond>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t\t<block_execution_flow>\n" +
                "\t\t\t\t<block_id>3</block_id>\n" +
                "\t\t\t\t<x_position>120</x_position>\n" +
                "\t\t\t\t<y_position>40</y_position>\n" +
                "\t\t\t\t<block_name>EXIT</block_name>\n" +
                "\t\t\t</block_execution_flow>\n" +
                "\t\t</blocks_execution_group>\n" +
                "\t\t\n" +
                "\t\t<pattern_output>\n" +
                "\t\t\t<intervention_required>0</intervention_required>\n" +
                "\t\t\t<problem_not_solved>0</problem_not_solved>\n" +
                "\t\t\t<no_problem_detected>0</no_problem_detected>\n" +
                "\t\t\t<massive_disservice>1</massive_disservice>\n" +
                "\t\t\t<problem_solved>0</problem_solved>\n" +
                "\t\t</pattern_output>\n" +
                "\t</item>\n" +
                "</data-set>";
    }
}
