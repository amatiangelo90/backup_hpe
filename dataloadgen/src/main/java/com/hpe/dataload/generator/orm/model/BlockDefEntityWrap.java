package com.hpe.dataload.generator.orm.model;

import java.sql.Timestamp;
import java.util.List;

public class BlockDefEntityWrap {

    private String blockName;
    private String blockDesc;
    private Timestamp recordInsertDate;
    private Timestamp recordUpdateDate;
    private List<PartialBlockDiagCodeMappingTEntity> exitCodeList;

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockDesc() {
        return blockDesc;
    }

    public void setBlockDesc(String blockDesc) {
        this.blockDesc = blockDesc;
    }

    public Timestamp getRecordInsertDate() {
        return recordInsertDate;
    }

    public void setRecordInsertDate(Timestamp recordInsertDate) {
        this.recordInsertDate = recordInsertDate;
    }

    public Timestamp getRecordUpdateDate() {
        return recordUpdateDate;
    }

    public void setRecordUpdateDate(Timestamp recordUpdateDate) {
        this.recordUpdateDate = recordUpdateDate;
    }

    public List<PartialBlockDiagCodeMappingTEntity> getExitCodeList() {
        return exitCodeList;
    }

    public void setExitCodeList(List<PartialBlockDiagCodeMappingTEntity> exitCodeList) {
        this.exitCodeList = exitCodeList;
    }
}
