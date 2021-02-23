package com.hpe.dataload.generator.intergration;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;

public interface DataloadGenerator {

	DataloadGeneratorResult generateDataload(DataloadGeneratorInput input) throws DataloadGeneratorException;
}
