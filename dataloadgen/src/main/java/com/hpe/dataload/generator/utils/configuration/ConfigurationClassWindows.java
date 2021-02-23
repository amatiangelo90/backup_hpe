package com.hpe.dataload.generator.utils.configuration;

public class ConfigurationClassWindows extends ConfigurationClass {

    static private String actionDetailsPathFile = "C:\\Users\\50050802\\Desktop\\Progetti\\DATALOAD_DEV_BRANCH\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\main\\resources\\actionsConfiguration.json";
    static private String PATH_ACTIONS = "C:\\Users\\50050802\\Desktop\\Progetti\\DATALOAD_DEV_BRANCH\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\main\\resources\\dataload_ToRetrieveActions.xml";
    static private String PATH_WF_SAVE = "C:\\Users\\50050802\\Desktop\\Progetti\\DATALOAD_DEV_BRANCH\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\main\\resources\\";
    static private String PATH_GENERATED_SAVE = "C:\\Users\\50050802\\Desktop\\Dataload\\";
    static private String PATH_GENERATED_EXTERNAL = "C:\\Users\\50050802\\Desktop\\Progetti\\DATALOAD_DEV_BRANCH\\ted\\src\\OSS_TED\\artifacts\\dataloadgen\\src\\main\\resources\\dataload_RES_OLD.xml";

    public ConfigurationClassWindows() {
        super(actionDetailsPathFile, PATH_ACTIONS, PATH_WF_SAVE, PATH_GENERATED_SAVE, PATH_GENERATED_EXTERNAL);
    }
}

