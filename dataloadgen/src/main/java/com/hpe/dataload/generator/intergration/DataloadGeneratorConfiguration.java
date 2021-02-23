package com.hpe.dataload.generator.intergration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataloadGeneratorConfiguration {
	private String outputPath;
}
