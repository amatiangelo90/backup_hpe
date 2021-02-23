package com.hpe.dataload.generator.orm.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TbsPatternSavedTEntityPK implements Serializable {
    private String clazz;
    private String isUcpe;
    private String problemType;
    private String rgType;
    private String segment;
    private String service;
    private String technology;
    private String solutionName;

    @Column(name = "CLASS")
    @Id
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Column(name = "IS_UCPE")
    @Id
    public String getIsUcpe() {
        return isUcpe;
    }

    public void setIsUcpe(String isUcpe) {
        this.isUcpe = isUcpe;
    }

    @Column(name = "PROBLEM_TYPE")
    @Id
    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    @Column(name = "RG_TYPE")
    @Id
    public String getRgType() {
        return rgType;
    }

    public void setRgType(String rgType) {
        this.rgType = rgType;
    }

    @Column(name = "SEGMENT")
    @Id
    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    @Column(name = "SERVICE")
    @Id
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Column(name = "TECHNOLOGY")
    @Id
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    @Column(name = "SOLUTION_NAME")
    @Id
    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbsPatternSavedTEntityPK that = (TbsPatternSavedTEntityPK) o;
        return Objects.equals(clazz, that.clazz) &&
                Objects.equals(isUcpe, that.isUcpe) &&
                Objects.equals(problemType, that.problemType) &&
                Objects.equals(rgType, that.rgType) &&
                Objects.equals(segment, that.segment) &&
                Objects.equals(service, that.service) &&
                Objects.equals(technology, that.technology) &&
                Objects.equals(solutionName, that.solutionName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(clazz, isUcpe, problemType, rgType, segment, service, technology, solutionName);
    }
}
