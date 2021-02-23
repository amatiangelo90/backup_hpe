package com.hpe.dataload.generator.orm.model;

import java.io.Serializable;
import java.util.Objects;

public class BlockDiagCodeMappingTEntityPK implements Serializable {
    private String blockName;
    private String exitCode;

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getExitCode() {
        return exitCode;
    }

    public void setExitCode(String exitCode) {
        this.exitCode = exitCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockDiagCodeMappingTEntityPK that = (BlockDiagCodeMappingTEntityPK) o;
        return Objects.equals(blockName, that.blockName) &&
                Objects.equals(exitCode, that.exitCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(blockName, exitCode);
    }
}
