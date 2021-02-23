package com.hpe.dataload.generator.utils;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.model.intfilemodel.DataSetItem;
import com.hpe.dataload.generator.model.intfilemodel.ObjectFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.util.logging.Logger;

import static com.hpe.dataload.generator.utils.DataloadGenUtils.*;

/**
 * Read from classpath the input file and return the xmlData as String
 *
 * full path name is required as input
 *
 *  @author Angelo Amati - n. 50050802
 */

public class XmlSourceReader {

    private static final Logger logger = Logger.getLogger(XmlSourceReader.class.getName());
    /**
     *
     * This method give back a DataSetItem Object unmarshalled from an xml placed into file system
     * The method want as a input the filePath of the file
     *
     * Wrong file path will throw a DataloadGeneratorException
     *
     * @param filePath
     * @return
     * @throws DataloadGeneratorException
     */
    public static DataSetItem getDataSetTypeObjectFromPath(String filePath) throws DataloadGeneratorException {

        checkFileNameFormatIntegrity(filePath, XML_EXTENSION);
        validateStringValueBySpecialCharPresence(filePath, SPECIAL_CHAR_FILEPATH);
        File file = new File(filePath);

        if(Files.notExists(file.toPath())){
            logger.severe("Error - The file specified as inputIntermediate file doesn't exist: File: [" + filePath +"]. ");
            throw new DataloadGeneratorException("Error - The file specified as inputIntermediate file doesn't exist: File: [" + filePath +"]. ");
        }

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<DataSetItem> dateSetType = (JAXBElement<DataSetItem>) jaxbUnmarshaller.unmarshal(file);

            DataloadGenUtils.validateDataSetTypeObj(dateSetType.getValue());

            return dateSetType.getValue();
        } catch (JAXBException e) {
            logger.severe("Error - Impossible to generate dalaload due input file corrupted or wrong. Check the input file structure. StackTrace: " + e);
            throw new DataloadGeneratorException("Error - Impossible to generate dalaload due input file corrupted or wrong. Check the input file structure. StackTrace: " + e);
        }
    }


    /**
     *
     * This method give back a DataSetItem Object generated from an xmlString
     *
     * Wrong file path will throw a DataloadGeneratorException
     *
     * @param xmlIntermetiateFile
     * @return
     * @throws DataloadGeneratorException
     */
    public static DataSetItem getDataSetTypeObjectFromString(String xmlIntermetiateFile) throws DataloadGeneratorException {

        JAXBContext jaxbContext;
        try {
            if(xmlIntermetiateFile == null || xmlIntermetiateFile.equalsIgnoreCase(EMPTY_STRING))
                throw new DataloadGeneratorException("Error -  Intermediate file is empty or null.");

            jaxbContext = JAXBContext.newInstance(DataSetItem.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlIntermetiateFile);

            DataSetItem dataSetObj = (DataSetItem) unmarshaller.unmarshal(reader);
            validateDataSetTypeObj(dataSetObj);
            return dataSetObj;

        } catch (JAXBException e) {
            logger.severe("Error -  Wrong intermediate file structure. Cannot convert the input file into DataSetItem Object. InputFile [ " + xmlIntermetiateFile+" ]\n");
            throw new DataloadGeneratorException("Error -  Wrong intermediate file structure. Cannot convert the input file into DataSetItem Object. InputFile [ " + xmlIntermetiateFile+" ]\n");
        }
    }

    public static String readFileFromClasspathAsString(String filePath) throws DataloadGeneratorException {
        String xmlString;
        checkFileNameFormatIntegrity(filePath, XML_EXTENSION);
        File xmlFile = new File(filePath);
        Reader fileReader;
        try {
            fileReader = new FileReader(xmlFile);
            BufferedReader bufReader = new BufferedReader(fileReader);

            StringBuilder sb = new StringBuilder();
            String line = bufReader.readLine();
            while( line != null){
                sb.append(line).append("\n");
                line = bufReader.readLine();
            }
            xmlString = sb.toString();
            bufReader.close();

        } catch (FileNotFoundException e) {
            throw new DataloadGeneratorException(e);
        } catch (IOException e) {
            throw new DataloadGeneratorException(e);
        }
        return xmlString;
    }

    /**
     * Get Document Object from xml which is file system located
     *
     * NOT WORKING
     *
     * @param filePath
     * @return
     * @throws DataloadGeneratorException
     */

    public static Document readFileFromClasspathAsDocumentObject(String filePath) throws DataloadGeneratorException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(readFileFromClasspathAsString(filePath));
            return doc;
        } catch (ParserConfigurationException e) {
            throw new DataloadGeneratorException(e);
        } catch (SAXException e) {
            throw new DataloadGeneratorException(e);
        } catch (IOException e) {
            throw new DataloadGeneratorException(e);
        }
    }

    public static void checkDataloadVersionIntegrity(String dataloadVersion) throws DataloadGeneratorException {
        try{
            if(dataloadVersion == null){
                logger.severe("Error - The dataload version inserted [ " + dataloadVersion + " ] is not allowed. Valorize that parameter with an integer equal or grater than 1 and try again");
                throw new DataloadGeneratorException("Error - The dataload version inserted [ " + dataloadVersion + " ] is not allowed. Valorize that parameter with an integer grater than 1 and try again");
            }
            Integer currentDtVersion = new Integer(dataloadVersion);
            if(currentDtVersion.equals(0)){
                logger.severe("Error - The dataload version inserted [ " + dataloadVersion + " ] must be greater or equal than 1.  Put a right versione value and try again");
                throw new DataloadGeneratorException("Error - The dataload version inserted [ " + dataloadVersion + " ] must be greater or equal than 1.  Put a right versione value and try again");
            }
        }catch (java.lang.NumberFormatException numFormatEx){
            logger.severe("Error - The dataload version inserted [ " + dataloadVersion + " ] is not NUMBER. Put a right version value and try again");
            throw new DataloadGeneratorException("Error - The dataload version inserted [ " + dataloadVersion + " ] is not NUMBER. Put a right version value and try again. Exception: " + numFormatEx);
        }
    }

    /**
     *
     * This method takes from file a workflow and give it to the caller as String object
     *
     * @param filePath
     * @return
     * @throws DataloadGeneratorException
     */
    public static String getWorkflowAsStringFromTemplateFile(String filePath) throws DataloadGeneratorException {

        validateStringValueBySpecialCharPresence(filePath, SPECIAL_CHAR_FILEPATH);
        File file = new File(filePath);

        if(Files.notExists(file.toPath())){
            logger.severe("Error - The file doesn't exist: File: [" + filePath +"]. ");
            throw new DataloadGeneratorException("Error - The file doesn't exist: File: [" + filePath +"]. ");
        }

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance();
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<String> wfObject = (JAXBElement<String>) jaxbUnmarshaller.unmarshal(file);

            return wfObject.getValue();
        } catch (JAXBException e) {
            logger.severe("Error - " + e);
            throw new DataloadGeneratorException("Error - " + e);
        }
    }
}