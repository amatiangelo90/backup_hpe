package com.hpe.automatization.test;


import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.model.dataloadmodel.ActionRefType;
import com.hpe.dataload.generator.model.dataloadmodel.GroupType;
import com.hpe.dataload.generator.model.intfilemodel.DataSetItem;
import com.hpe.dataload.generator.utils.EnumTedVersion;
import org.junit.Test;
import static com.hpe.dataload.generator.utils.DataloadGenUtils.SPECIAL_CHAR_EX;
import static com.hpe.dataload.generator.utils.DataloadGenUtils.validateStringValueBySpecialCharPresence;
import static com.hpe.dataload.generator.engine.Builder.generateDataloadFromXmlPath;
import static com.hpe.dataload.generator.utils.XmlSourceReader.getDataSetTypeObjectFromPath;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;



public class GenDataload_xmlPathInput {

    public static final String NOT_PRESENT_FILE = "..\\dataloadgen\\src\\test\\resources\\wrongwrongasa.xml";
    public static final String INPUT_INTEGRATION_DATALOAD = "..\\dataloadgen\\src\\test\\resources\\intermediate_integration.xml";
    public static final String WRONG_INPUT_PATH_FILE = "..\\dataloadgen\\src\\test\\resources\\intermediate_file.1xm5l";
    public static final String INPUT_PATH_FILE_DATALOAD = "..\\dataloadgen\\src\\test\\resources\\dataload.xml";
    public static final String INPUT_PATH_FILE_DATALOAD_REG_WRONG = "..\\d@@ataloadgen\\src\\m?ain\\resources\\dataload.xml";
    public static final String INPUT_INTEGRATION_SPEC_LINUX = "/opt/OV/ServiceActivator/solutions/OSS_TED/etc/intermediateFiles/intermediate_file.xml";
    private static final String INPUT_PATTERN_OUTPUT_NULL = "..\\dataloadgen\\src\\test\\resources\\intermediate_pattern_output_null.xml";;
    private static final String WRONG_FILE_INTERMEDIATE = "..\\dataloadgen\\src\\test\\resources\\intermediate_list_dataset.xml";;
    private static final String WRONG_FILE_INTERMEDIATE_INT = "..\\dataloadgen\\src\\test\\resources\\strutturaIntermerdia.xml";;

    private static final String CURRENT_DATALOAD_VERSION                            = "1";
    private static String TBS_SELF_SOLUTION                                         = "TBS_SELF";


    /**
     * Intermediate file to show the happyCase behaviour of the tool
     *
     * this 'test' is not checking any error but reproducing a real 'happy case' behaviour of the tool.
     * Take off the comment and put your own intermediate file into the xml file placed to the current path: "..\\dataloadgen\\src\\test\\resources\\intermediate_file.xml";
     *
     */
//    public static final String INTERMEDIATE_FILE_HAPPY_CASE = "..\\dataloadgen\\src\\test\\resources\\strutturaIntermerdia.xml";
//    public static final String INTERMEDIATE_FILE_HAPPY_CASE = "..\\dataloadgen\\src\\test\\resources\\intFileSkinny.xml";
//    public static final String INTERMEDIATE_FILE_HAPPY_CASE = "..\\dataloadgen\\src\\test\\resources\\StrutturaIntermediaCadutaConnessione.xml";




//    public static final String INTERMEDIATE_FILE_HAPPY_CASE = "..\\dataloadgen\\src\\test\\resources\\StrutturaIntermediaCadutaConn.xml";
//    public static final String INTERMEDIATE_FILE_HAPPY_CASE = "..\\dataloadgen\\src\\test\\resources\\StrutturaIntermediaCadutaConnessione_Opz12Wifi.xml";
    public static final String INTERMEDIATE_FILE_HAPPY_CASE = "..\\dataloadgen\\src\\test\\resources\\fede_xxxxxx.xml";
//    public static final String INTERMEDIATE_FILE_HAPPY_CASE = "..\\dataloadgen\\src\\test\\resources\\StrutturaIntermediaNavigazioneAssente.xml";
//    public static final String INTERMEDIATE_FILE_HAPPY_CASE = "..\\dataloadgen\\src\\test\\resources\\StrutturaIntermedia_CadutaConnessioneOpzione2Wifi.xml";
//    public static final String INTERMEDIATE_FILE_HAPPY_CASE = "..\\dataloadgen\\src\\test\\resources\\StrutturaIntermedia_CadutaConnessioneETH.xml";

    @Test
    public void testIntegrationDataload() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(INTERMEDIATE_FILE_HAPPY_CASE,false,false, EnumTedVersion.version_8_1,"ReteAlta",  "description","coordass", CURRENT_DATALOAD_VERSION);
    }
    /**
     * ===============================================================================================================
     *
     *
     */

    /**
     * Intermediate file to show the happyCase behaviour of the tool - That file is been taken from The GUI tool result
     *
     *
     */
    public static final String INTERMEDIATE_FILE_GUI_OUTPUT = "..\\dataloadgen\\src\\test\\resources\\intfile_2.xml";

    @Test
    public void testIntegrationDataload_GUIOutput() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(INTERMEDIATE_FILE_GUI_OUTPUT,true,true,EnumTedVersion.version_8_1,TBS_SELF_SOLUTION,  "description","coordass", CURRENT_DATALOAD_VERSION);
    }
    /**
     * ===============================================================================================================
     *
     *
     */


    @Test
    public void testIntegrationDataload_int() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(WRONG_FILE_INTERMEDIATE_INT,true,true,EnumTedVersion.version_8_1,TBS_SELF_SOLUTION,  "description","coordass", CURRENT_DATALOAD_VERSION);
    }

    @Test
    public void testIntegrationWRONGDataload() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(WRONG_FILE_INTERMEDIATE,true,true,EnumTedVersion.version_8_1,TBS_SELF_SOLUTION,  "description","coordass", CURRENT_DATALOAD_VERSION);
    }

    /**
     * File not present. Expected exception
     *
     */
    @Test(expected = DataloadGeneratorException.class)
    public void testNotPresentFile() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(NOT_PRESENT_FILE,true,true, EnumTedVersion.version_8_1,TBS_SELF_SOLUTION,"description","coordass",CURRENT_DATALOAD_VERSION);
    }
    @Test(expected = DataloadGeneratorException.class)
    public void testVersionDataLoad_8_1() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(INPUT_INTEGRATION_SPEC_LINUX,true,true, EnumTedVersion.version_8_0,TBS_SELF_SOLUTION, "description","coordass",CURRENT_DATALOAD_VERSION);
    }
    @Test(expected = DataloadGeneratorException.class)
    public void testVersionDataLoad_8_0() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(INPUT_PATTERN_OUTPUT_NULL,true,true, EnumTedVersion.version_8_1,TBS_SELF_SOLUTION, "description","coordass",CURRENT_DATALOAD_VERSION);
    }
    @Test(expected = DataloadGeneratorException.class)
    public void testWrongFilePathName_regexConditionNotMatched() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(INPUT_PATH_FILE_DATALOAD_REG_WRONG,
                true,
                true,
                EnumTedVersion.version_8_1,
                "description",
                "coordass",
                TBS_SELF_SOLUTION,
                CURRENT_DATALOAD_VERSION);
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testEmptyInputArgs() throws DataloadGeneratorException {
        generateDataloadFromXmlPath("",true,true,EnumTedVersion.version_8_1,TBS_SELF_SOLUTION, "description","coordass",CURRENT_DATALOAD_VERSION);
    }
    @Test(expected = DataloadGeneratorException.class)
    public void testNullInputArgs() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(null,true,true,EnumTedVersion.version_8_1,TBS_SELF_SOLUTION,"description","coordass", CURRENT_DATALOAD_VERSION);
    }
    @Test
    public void testGetDataSetTypeOutputObject() throws DataloadGeneratorException {
        assertTrue(getDataSetTypeObjectFromPath(INTERMEDIATE_FILE_HAPPY_CASE) instanceof DataSetItem);
    }
    /**
     * the file gave in input in this case cannot be converted to DataSetItem Object, so it gonna throws an exception
     * @throws DataloadGeneratorException
     */
    @Test(expected = DataloadGeneratorException.class)
    public void testGetDataSetTypeWrongXmlFileContent() throws DataloadGeneratorException {
        try {
            getDataSetTypeObjectFromPath(INPUT_PATH_FILE_DATALOAD);
        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException(e);
        }
    }

    /**
     *
     * wrong extension file give as a input. DataloadGeneratorException expected
     *
     * @throws DataloadGeneratorException
     */
    @Test(expected = DataloadGeneratorException.class)
    public void testWrongArgumentExceptionGetDataSetTypeObject() throws DataloadGeneratorException {
        try {
            getDataSetTypeObjectFromPath(WRONG_INPUT_PATH_FILE);
        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException(e);
        }
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testWrongDataloadVersionValueInput() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(INPUT_PATH_FILE_DATALOAD_REG_WRONG,
                true,
                true,
                EnumTedVersion.version_8_1,
                TBS_SELF_SOLUTION,
                "description",
                "coordass",
                CURRENT_DATALOAD_VERSION);
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testZeroValueDataloadVersionInput() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(INPUT_INTEGRATION_DATALOAD,
                true,
                true,
                EnumTedVersion.version_8_1,
                TBS_SELF_SOLUTION,
                "description",
                "coordass",
                 CURRENT_DATALOAD_VERSION);
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testNullDataloadVersionAsInput() throws DataloadGeneratorException {

        generateDataloadFromXmlPath(
                INPUT_PATH_FILE_DATALOAD_REG_WRONG,
                true,
                true,
                EnumTedVersion.version_8_1,
                TBS_SELF_SOLUTION,
                "description",
                "coordass",
                CURRENT_DATALOAD_VERSION);
    }

    /**

     Check the equal and hashCode method of the class GroupType.Indexes.Index if this test goes wrong
     */
    @Test
    public void testEqualsAndHashCodeOnIndexObject(){
        GroupType.Indexes.Index index = new GroupType.Indexes.Index();

        GroupType.Indexes.Index.Actions actions = new GroupType.Indexes.Index.Actions();
        GroupType.Indexes.Index.Actions.Recommended reccomendedAction = new GroupType.Indexes.Index.Actions.Recommended();
        ActionRefType actionRefType = new ActionRefType();
        actionRefType.setName("asdasd");
        reccomendedAction.getAction().add(actionRefType);
        actions.setRecommended(reccomendedAction);
        GroupType.Indexes.Index index2 = new GroupType.Indexes.Index();

        GroupType.Indexes.Index.States states = new GroupType.Indexes.Index.States();
        states.getValue().add("");
        states.getValue().add("");
        states.getValue().add("");
        states.getValue().add("");
        states.getValue().add("");
        states.getValue().add("");
        states.getValue().add("");
        states.getValue().add("");
        states.getValue().add("");


        index.setActions(actions);
        index2.setActions(actions);

        index.setStates(states);
        index2.setStates(states);

        System.out.println("Code index: " + index.hashCode());
        System.out.println("Code index2: " + index2.hashCode());
        assertTrue(index2.equals(index));
    }

    @Test
    public void testEqualsAndHashCodeOnIndexObject_False(){
        GroupType.Indexes.Index index = new GroupType.Indexes.Index();

        GroupType.Indexes.Index.Actions actions = new GroupType.Indexes.Index.Actions();
        GroupType.Indexes.Index.Actions.Recommended reccomendedAction = new GroupType.Indexes.Index.Actions.Recommended();
        ActionRefType actionRefType = new ActionRefType();
        actionRefType.setName("asdasd");
        reccomendedAction.getAction().add(actionRefType);
        actions.setRecommended(reccomendedAction);

        index.setActions(actions);
        GroupType.Indexes.Index index2 = new GroupType.Indexes.Index();
        assertFalse(index2.equals(index));
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testWrongInput_specialCharacterContained() throws DataloadGeneratorException {
        try {
            validateStringValueBySpecialCharPresence("ytr!ew",SPECIAL_CHAR_EX);
        } catch (DataloadGeneratorException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException(e);
        }
    }

    @Test
    public void checkFileNameFormatIntegrity_spaceCharCase() throws DataloadGeneratorException {
        validateStringValueBySpecialCharPresence("yt rew",SPECIAL_CHAR_EX);
    }
}
