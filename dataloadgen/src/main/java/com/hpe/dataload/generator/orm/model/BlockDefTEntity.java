package com.hpe.dataload.generator.orm.model;

import java.sql.Timestamp;
import java.util.Objects;

public class BlockDefTEntity {
    private String blockName;
    private String blockDesc;
    private Timestamp recordInsertDate;
    private Timestamp recordUpdateDate;

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

    public BlockDefTEntity(String blockName, String blockDesc, Timestamp recordInsertDate, Timestamp recordUpdateDate) {
        this.blockName = blockName;
        this.blockDesc = blockDesc;
        this.recordInsertDate = recordInsertDate;
        this.recordUpdateDate = recordUpdateDate;
    }

    public BlockDefTEntity(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockDefTEntity that = (BlockDefTEntity) o;
        return Objects.equals(blockName, that.blockName) &&
                Objects.equals(blockDesc, that.blockDesc) &&
                Objects.equals(recordInsertDate, that.recordInsertDate) &&
                Objects.equals(recordUpdateDate, that.recordUpdateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(blockName, blockDesc, recordInsertDate, recordUpdateDate);
    }

    @Override
    public String toString() {
        return "BlockDefTEntity{" +
                "blockName='" + blockName + '\'' +
                ", blockDesc='" + blockDesc + '\'' +
                ", recordInsertDate=" + recordInsertDate +
                ", recordUpdateDate=" + recordUpdateDate +
                '}';
    }
}
