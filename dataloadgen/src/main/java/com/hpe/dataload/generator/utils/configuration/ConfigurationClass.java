package com.hpe.dataload.generator.utils.configuration;

public class ConfigurationClass {

    String actionDetailsPathFile;
    String PATH_DATALOAD;
    String PATH_WF_SAVE;
    String PATH_GENERATED_SAVE;
    String PATH_GENERATED_EXTERNAL;

    public ConfigurationClass(String actionDetailsPathFile, String PATH_DATALOAD, String PATH_WF_SAVE, String PATH_GENERATED_SAVE, String PATH_GENERATED_EXTERNAL) {
        this.actionDetailsPathFile = actionDetailsPathFile;
        this.PATH_DATALOAD = PATH_DATALOAD;
        this.PATH_WF_SAVE = PATH_WF_SAVE;
        this.PATH_GENERATED_SAVE = PATH_GENERATED_SAVE;
        this.PATH_GENERATED_EXTERNAL = PATH_GENERATED_EXTERNAL;
    }

    public String getActionDetailsPathFile() {
        return actionDetailsPathFile;
    }

    public String getPATH_DATALOAD() {
        return PATH_DATALOAD;
    }

    public String getPATH_WF_SAVE() {
        return PATH_WF_SAVE;
    }

    public String getPATH_GENERATED_SAVE() {
        return PATH_GENERATED_SAVE;
    }

    public String getPATH_GENERATED_EXTERNAL() {
        return PATH_GENERATED_EXTERNAL;
    }
}
