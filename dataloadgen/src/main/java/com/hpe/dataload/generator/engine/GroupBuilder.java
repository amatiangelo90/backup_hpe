package com.hpe.dataload.generator.engine;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.model.dataloadmodel.GroupType;
import com.hpe.dataload.generator.model.dataloadmodel.TD;
import com.hpe.dataload.generator.model.intfilemodel.DataSetItem;
import com.hpe.dataload.generator.model.intfilemodel.ItemType;
import com.hpe.dataload.generator.utils.EnumTedVersion;

import static com.hpe.dataload.generator.engine.Builder.buildDataload;
import static com.hpe.dataload.generator.model.intfilemodel.utils.ConverterIntermediateFile.getFatFromSlimModelConverter;
import static com.hpe.dataload.generator.utils.DataloadGenUtils.groupTypeObjectToString;
import static com.hpe.dataload.generator.utils.XmlSourceReader.getDataSetTypeObjectFromString;

public class GroupBuilder {

    public static String buildGroupFromIntermediateFile(String intermediateFile) throws DataloadGeneratorException {
        String intermediateFatFile;
        try{
            System.out.println("Try to convert intermediate file..");
            intermediateFatFile = getFatFromSlimModelConverter(intermediateFile);
        }catch (Exception e){
            System.out.println("Error - Unable to convert incoming intermediate file to FatIntermediate File. Try to use it as FatIntermediate File already..");
            intermediateFatFile = intermediateFile;
        }

        System.out.println("Build dataSetItem Object from intermediate fat file..");
        DataSetItem dataSetTypeObjectFromString = getDataSetTypeObjectFromString(intermediateFatFile);

        TD td = buildDataload(dataSetTypeObjectFromString,
                false,
                false,
                EnumTedVersion.version_8_1,
                "DEFAULT",
                "1",
                null,
                null,
                null);

        if(td.getGroups().getGroup().size() == 1){
            for(GroupType groupType : td.getGroups().getGroup()){
                groupType.setOrder(getGroupOrder(dataSetTypeObjectFromString));
                groupType.setName(getGroupName(dataSetTypeObjectFromString));
            }
            System.out.println("Group created with success");
            return groupTypeObjectToString(td.getGroups().getGroup().get(0));
        }else{
            System.out.println("Unable to manage a dataload with more than one group");
            throw new DataloadGeneratorException("Unable to manage a dataload with more than one group");
        }
    }

    private static int getGroupOrder(DataSetItem dataSetTypeObjectFromString) {
        try{
            for(ItemType itemType : dataSetTypeObjectFromString.getItem()){
                return Integer.parseInt(itemType.getPatternFilter().getClassValue());
            }
            return 0;
        }catch(Exception e) {
            System.out.println("Cannot obtain grop order from Item Type - Return defaul value 0");
            return 0;
        }
    }

    private static String getGroupName(DataSetItem dataSetTypeObjectFromString) {
        try{
            for(ItemType itemType : dataSetTypeObjectFromString.getItem()){
                return itemType.getPatternFilter().getTechnology();
            }
            return "NO_NAME";
        }catch(Exception e) {
            System.out.println("Cannot obtain grop order from Item Type - Return defaul value 0");
            return "NO_NAME";
        }
    }
}
