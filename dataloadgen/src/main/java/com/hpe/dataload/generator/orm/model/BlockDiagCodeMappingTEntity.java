package com.hpe.dataload.generator.orm.model;

import java.sql.Timestamp;
import java.util.Objects;

public class BlockDiagCodeMappingTEntity {
    private String blockName;
    private String exitCode;
    private String diagnosticCode;
    private String diagnosticCodeDesc;
    private Timestamp recordInsertDate;
    private Timestamp recordUpdateDate;
    private String contactReason;
    private String contactReasonDetail;
    private String rocName;
    private String techIssue;
    private String resolution;
    private String closeReason;

    public BlockDiagCodeMappingTEntity() {}

    @Override
    public String toString() {
        return "BlockDiagCodeMappingTEntity{" +
                "blockName='" + blockName + '\'' +
                ", exitCode='" + exitCode + '\'' +
                ", diagnosticCode='" + diagnosticCode + '\'' +
                ", diagnosticCodeDesc='" + diagnosticCodeDesc + '\'' +
                ", recordInsertDate=" + recordInsertDate +
                ", recordUpdateDate=" + recordUpdateDate +
                ", contactReason='" + contactReason + '\'' +
                ", contactReasonDetail='" + contactReasonDetail + '\'' +
                ", rocName='" + rocName + '\'' +
                ", techIssue='" + techIssue + '\'' +
                ", resolution='" + resolution + '\'' +
                ", closeReason='" + closeReason + '\'' +
                '}';
    }

    public BlockDiagCodeMappingTEntity(String blockName) {
        this.blockName = blockName;
    }

    public BlockDiagCodeMappingTEntity(String blockName, String exitCode, String diagnosticCode, String diagnosticCodeDesc, Timestamp recordInsertDate, Timestamp recordUpdateDate) {
        this.blockName = blockName;
        this.exitCode = exitCode;
        this.diagnosticCode = diagnosticCode;
        this.diagnosticCodeDesc = diagnosticCodeDesc;
        this.recordInsertDate = recordInsertDate;
        this.recordUpdateDate = recordUpdateDate;
    }

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

    public String getDiagnosticCode() {
        return diagnosticCode;
    }

    public void setDiagnosticCode(String diagnosticCode) {
        this.diagnosticCode = diagnosticCode;
    }

    public String getDiagnosticCodeDesc() {
        return diagnosticCodeDesc;
    }

    public void setDiagnosticCodeDesc(String diagnosticCodeDesc) {
        this.diagnosticCodeDesc = diagnosticCodeDesc;
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

    public String getContactReason() {
        return contactReason;
    }

    public void setContactReason(String contactReason) {
        this.contactReason = contactReason;
    }

    public String getContactReasonDetail() {
        return contactReasonDetail;
    }

    public void setContactReasonDetail(String contactReasonDetail) {
        this.contactReasonDetail = contactReasonDetail;
    }

    public String getRocName() {
        return rocName;
    }

    public void setRocName(String rocName) {
        this.rocName = rocName;
    }

    public String getTechIssue() {
        return techIssue;
    }

    public void setTechIssue(String techIssue) {
        this.techIssue = techIssue;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockDiagCodeMappingTEntity that = (BlockDiagCodeMappingTEntity) o;
        return Objects.equals(blockName, that.blockName) &&
                Objects.equals(exitCode, that.exitCode) &&
                Objects.equals(diagnosticCode, that.diagnosticCode) &&
                Objects.equals(diagnosticCodeDesc, that.diagnosticCodeDesc) &&
                Objects.equals(recordInsertDate, that.recordInsertDate) &&
                Objects.equals(recordUpdateDate, that.recordUpdateDate) &&
                Objects.equals(contactReason, that.contactReason) &&
                Objects.equals(contactReasonDetail, that.contactReasonDetail) &&
                Objects.equals(rocName, that.rocName) &&
                Objects.equals(techIssue, that.techIssue) &&
                Objects.equals(resolution, that.resolution) &&
                Objects.equals(closeReason, that.closeReason);
    }

    @Override
    public int hashCode() {

        return Objects.hash(blockName, exitCode, diagnosticCode, diagnosticCodeDesc, recordInsertDate, recordUpdateDate, contactReason, contactReasonDetail, rocName, techIssue, resolution, closeReason);
    }

}
