package com.hpe.dataload.generator.intergration;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.engine.Builder;

public class DataloadGeneratorImpl implements DataloadGenerator {

    @Override
    public DataloadGeneratorResult generateDataload(DataloadGeneratorInput dataloadGenInput) throws DataloadGeneratorException {

        return Builder.generateDataloadFromXmlFile(
                dataloadGenInput.getIntermediateXml(),
                true,
                true,
                dataloadGenInput.getTedVersion(),
                dataloadGenInput.getTedSolution(),
                dataloadGenInput.getDescription(),
                dataloadGenInput.getOperatornName(),
                dataloadGenInput.getDataloadVersion(),
                dataloadGenInput.getConfiguration().getOutputPath());
    }
}
