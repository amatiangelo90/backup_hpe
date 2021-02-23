package com.hpe.automatization.test.testSuiteDataloadService;

import com.hpe.dataload.generator.engine.GroupMergeEngine;
import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.orm.model.TbsPatternSavedTEntity;
import org.junit.Test;
import java.util.logging.Logger;
import java.util.List;

import static com.hpe.automatization.test.utils.TestUtils.getTbsPatternSavedTEntityList;
import static com.hpe.dataload.generator.utils.WorkflowTemplateUtils.buildWorkflowToDriveProcessThroughtGroups;
import static com.hpe.dataload.generator.utils.WorkflowTemplateUtils.saveWfDriveGroupAsXmlFile;

public class TestMergeGroupEngine {

    private static final String TEMPLATE_WF_PATH = "..\\dataloadgen\\src\\main\\resources\\nextGroupTemplates\\template_wf_drive_group.txt";
    private static Logger logger = Logger.getLogger(TestMergeGroupEngine.class.getName());
    private static final String TBS_SELF_SOLUTION = "TBS_SELF";
    private GroupMergeEngine groupMergeEngine = null;

    private GroupMergeEngine getGroupMergeEngine() {
        if(groupMergeEngine == null){
            return new GroupMergeEngine();
        }else{
            return this.groupMergeEngine;
        }
    }


    @Test
    public void testGroupMergeEngine() throws DataloadGeneratorException {

        groupMergeEngine = getGroupMergeEngine();
        logger.info("Retrieving tbsPatternSavedEntities by solution [" + TBS_SELF_SOLUTION + "]");
        List<TbsPatternSavedTEntity> tbsPatternSavedTEntities = getTbsPatternSavedTEntityList(TBS_SELF_SOLUTION);
        System.out.println("########### TbsPatternSavedTEntity List ############\n");
        for(TbsPatternSavedTEntity tbsPatternSavedTItem : tbsPatternSavedTEntities){
            System.out.println("  - " + tbsPatternSavedTItem.toString());
        }
        System.out.println("\n########### TbsPatternSavedTEntity List End ############");

//        List<TbsPatternSavedTEntityOrderWrap> tbsPatternSavedTEntityOrderWrapList = buildtbsPatternSavedTEntitiesWrap(tbsPatternSavedTEntities);

//        groupMergeEngine.mergeGroup(tbsPatternSavedTEntityOrderWrapList);
    }

    @Test
    public void testGetWorkflowAsStringFromTemplateFile() throws DataloadGeneratorException {
        String s = buildWorkflowToDriveProcessThroughtGroups("cas", "asdas", "XXX", "XXX");
        System.out.println(s);
        saveWfDriveGroupAsXmlFile(s, "aaa", "ccc");

    }

}
