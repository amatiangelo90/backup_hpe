package com.hpe.automatization.test;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.utils.EnumTedVersion;
import com.hpe.ted.modelconverter.ConverterException;
import com.hpe.ted.modelconverter.SlimToFatModelConverter;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static com.hpe.dataload.generator.engine.Builder.generateDataloadFromXmlFile;
import static com.hpe.dataload.generator.engine.Builder.generateDataloadFromXmlPath;

public class CheckListGen {

    private static final String CURRENT_DATALOAD_VERSION = "1";

    public static final String CADUTA_CONNESSIONE = "..\\dataloadgen\\src\\test\\resources\\CheckList\\StrutturaIntermediaCadutaConnessione.xml";
    @Test
    public void testIntegrationDataload_CADUTA_CONNESSIONE() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(CADUTA_CONNESSIONE,false,false, EnumTedVersion.version_8_1,"CADUTA_CONNESSIONE",  "description","checkList", CURRENT_DATALOAD_VERSION);
    }

    public static final String BLOCCO_2_WIFI = "..\\dataloadgen\\src\\test\\resources\\CheckList\\StrutturaIntermediaCadutaConn_Opz2WIFI.xml";
    @Test
    public void testIntegrationDataload_BLOCCO_2_WIFI() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(BLOCCO_2_WIFI,false,false, EnumTedVersion.version_8_1,"BLOCCO_2_WIFI",  "description","checkList", CURRENT_DATALOAD_VERSION);
    }

    public static final String BLOCCO_OPZ1_2_WIFI = "..\\dataloadgen\\src\\test\\resources\\CheckList\\StrutturaIntermediaCadutaConn_Opz1_2WIFI.xml";
    @Test
    public void testIntegrationDataload_BLOCCO_OPZ_1_2_WIFI() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(BLOCCO_OPZ1_2_WIFI,false,false, EnumTedVersion.version_8_1,"BLOCCO_OPZ_1_2_WIFI",  "description","checkList", CURRENT_DATALOAD_VERSION);
    }

    public static final String BLOCCO_OPZ1_1_ETH = "..\\dataloadgen\\src\\test\\resources\\CheckList\\StrutturaIntermediaCadutaConn_Opz1_1Eth.xml";
    @Test
    public void testIntegrationDataload_BLOCCO_OPZ1_1_ETH() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(BLOCCO_OPZ1_1_ETH,false,false, EnumTedVersion.version_8_1,"BLOCCO_OPZ1_1_ETH",  "description","checkList", CURRENT_DATALOAD_VERSION);
    }

    public static final String BLOCCO_NAVIGAZIONE_ASSENTE = "..\\dataloadgen\\src\\test\\resources\\CheckList\\StrutturaIntermediaNavigazioneAssente.xml";
    @Test
    public void testIntegrationDataload_NAVIGAZIONE_ASSENTE() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(BLOCCO_NAVIGAZIONE_ASSENTE,false,false, EnumTedVersion.version_8_1,"NAVIGAZIONE_ASSENTE",  "description","checkList", CURRENT_DATALOAD_VERSION);
    }

    public static final String BLOCCO_NAVIGAZIONE_ASSENTE_28Febbraio = "..\\dataloadgen\\src\\test\\resources\\DAJE_DATALOAD.xml";
    @Test
    public void testIntegrationDataload_NAVIGAZIONE_ASSENTE_28Febbraio() throws DataloadGeneratorException {
        generateDataloadFromXmlPath(BLOCCO_NAVIGAZIONE_ASSENTE_28Febbraio,false,false, EnumTedVersion.version_8_1,"NAVIGAZIONE_ASSENTE",  "description","checkList", CURRENT_DATALOAD_VERSION);
    }

    /**
     * SlimToFatModelConverter
     *
     */
    public static final String NAVIGAZIONE_LENTA_WIFI = "..\\dataloadgen\\src\\test\\resources\\CheckList\\StrutturaIntermediaNavigazioneLentaWIFI.xml";
    @Test
    public void testIntegrationDataload_NAVIGAZIONE_LENTA() throws ConverterException, DataloadGeneratorException, IOException {

        SlimToFatModelConverter slimToFatModelConverter = new SlimToFatModelConverter();
        String convert = slimToFatModelConverter.convert(fileXmlAsString(NAVIGAZIONE_LENTA_WIFI));
        generateDataloadFromXmlFile(convert,false,false, EnumTedVersion.version_8_1,"NAVIGAZIONE_LENTA_WIFI",  "description","checkList",CURRENT_DATALOAD_VERSION,"..\\dataloadgen\\src\\test\\resources\\CheckList\\");
//        System.out.println("##### :" + convert);
    }

    public String fileXmlAsString(String xmlFile) throws IOException {
        Reader fileReader = new FileReader(xmlFile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String line = bufReader.readLine();
        while( line != null){
            sb.append(line).append("\n");
            line = bufReader.readLine();
        }
        String xml2String = sb.toString();
        bufReader.close();
        return xml2String;

    }

//    @Test
//    public void populateClearVectorList(){
//
//        String firstHalf = "<Parameter>\n" +
//                "\t\t<attribute>";
//        String secondHalf = "</attribute>\n" +
//                "\t\t<value>-</value>\n" +
//                "\t\t<type>String</type>\n" +
//                "\t</Parameter>";
//
//        Iterator<String> iterator = stateIndexes.iterator();
//        StringBuffer stringBuffer = new StringBuffer("<Parameters>");
//        while (iterator.hasNext()){
//            String currentStateItem = iterator.next();
//            stringBuffer.append(firstHalf + currentStateItem + secondHalf);
//        }
//        stringBuffer.append("</Parameters>");
//
//        System.out.println(stringBuffer);
//
//    }

//    @Test
//    public void populateClearVectorList_DAtaload(){
//        int size = stateIndexes.size();
//        String firstHalf = "<outputParameter>\n" +
//                "\t\t\t\t\t<name>";
//        String secondHalf = "</name>\n" +
//                "\t\t\t\t\t<type>String</type> \n" +
//                "\t\t\t\t\t<label>!!result!</label>\n" +
//                "\t\t\t\t\t<editable>false</editable>\n" +
//                "\t\t\t\t\t<hidden>true</hidden>\n" +
//                "\t\t\t\t\t<includeInStateVector>true</includeInStateVector>\t\t\n" +
//                "\t\t\t\t\t<stateVectorName>";
//        String thirdHalf = "</stateVectorName>\t\t\t\t\t\t\n" +
//                "\t\t\t\t</outputParameter>";
//
//        Iterator<String> iterator = stateIndexes.iterator();
//        StringBuffer stringBuffer = new StringBuffer("<outputParameters>");
//        while (iterator.hasNext()){
//            String currentStateItem = iterator.next();
//            stringBuffer.append(firstHalf + currentStateItem + secondHalf + currentStateItem + thirdHalf);
//        }
//        stringBuffer.append("</outputParameters>");
//
//        System.out.println(stringBuffer);
//
//    }
//
//    @Test
//    public void createMapDuplicatesIndexes(){
//        Map<String, Long> counter = stateIndexes.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        System.out.println(counter);
//    }
}
