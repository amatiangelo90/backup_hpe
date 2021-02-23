package com.hpe.automatization.test.testSuiteDataloadService;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.model.dataloadmodel.HelpTextType;
import com.hpe.dataload.generator.model.intfilemodel.DataSetItem;
import com.hpe.dataload.generator.orm.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.hpe.automatization.test.utils.TestUtils.*;
import static com.hpe.dataload.generator.model.intfilemodel.utils.ConverterIntermediateFile.getSlimFromFatConverter;
import static com.hpe.dataload.generator.utils.DataloadGenUtils.buildHelpTextTypeTypeObjectFromXmlString;
import static com.hpe.dataload.generator.utils.DataloadGenUtils.buildStringXmlFromHelpTextTemplate;
import static com.hpe.dataload.generator.utils.DataloadGenUtils.buildXmlStringFromDataSetObject;
import static com.hpe.dataload.generator.utils.XmlSourceReader.getDataSetTypeObjectFromPath;
import static org.junit.Assert.assertNotNull;

public class TestOrmIntegration {

    private static final String TBS_SELF_SOLUTION = "TBS_SELF";

    @Test
    public void testORMUsageToretrieveTbsPatternSavedEntity() throws DataloadGeneratorException {

        // takes from db patternSavedEntity List by solution name
        List<TbsPatternSavedTEntity> tbsPatternSavedTEntities = getTbsPatternSavedTEntityList(TBS_SELF_SOLUTION);

        for(TbsPatternSavedTEntity tbsPatternSavedTEntity : tbsPatternSavedTEntities){
            if("td_admin".equalsIgnoreCase(tbsPatternSavedTEntity.getOperator()))
                System.out.println("Operator : " + tbsPatternSavedTEntity.getOperator() +" -- Dataload Name" +tbsPatternSavedTEntity.getSegment() + "Intermediate File : " + tbsPatternSavedTEntity.getIntermediateXml());
        }
        assertNotNull(tbsPatternSavedTEntities);
    }

    @Test
    public void testORMUsageToretrieveBlocks() throws DataloadGeneratorException {

//        List<BlockDiagCodeMappingTEntity> tbsBlockSaved = getTbsBlockCodeDiagMappingTEntity();
        List<BlockDefEntityWrap> tbsBlockEntity = getTbsBlockEntity();
//        System.out.println("BlockDiagCodeMappingTEntity - " + tbsBlockSaved);
        System.out.println("BlockDefTEntity - " + tbsBlockEntity);
//        assertNotNull(tbsBlockSaved);
    }

    @Test
    public void testORMUsageToDeleteBlockDiagConf() throws DataloadGeneratorException {
        BlockDefEntityWrap blockDefEntityWrap = new BlockDefEntityWrap();
        blockDefEntityWrap.setBlockName("next_g_cadutaConn1.2");
        deleteBlockDiagConf(blockDefEntityWrap);
    }

    @Test(expected = DataloadGeneratorException.class)
    public void testORMUsageToCreateBlockDiagConfWithNULLExitCodeList() throws DataloadGeneratorException {
        BlockDefEntityWrap blockDefEntityWrap = new BlockDefEntityWrap();
        blockDefEntityWrap.setBlockName("next_g_cadutaConn1.2");
        createBlockDiagConf(blockDefEntityWrap);
    }


    @Test
    public void testORMUsageToCreateBlockDiagConf() throws DataloadGeneratorException {
        BlockDefEntityWrap blockDefEntityWrap = new BlockDefEntityWrap();
        blockDefEntityWrap.setBlockName("next_g_cadutaConn1.2");
        List<PartialBlockDiagCodeMappingTEntity> partialBlockDiagCodeMappingTEntities = new ArrayList<>();
        PartialBlockDiagCodeMappingTEntity partialBlockDiagCodeMappingTEntity = new PartialBlockDiagCodeMappingTEntity();
        partialBlockDiagCodeMappingTEntity.setExitCode("CadutaConn_Opz1_2Wifi");
        partialBlockDiagCodeMappingTEntities.add(partialBlockDiagCodeMappingTEntity);
        blockDefEntityWrap.setExitCodeList(partialBlockDiagCodeMappingTEntities);
        createBlockDiagConf(blockDefEntityWrap);
    }

    @Test
    public void testORMUsageToUpdateBlockDiagConf() throws DataloadGeneratorException {
        BlockDefEntityWrap blockDefEntityWrap = new BlockDefEntityWrap();
        blockDefEntityWrap.setBlockName("next_g_cadutaConn1.2");
        List<PartialBlockDiagCodeMappingTEntity> partialBlockDiagCodeMappingTEntities = new ArrayList<>();
        PartialBlockDiagCodeMappingTEntity partialBlockDiagCodeMappingTEntity = new PartialBlockDiagCodeMappingTEntity();
        partialBlockDiagCodeMappingTEntity.setExitCode("CadutaConn_Opz1_2Wifi");

        partialBlockDiagCodeMappingTEntities.add(partialBlockDiagCodeMappingTEntity);
        blockDefEntityWrap.setExitCodeList(partialBlockDiagCodeMappingTEntities);
        updateBlockDiagConf(blockDefEntityWrap);
    }

    @Test
    public void testFatToSlim() throws DataloadGeneratorException {
        String cadutaConnessioneOpz1_1IntFileFat = "C:\\Users\\50050802\\Desktop\\Progetti\\DATALOAD_DEV_BRANCH\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\main\\resources\\StrutturaIntermediaNavigazioneAssente.xml";
        DataSetItem dataSetItemObjectFromExt = getDataSetTypeObjectFromPath(cadutaConnessioneOpz1_1IntFileFat);
        String slimFromFatConverter = getSlimFromFatConverter(buildXmlStringFromDataSetObject(dataSetItemObjectFromExt));
        System.out.println(slimFromFatConverter);
    }

    @Test
    public void testbuildHelpTextTypeTypeObjectFromXmlString() throws DataloadGeneratorException {
        buildHelpTextTypeTypeObjectFromXmlString(getHelptextTemplateXml());
    }

    private String getHelptextTemplateXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<helpText xmlns:ns2=\"http://www.xml.td.hp.com/Model\">\n" +
                "    <ns2:name>asd</ns2:name>\n" +
                "    <ns2:text>asd</ns2:text>\n" +
                "    <ns2:icon>asd</ns2:icon>\n" +
                "</helpText>";
    }

    @Test
    public void objToStringXML() throws DataloadGeneratorException {
        HelpTextType helpTextType = new HelpTextType();
        helpTextType.setIcon("asd");
        helpTextType.setName("asd");
        helpTextType.setText("asd");

        String s = buildStringXmlFromHelpTextTemplate(helpTextType);
        System.out.println(s);
    }

}
