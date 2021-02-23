package com.hpe.automatization.test.utils;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.model.dataloadmodel.ActionType;
import com.hpe.dataload.generator.model.dataloadmodel.WorkflowTemplateType;
import com.hpe.dataload.generator.orm.TbsOrmImpl;
import com.hpe.dataload.generator.orm.model.BlockDefEntityWrap;
import com.hpe.dataload.generator.orm.model.BlockDefTEntity;
import com.hpe.dataload.generator.orm.model.BlockDiagCodeMappingTEntity;
import com.hpe.dataload.generator.orm.model.TbsPatternSavedTEntity;

import java.util.List;

public class TestUtils {

    private static TbsOrmImpl tbsOrm = null;

    public static List<TbsPatternSavedTEntity> getTbsPatternSavedTEntityList(String solution) throws DataloadGeneratorException {

        TbsOrmImpl tbsOrmImplInstance = getTbsOrmImplInstance();
        List<TbsPatternSavedTEntity> tbsPatternSavedTEntities =
                tbsOrmImplInstance.retrieveTbsPatternSavedTEnt(solution);
        return tbsPatternSavedTEntities;
    }

    public static List<BlockDiagCodeMappingTEntity> getTbsBlockCodeDiagMappingTEntity() throws DataloadGeneratorException {

        TbsOrmImpl tbsOrmImplInstance = getTbsOrmImplInstance();
        List<BlockDiagCodeMappingTEntity> blocksList =
                tbsOrmImplInstance.retrieveBlockDiagCodeMapping();
        return blocksList;
    }

    public static List<BlockDefEntityWrap> getTbsBlockEntity() throws DataloadGeneratorException {

        TbsOrmImpl tbsOrmImplInstance = getTbsOrmImplInstance();

        List<BlockDefEntityWrap> blockDefEntityWraps = tbsOrmImplInstance.retrieveBlockDefTEntity();
        return blockDefEntityWraps;
    }

    public static void deleteBlockDiagConf(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException {
        TbsOrmImpl tbsOrmImplInstance = getTbsOrmImplInstance();
        tbsOrmImplInstance.deleteBlockDiagCodeMapping(blockDefEntityWrap);
    }

    public static void createBlockDiagConf(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException {
        TbsOrmImpl tbsOrmImplInstance = getTbsOrmImplInstance();
        tbsOrmImplInstance.createBlockDiagCodeMapping(blockDefEntityWrap);
    }

    public static void updateBlockDiagConf(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException {
        TbsOrmImpl tbsOrmImplInstance = getTbsOrmImplInstance();
        tbsOrmImplInstance.editBlockDiagCodeMapping(blockDefEntityWrap);
    }

    private static TbsOrmImpl getTbsOrmImplInstance() throws DataloadGeneratorException {
        if(tbsOrm == null){
            return new TbsOrmImpl();
        }else{
            return tbsOrm;
        }
    }
}
