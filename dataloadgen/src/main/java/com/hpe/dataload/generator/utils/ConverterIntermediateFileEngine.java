package com.hpe.dataload.generator.utils;

import com.hpe.ted.modelconverter.ConverterException;
import com.hpe.ted.modelconverter.SlimToFatModelConverter;
import com.hpe.ted.modelconverter.fattoslim.FatToSlimModelConverter;

public class ConverterIntermediateFileEngine {

    SlimToFatModelConverter slimToFatModelConverter = null;
    FatToSlimModelConverter fatToSlimModelConverter = null;

    public ConverterIntermediateFileEngine() {
        if(slimToFatModelConverter == null){
            slimToFatModelConverter = new SlimToFatModelConverter();
        }
        if(fatToSlimModelConverter == null) {
            fatToSlimModelConverter = new FatToSlimModelConverter();
        }
    }

    public String performSlimToFatModelConverter(String intermetiateSlimFile) throws ConverterException {
        return slimToFatModelConverter.convert(intermetiateSlimFile);
    }

    public String performFatToSlimModelConverter(String intermetiateFatFile) throws ConverterException {
        return fatToSlimModelConverter.convert(intermetiateFatFile);
    }
}
