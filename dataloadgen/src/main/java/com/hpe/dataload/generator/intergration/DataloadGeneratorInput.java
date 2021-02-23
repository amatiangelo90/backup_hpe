package com.hpe.dataload.generator.intergration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataloadGeneratorInput {
	private String intermediateXml;
	private String operatornName;
	private String description;
	private String dataloadVersion;
	private String tedSolution;
	private String tedVersion;
	private DataloadGeneratorConfiguration configuration;
}

