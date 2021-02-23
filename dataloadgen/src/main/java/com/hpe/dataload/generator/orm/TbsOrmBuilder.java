package com.hpe.dataload.generator.orm;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;

public class TbsOrmBuilder {

    private static TbsOrmImpl tbsOrm = null;

    public static TbsOrmImpl getTbsOrmImplementation() throws DataloadGeneratorException {
        if(tbsOrm == null){
            return new TbsOrmImpl();
        }else{
            return tbsOrm;
        }
    }
}
