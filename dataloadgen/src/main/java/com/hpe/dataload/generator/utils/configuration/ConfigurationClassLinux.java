package com.hpe.dataload.generator.utils.configuration;

public class ConfigurationClassLinux extends ConfigurationClass {


    static private String actionDetailsPathFile = "/opt/dataload-gen-files/actionsConfiguration.json";
    static private String PATH_ACTIONS = "/opt/dataload-gen-files/dataload_ToRetrieveActions.xml";
    static private String PATH_WF_SAVE = "/opt/dataload-gen-files/generated/";
    static private String PATH_GENERATED_SAVE = "/opt/dataload-gen-files/generated/";
    static private String PATH_GENERATED_EXTERNAL = "";


    public ConfigurationClassLinux() {
        super(actionDetailsPathFile, PATH_ACTIONS, PATH_WF_SAVE, PATH_GENERATED_SAVE, PATH_GENERATED_EXTERNAL);
    }
}
