package com.hpe.dataload.generator.orm.model;

public class PartialBlockDiagCodeMappingTEntity {
    private String exitCode;
    private String diagnosticCode;
    private String diagnosticCodeDesciption;

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

    public String getDiagnosticCodeDesciption() {
        return diagnosticCodeDesciption;
    }

    public void setDiagnosticCodeDesciption(String diagnosticCodeDesciption) {
        this.diagnosticCodeDesciption = diagnosticCodeDesciption;
    }
}
