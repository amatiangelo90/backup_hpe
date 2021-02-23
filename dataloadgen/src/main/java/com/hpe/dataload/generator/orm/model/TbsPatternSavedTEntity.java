package com.hpe.dataload.generator.orm.model;

import java.sql.Timestamp;

@javax.persistence.Entity
@javax.persistence.Table(name = "TBS_PATTERN_SAVED_T", schema = "TD_UDB_OWN", catalog = "")
@javax.persistence.IdClass(TbsPatternSavedTEntityPK.class)
public class TbsPatternSavedTEntity {
    private String clazz;
    private String isUcpe;
    private String problemType;
    private String rgType;
    private String segment;
    private String service;
    private String technology;
    private String intermediateXml;
    private String operator;
    private String isContainedInLastDataload;
    private String toDelete;
    private Timestamp recordInsertDate;
    private Timestamp recordUpdateDate;
    private String solutionName;

    @Override
    public String toString() {
        return "TbsPatternSavedTEntity{" +
                "clazz='" + clazz + '\'' +
                ", isUcpe='" + isUcpe + '\'' +
                ", problemType='" + problemType + '\'' +
                ", rgType='" + rgType + '\'' +
                ", segment='" + segment + '\'' +
                ", service='" + service + '\'' +
                ", technology='" + technology + '\'' +
                ", intermediateXml='" + intermediateXml + '\'' +
                ", operator='" + operator + '\'' +
                ", solutionName='" + solutionName + '\'' +
                '}';
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "CLASS")
    public java.lang.String getClazz() {
        return clazz;
    }

    public void setClazz(java.lang.String clazz) {
        this.clazz = clazz;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "IS_UCPE")
    public java.lang.String getIsUcpe() {
        return isUcpe;
    }

    public void setIsUcpe(java.lang.String isUcpe) {
        this.isUcpe = isUcpe;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "PROBLEM_TYPE")
    public java.lang.String getProblemType() {
        return problemType;
    }

    public void setProblemType(java.lang.String problemType) {
        this.problemType = problemType;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "RG_TYPE")
    public java.lang.String getRgType() {
        return rgType;
    }

    public void setRgType(java.lang.String rgType) {
        this.rgType = rgType;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "SEGMENT")
    public java.lang.String getSegment() {
        return segment;
    }

    public void setSegment(java.lang.String segment) {
        this.segment = segment;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "SERVICE")
    public java.lang.String getService() {
        return service;
    }

    public void setService(java.lang.String service) {
        this.service = service;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "TECHNOLOGY")
    public java.lang.String getTechnology() {
        return technology;
    }

    public void setTechnology(java.lang.String technology) {
        this.technology = technology;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "INTERMEDIATE_XML")
    public java.lang.String getIntermediateXml() {
        return intermediateXml;
    }

    public void setIntermediateXml(java.lang.String intermediateXml) {
        this.intermediateXml = intermediateXml;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "OPERATOR")
    public java.lang.String getOperator() {
        return operator;
    }

    public void setOperator(java.lang.String operator) {
        this.operator = operator;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "IS_CONTAINED_IN_LAST_DATALOAD")
    public java.lang.String getIsContainedInLastDataload() {
        return isContainedInLastDataload;
    }

    public void setIsContainedInLastDataload(java.lang.String isContainedInLastDataload) {
        this.isContainedInLastDataload = isContainedInLastDataload;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "TO_DELETE")
    public java.lang.String getToDelete() {
        return toDelete;
    }

    public void setToDelete(java.lang.String toDelete) {
        this.toDelete = toDelete;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "RECORD_INSERT_DATE")
    public java.sql.Timestamp getRecordInsertDate() {
        return recordInsertDate;
    }

    public void setRecordInsertDate(java.sql.Timestamp recordInsertDate) {
        this.recordInsertDate = recordInsertDate;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "RECORD_UPDATE_DATE")
    public java.sql.Timestamp getRecordUpdateDate() {
        return recordUpdateDate;
    }

    public void setRecordUpdateDate(java.sql.Timestamp recordUpdateDate) {
        this.recordUpdateDate = recordUpdateDate;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "SOLUTION_NAME")
    public java.lang.String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(java.lang.String solutionName) {
        this.solutionName = solutionName;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        TbsPatternSavedTEntity that = (TbsPatternSavedTEntity) object;

        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;
        if (isUcpe != null ? !isUcpe.equals(that.isUcpe) : that.isUcpe != null) return false;
        if (problemType != null ? !problemType.equals(that.problemType) : that.problemType != null) return false;
        if (rgType != null ? !rgType.equals(that.rgType) : that.rgType != null) return false;
        if (segment != null ? !segment.equals(that.segment) : that.segment != null) return false;
        if (service != null ? !service.equals(that.service) : that.service != null) return false;
        if (technology != null ? !technology.equals(that.technology) : that.technology != null) return false;
        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        result = 31 * result + (isUcpe != null ? isUcpe.hashCode() : 0);
        result = 31 * result + (problemType != null ? problemType.hashCode() : 0);
        result = 31 * result + (rgType != null ? rgType.hashCode() : 0);
        result = 31 * result + (segment != null ? segment.hashCode() : 0);
        result = 31 * result + (service != null ? service.hashCode() : 0);
        result = 31 * result + (technology != null ? technology.hashCode() : 0);
        result = 31 * result + (intermediateXml != null ? intermediateXml.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (isContainedInLastDataload != null ? isContainedInLastDataload.hashCode() : 0);
        result = 31 * result + (toDelete != null ? toDelete.hashCode() : 0);
        result = 31 * result + (recordInsertDate != null ? recordInsertDate.hashCode() : 0);
        result = 31 * result + (recordUpdateDate != null ? recordUpdateDate.hashCode() : 0);
        result = 31 * result + (solutionName != null ? solutionName.hashCode() : 0);
        return result;
    }
}
