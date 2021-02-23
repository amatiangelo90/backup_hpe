package com.hpe.dataload.generator.intergration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataloadGeneratorResult {

	private String dataloadId;
	private String dataloadXml;

}
