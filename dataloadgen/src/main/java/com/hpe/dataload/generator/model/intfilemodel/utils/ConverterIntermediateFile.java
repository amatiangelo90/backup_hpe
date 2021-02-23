package com.hpe.dataload.generator.model.intfilemodel.utils;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.intergration.DataloadGenerator;
import com.hpe.dataload.generator.model.intfilemodel.DataSetItem;
import com.hpe.ted.modelconverter.ConverterException;
import com.hpe.ted.modelconverter.SlimToFatModelConverter;
import com.hpe.ted.modelconverter.fattoslim.FatToSlimModelConverter;

import static com.hpe.dataload.generator.utils.XmlSourceReader.getDataSetTypeObjectFromString;

public class ConverterIntermediateFile {


    public static String getFatFromSlimModelConverter(String intermediateFile) throws DataloadGeneratorException {
        if(isAlreadyIntermediateFatFile(intermediateFile)){
            return intermediateFile;
        }
        try {
            SlimToFatModelConverter slimToFatModelConverter = new SlimToFatModelConverter();
            System.out.println("Generate fat file from ["+ intermediateFile +"]");
            String convert = slimToFatModelConverter.convert(intermediateFile);
            return convert;
        } catch (Exception e) {
            System.out.println("Error " + e);
            throw new DataloadGeneratorException(e);
        }

    }
    public static String getSlimFromFatConverter(String intermediateFile) throws DataloadGeneratorException {
        if(!isAlreadyIntermediateFatFile(intermediateFile)){
            return intermediateFile;
        }
        try {
            FatToSlimModelConverter fatToSlimModelConverter = new FatToSlimModelConverter();
            String convert = fatToSlimModelConverter.convert(intermediateFile);
            return convert;
        } catch (ConverterException e) {
            throw new DataloadGeneratorException(e);
        }
    }

    private static boolean isAlreadyIntermediateFatFile(String intermediateFile) {
        try {
            getDataSetTypeObjectFromString(intermediateFile);
            return true;
        } catch (DataloadGeneratorException e) {
            return false;
        }

    }
}
