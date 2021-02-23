package com.hpe.dataload.generator.orm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import com.hpe.dataload.generator.model.dataloadmodel.*;
import com.hpe.dataload.generator.orm.model.*;
import com.hpe.dataload.generator.utils.DataloadGenUtils;
import com.hpe.dataload.generator.utils.configuration.ConfigurationClass;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.hpe.dataload.generator.utils.DataloadGenUtils.*;

public class TbsOrmImpl implements TbsOrmInterface {


    private static final SessionFactory ourSessionFactory;
    private static Query query;

    private static ConfigurationClass confClassImpl;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
            confClassImpl = getConfigurationFile();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    //    @Override
//    public List<TbsPatternSavedTEntity> retrieveTbsPatternSavedTEnt(String solution) throws DataloadGeneratorException {
//        final Session session = getSession();
//        List<TbsPatternSavedTEntity> tbsPatternSavedTEntities = new ArrayList<>();
//        try {
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                if(solution == null || EMPTY_STRING.equalsIgnoreCase(solution)){
//                    query = session.createQuery("FROM " + entityName);
//                }else{
//                    System.out.println("Querying from " + entityName + " with solution name " + solution);
//
//                    query = session.createQuery("FROM " + entityName + " WHERE SOLUTION_NAME=: sol");
//                    query.setParameter("sol", solution);
//                }
//
//                for (Object o : query.list()) {
//                    if(o instanceof TbsPatternSavedTEntity){
//                        if("td_admin".equalsIgnoreCase(((TbsPatternSavedTEntity)o).getOperator())) {
//                            tbsPatternSavedTEntities.add((TbsPatternSavedTEntity) o);
//                        }
//                    }
//                }
//                return tbsPatternSavedTEntities;
//            }
//        } catch(Exception e){
//            throw new DataloadGeneratorException(e);
//        }finally {
//            session.close();
//        }
//        return null;
//    }
    @Override
    public List<TbsPatternSavedTEntity> retrieveTbsPatternSavedTEnt(String solution) throws DataloadGeneratorException {
        final Session session = getSession();
        try{
            List<TbsPatternSavedTEntity> tbsPatternSavedTEntities = new ArrayList<>();

            System.out.println("Querying from ");

            Query query=session.createQuery("from TbsPatternSavedTEntity");
            for (Object o : query.list()) {
                if(o instanceof TbsPatternSavedTEntity){
                    if("td_admin".equalsIgnoreCase(((TbsPatternSavedTEntity)o).getOperator())) {
                        tbsPatternSavedTEntities.add((TbsPatternSavedTEntity) o);
                    }
                }
            }
            tbsPatternSavedTEntities.addAll(retrieveGroupsAsTbsPatternSavedEntityFromDataload());
            return tbsPatternSavedTEntities;
        }catch(Exception e){
            throw new DataloadGeneratorException("Error - Retrieve Tbs Pattern saved. " + e);
        }finally {
            session.close();
        }
    }


    @Override
    public List<BlockDiagCodeMappingTEntity> retrieveBlockDiagCodeMapping() throws DataloadGeneratorException {
        final Session session = getSession();
        try{
            List<BlockDiagCodeMappingTEntity> blockDiagCodeMappingTEntities = new ArrayList<>();

            System.out.println("Querying blockDiagCodeMappingTEntities...");

            Query query=session.createQuery("from BlockDiagCodeMappingTEntity");
            for (Object o : query.list()) {
                if(o instanceof BlockDiagCodeMappingTEntity){
                    blockDiagCodeMappingTEntities.add((BlockDiagCodeMappingTEntity) o);
                }
            }
            return blockDiagCodeMappingTEntities;
        }catch(Exception e){
            throw new DataloadGeneratorException("Error - Retrieve Blocks saved. " + e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<BlockDefEntityWrap> retrieveBlockDefTEntity() throws DataloadGeneratorException {
        final Session session = getSession();
        try{
            List<BlockDefTEntity> blockDefTEntities = new ArrayList<>();

            System.out.println("Querying blockDefTEntity...");

            Query query=session.createQuery("from BlockDefTEntity");
            for (Object o : query.list()) {
                if(o instanceof BlockDefTEntity){
                    blockDefTEntities.add((BlockDefTEntity) o);
                }
            }

            System.out.println("Querying blockDiagCodeMappingTEntities...");

            List<BlockDiagCodeMappingTEntity> blockDiagCodeMappingTEntities = new ArrayList<>();
            Query queryBlockDiagCodeMapping = session.createQuery("from BlockDiagCodeMappingTEntity");
            for (Object o : queryBlockDiagCodeMapping.list()) {
                if(o instanceof BlockDiagCodeMappingTEntity){
                    blockDiagCodeMappingTEntities.add((BlockDiagCodeMappingTEntity) o);
                }
            }
            List<BlockDefEntityWrap> blockDefEntityWrapsList = new ArrayList<>();
            for(BlockDefTEntity blockDefTEntity : blockDefTEntities){
                BlockDefEntityWrap blockDefEntityWrap = new BlockDefEntityWrap();
                blockDefEntityWrap.setBlockName(blockDefTEntity.getBlockName());
                blockDefEntityWrap.setBlockDesc(blockDefTEntity.getBlockDesc());

                blockDefEntityWrap.setRecordInsertDate(blockDefTEntity.getRecordInsertDate());
                blockDefEntityWrap.setRecordUpdateDate(blockDefTEntity.getRecordUpdateDate());

                blockDefEntityWrap.setExitCodeList(getExitCodeListFromBlockDiagCodeMappingTEntitiesList(blockDiagCodeMappingTEntities, blockDefTEntity.getBlockName()));
                blockDefEntityWrapsList.add(blockDefEntityWrap);

            }

            return blockDefEntityWrapsList;

        }catch(Exception e){
            throw new DataloadGeneratorException("Error - Retrieve Blocks saved. " + e);
        }finally {
            session.close();
        }
    }

    @Override
    public void uploadBlockDefEntity(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException {
        final Session session = getSession();
        if(blockDefEntityWrap == null
                || blockDefEntityWrap.getBlockName() == null
                || EMPTY_STRING.equalsIgnoreCase(blockDefEntityWrap.getBlockName())){
            throw new DataloadGeneratorException("Error - Cannot upload block into db. Object is null or block Name is missing");
        }
        if(blockDefEntityWrap.getExitCodeList().isEmpty() || blockDefEntityWrap.getExitCodeList().size() == 0) {
            throw new DataloadGeneratorException("Error - Cannot upload block into db. Exit Code list cannot be empty");
        }
        System.out.println("Updating block [" + blockDefEntityWrap.getBlockName() + "]");

        deleteBlockDiagCodeMapping(blockDefEntityWrap);
    }

    @Override
    public void createBlockDefConfigurationOnDb(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException {
        final Session session = getSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if(blockDefEntityWrap == null
                    || blockDefEntityWrap.getBlockName() == null
                    || EMPTY_STRING.equalsIgnoreCase(blockDefEntityWrap.getBlockName())){
                throw new DataloadGeneratorException("Error - Cannot create block into db. Object is null or block Name is missing");
            }
            if(blockDefEntityWrap.getExitCodeList().isEmpty() || blockDefEntityWrap.getExitCodeList().size() == 0) {
                throw new DataloadGeneratorException("Error - Cannot create block into db. Exit Code list cannot be empty");
            }
            Query query=session.createQuery("from BlockDefTEntity");

            for (Object o : query.list()) {
                if(o instanceof BlockDefTEntity){
                    if(blockDefEntityWrap.getBlockName().equalsIgnoreCase(((BlockDefTEntity) o).getBlockName())){
                        throw new DataloadGeneratorException("Error - Block with name [" + blockDefEntityWrap.getBlockName() + "] is already present into DB");
                    }
                }
            }
            session.save(new BlockDefTEntity(blockDefEntityWrap.getBlockName(),
                    blockDefEntityWrap.getBlockDesc(),
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())));

            for(PartialBlockDiagCodeMappingTEntity partialBlockDiagCodeMappingTEntity : blockDefEntityWrap.getExitCodeList()){
                session.save(new BlockDiagCodeMappingTEntity(
                        blockDefEntityWrap.getBlockName(),
                        partialBlockDiagCodeMappingTEntity.getExitCode(),
                        partialBlockDiagCodeMappingTEntity.getDiagnosticCode(),
                        partialBlockDiagCodeMappingTEntity.getDiagnosticCodeDesciption(),
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())));
            }
            transaction.commit();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot save block configuration. " + e);
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public void deleteBlockConfigurationFromDB(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException {
        final Session session = getSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if(blockDefEntityWrap == null
                    || blockDefEntityWrap.getBlockName() == null
                    || EMPTY_STRING.equalsIgnoreCase(blockDefEntityWrap.getBlockName())){
                throw new DataloadGeneratorException("Error - Cannot delete block from db. Object is null or block Name is missing");
            }

            System.out.println("Querying blockDiagCodeMappingTEntities...");
            Query queryBlockDiagCodeMapping = session.createQuery("from BlockDiagCodeMappingTEntity");
            for (Object o : queryBlockDiagCodeMapping.list()) {
                if(o instanceof BlockDiagCodeMappingTEntity){
                    if(((BlockDiagCodeMappingTEntity) o).getBlockName().equalsIgnoreCase(blockDefEntityWrap.getBlockName())){
                        session.delete(o);
                    }
                }
            }
            Query query=session.createQuery("from BlockDefTEntity");

            for (Object o : query.list()) {
                if(o instanceof BlockDefTEntity){
                    if(blockDefEntityWrap.getBlockName().equalsIgnoreCase(((BlockDefTEntity) o).getBlockName())){
                        session.delete(o);
                    }
                }
            }

            transaction.commit();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
            throw new DataloadGeneratorException(e);
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public void deleteBlockDiagCodeMapping(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException {
        final Session session = getSession();
        Transaction transaction = null;
        try{
            transaction = session.getTransaction();
            transaction.begin();
            if(blockDefEntityWrap == null
                    || blockDefEntityWrap.getBlockName() == null
                    || EMPTY_STRING.equalsIgnoreCase(blockDefEntityWrap.getBlockName())){
                throw new DataloadGeneratorException("Error - Cannot delete block diag code into db. Object is null or block Name is missing");
            }

            System.out.println("Querying blockDiagCodeMappingTEntities...");

            List<BlockDiagCodeMappingTEntity> blockDiagCodeMappingTEntities = new ArrayList<>();
            Query queryBlockDiagCodeMapping = session.createQuery("from BlockDiagCodeMappingTEntity");
            for (Object o : queryBlockDiagCodeMapping.list()) {
                if(o instanceof BlockDiagCodeMappingTEntity){
                    blockDiagCodeMappingTEntities.add((BlockDiagCodeMappingTEntity) o);
                }
            }
            for(BlockDiagCodeMappingTEntity blockDiagCodeMappingTEntityToRemove : blockDiagCodeMappingTEntities){
                if(blockDiagCodeMappingTEntityToRemove.getBlockName().equalsIgnoreCase(blockDefEntityWrap.getBlockName())){
                    session.delete(blockDiagCodeMappingTEntityToRemove);
                }
            }
            System.out.println("Delete From Block diag code mapping the elements with block name [" + blockDefEntityWrap.getBlockName() + "]");

            transaction.commit();

        }catch(Exception e){
            transaction.rollback();
            throw new DataloadGeneratorException(e);
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public void editBlockDiagCodeMapping(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException {
        final Session session = getSession();
        Transaction transaction = null;
        System.out.println("Updating records with block name [" + blockDefEntityWrap.getBlockName() + "] on BLOCK_DIAG_CODE_MAPPING table");
        try{
            transaction = session.beginTransaction();

            if(blockDefEntityWrap == null
                    || blockDefEntityWrap.getBlockName() == null
                    || EMPTY_STRING.equalsIgnoreCase(blockDefEntityWrap.getBlockName())){
                throw new DataloadGeneratorException("Error - Cannot create block diag code into db. Object is null or block Name is missing");
            }
            if(blockDefEntityWrap.getExitCodeList() != null){
                if(blockDefEntityWrap.getExitCodeList().isEmpty() || blockDefEntityWrap.getExitCodeList().size() == 0) {
                    throw new DataloadGeneratorException("Error - Cannot create block diag cocde into db. Exit Code list cannot be empty");
                }
            }else{
                throw new DataloadGeneratorException("Error - Cannot create block diag cocde into db. Exit Code list cannot be empty");
            }

            List<BlockDiagCodeMappingTEntity> blockDiagCodeMappingTEntities = new ArrayList<>();
            Query queryBlockDiagCodeMapping = session.createQuery("from BlockDiagCodeMappingTEntity");
            for (Object o : queryBlockDiagCodeMapping.list()) {
                if(o instanceof BlockDiagCodeMappingTEntity){
                    blockDiagCodeMappingTEntities.add((BlockDiagCodeMappingTEntity) o);
                }
            }
            for(BlockDiagCodeMappingTEntity blockDiagCodeMappingTEntityToRemove : blockDiagCodeMappingTEntities){
                if(blockDiagCodeMappingTEntityToRemove.getBlockName().equalsIgnoreCase(blockDefEntityWrap.getBlockName())){
                    session.delete(blockDiagCodeMappingTEntityToRemove);
                }
            }
            System.out.println("Delete From Block diag code mapping the elements with block name [" + blockDefEntityWrap.getBlockName() + "]");
            for(PartialBlockDiagCodeMappingTEntity partialBlockDiagCodeMappingTEntity : blockDefEntityWrap.getExitCodeList()) {
                BlockDiagCodeMappingTEntity blockDiagCodeMappingTEntity = new BlockDiagCodeMappingTEntity(
                        blockDefEntityWrap.getBlockName(),
                        partialBlockDiagCodeMappingTEntity.getExitCode(),
                        partialBlockDiagCodeMappingTEntity.getDiagnosticCode(),
                        partialBlockDiagCodeMappingTEntity.getDiagnosticCodeDesciption(),
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis()));
                System.out.println("Saving - " +  blockDiagCodeMappingTEntity.toString());
                session.save(blockDiagCodeMappingTEntity);
            }
            transaction.commit();

        }catch(Exception e) {
            if(transaction != null)
                transaction.rollback();
            throw new DataloadGeneratorException(e);
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public void createBlockDiagCodeMapping(BlockDefEntityWrap blockDefEntityWrap) throws DataloadGeneratorException {
        final Session session = getSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            if(blockDefEntityWrap == null
                    || blockDefEntityWrap.getBlockName() == null
                    || EMPTY_STRING.equalsIgnoreCase(blockDefEntityWrap.getBlockName())){
                throw new DataloadGeneratorException("Error - Cannot create block diag code into db. Object is null or block Name is missing");
            }
            if(blockDefEntityWrap.getExitCodeList() != null){
                if(blockDefEntityWrap.getExitCodeList().isEmpty() || blockDefEntityWrap.getExitCodeList().size() == 0) {
                    throw new DataloadGeneratorException("Error - Cannot create block diag cocde into db. Exit Code list cannot be empty");
                }
            }else{
                throw new DataloadGeneratorException("Error - Cannot create block diag cocde into db. Exit Code list cannot be empty");
            }
            for(PartialBlockDiagCodeMappingTEntity partialBlockDiagCodeMappingTEntity : blockDefEntityWrap.getExitCodeList()) {
                BlockDiagCodeMappingTEntity blockDiagCodeMappingTEntity = new BlockDiagCodeMappingTEntity(
                        blockDefEntityWrap.getBlockName(),
                        partialBlockDiagCodeMappingTEntity.getExitCode(),
                        partialBlockDiagCodeMappingTEntity.getDiagnosticCode(),
                        partialBlockDiagCodeMappingTEntity.getDiagnosticCodeDesciption(),
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis()));
                System.out.println("Saving - " +  blockDiagCodeMappingTEntity.toString());
                session.save(blockDiagCodeMappingTEntity);
            }
            transaction.commit();

        }catch(Exception e) {
            if(transaction != null)
                transaction.rollback();
            throw new DataloadGeneratorException(e);
        }finally {
            if(session != null){
                session.close();
            }
        }

    }


    private List<PartialBlockDiagCodeMappingTEntity> getExitCodeListFromBlockDiagCodeMappingTEntitiesList(List<BlockDiagCodeMappingTEntity> blockDiagCodeMappingTEntities,
                                                                                                          String blockName) {
        List<PartialBlockDiagCodeMappingTEntity> exitCode = new ArrayList<>();
        for(BlockDiagCodeMappingTEntity blockDiagCodeMappingTEntity : blockDiagCodeMappingTEntities){
            if(blockDiagCodeMappingTEntity.getBlockName().equalsIgnoreCase(blockName)){
                PartialBlockDiagCodeMappingTEntity partialBlockDiagCodeMappingTEntity = new PartialBlockDiagCodeMappingTEntity();
                partialBlockDiagCodeMappingTEntity.setExitCode(blockDiagCodeMappingTEntity.getExitCode());
                partialBlockDiagCodeMappingTEntity.setDiagnosticCode(blockDiagCodeMappingTEntity.getDiagnosticCode());
                partialBlockDiagCodeMappingTEntity.setDiagnosticCodeDesciption(blockDiagCodeMappingTEntity.getDiagnosticCodeDesc());
                exitCode.add(partialBlockDiagCodeMappingTEntity);
            }
        }
        return exitCode;
    }

    private Collection<? extends TbsPatternSavedTEntity> retrieveGroupsAsTbsPatternSavedEntityFromDataload() throws DataloadGeneratorException {
        List<TbsPatternSavedTEntity> tbsPatternSavedTEntities = new ArrayList<>();
        TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
        if(dataload != null){
            if(dataload.getGroups() != null){
                if(dataload.getGroups().getGroup() != null || !dataload.getGroups().getGroup().isEmpty()) {
                    for(GroupType groupType : dataload.getGroups().getGroup()){
                        TbsPatternSavedTEntity tbsPatternSavedTEntity = new TbsPatternSavedTEntity();
                        tbsPatternSavedTEntity.setSegment("DataloadInMem");
                        tbsPatternSavedTEntity.setTechnology(groupType.getName());
                        tbsPatternSavedTEntity.setClazz(String.valueOf(groupType.getOrder()));
                        tbsPatternSavedTEntity.setOperator("-");
                        tbsPatternSavedTEntity.setRecordInsertDate(new Timestamp(System.currentTimeMillis()));
                        tbsPatternSavedTEntity.setRecordUpdateDate(new Timestamp(System.currentTimeMillis()));
                        tbsPatternSavedTEntity.setSolutionName("-");
                        tbsPatternSavedTEntity.setIntermediateXml(getStringXmlFromGroupTypeObject(groupType));
                        tbsPatternSavedTEntities.add(tbsPatternSavedTEntity);
                    }
                }
            }
        }
        return tbsPatternSavedTEntities;
    }

    @Override
    public void deleteTbsPatternSavedEntity(TbsPatternSavedTEntity tbsPatternSavedTEntity) throws DataloadGeneratorException {
        final Session session = getSession();
        try{
            session.delete(tbsPatternSavedTEntity);
        }catch (Exception e){
            throw new DataloadGeneratorException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<ActionType> getActionTypeList() throws DataloadGeneratorException {
        TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
        if(dataload.getActions() != null && dataload.getActions().getAction() != null){
            return dataload.getActions().getAction();
        }else{
            throw new DataloadGeneratorException("Error - No Actions availables from the current data source. [" + confClassImpl.getPATH_DATALOAD() +"]");
        }
    }


    @Override
    public List<GroupType> getGroupTypeList() throws DataloadGeneratorException {
        TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
        if(dataload.getGroups() != null && dataload.getGroups().getGroup() != null){
            return dataload.getGroups().getGroup();
        }else{
            throw new DataloadGeneratorException("Error - No Groups availables from the current data source. [" + confClassImpl.getPATH_DATALOAD() +"]");
        }
    }


    @Override
    public List<WorkflowTemplateType> getWorkflowsTemplateList() throws DataloadGeneratorException {
        TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
        if(dataload.getWorkflowTemplates() != null && dataload.getWorkflowTemplates().getWorkflowTemplate()!= null){
            return dataload.getWorkflowTemplates().getWorkflowTemplate();
        }else{
            throw new DataloadGeneratorException("Error - No Workflows availables from the current data source. [" + confClassImpl.getPATH_DATALOAD() +"]");
        }
    }

    @Override
    public List<HelpTextType> getListHelpText() throws DataloadGeneratorException {
        TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
        if(dataload.getHelpTexts() != null && dataload.getHelpTexts().getHelpText() != null){
            return dataload.getHelpTexts().getHelpText();
        }else{
            throw new DataloadGeneratorException("Error - No HelpText availables from the current data source. [" + confClassImpl.getPATH_DATALOAD() +"]");
        }
    }

    @Override
    public void updateGroupType(GroupType groupType) throws DataloadGeneratorException {
        System.out.println("Update group [" + groupType.getName() + "] on db");
        List<GroupType> groupTypesToRemove = new ArrayList<>();
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());

            for(GroupType groupTypeFromDataload : dataload.getGroups().getGroup()) {
                if(groupTypeFromDataload.getName().equalsIgnoreCase(groupType.getName())){
                    groupTypesToRemove.add(groupTypeFromDataload);
                }
            }
            if(groupTypesToRemove.size() > 0){
                dataload.getGroups().getGroup().removeAll(groupTypesToRemove);
                dataload.getGroups().getGroup().add(groupType);
                String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
                writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
            }else{
                throw new DataloadGeneratorException("Error - Cannot update group [" + groupType.getName() + "]. Group not found into datasource.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot update group [" + groupType.getName() + "]. Error - " + e.getCause());
        }
    }


    @Override
    public void updateActionType(ActionType actionType) throws DataloadGeneratorException {
        System.out.println("Update actionType [" + actionType.getName() + "] on db");
        List<ActionType> actionTypeListToRemove = new ArrayList<>();
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            for(ActionType actionTypeFromDataload : dataload.getActions().getAction()) {
                if(actionType.getName() == null){
                    throw new DataloadGeneratorException("Error - Action Name is null. Impossible to update");
                }
                if(actionTypeFromDataload.getName().equalsIgnoreCase(actionType.getName())){
                    actionTypeListToRemove.add(actionTypeFromDataload);
                }
            }
            if(actionTypeListToRemove.size() > 0){
                dataload.getActions().getAction().removeAll(actionTypeListToRemove);
                dataload.getActions().getAction().add(actionType);
                String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
                writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
            }else{
                throw new DataloadGeneratorException("Error - Cannot update action [" + actionType.getName() + "]. Action name not found into datasource.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot update action [" + actionType.getName() + "]. Error - " + e.getCause());
        }
    }

    @Override
    public void updateHelpTextType(HelpTextType helpTextType) throws DataloadGeneratorException {
        if(helpTextType.getName() == null
                || EMPTY_STRING.equalsIgnoreCase(helpTextType.getName())
                || helpTextType.getText() == null
                || EMPTY_STRING.equalsIgnoreCase(helpTextType.getText())){
            throw new DataloadGeneratorException("Error - Cannot update helpText item with Name empty/null or Text empty/null");
        }
        System.out.println("Update helpText [" + helpTextType.getName() + "] on db");
        List<HelpTextType> helpTextTypeListToRemove = new ArrayList<>();
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            for(HelpTextType helpTextType1 : dataload.getHelpTexts().getHelpText()) {
                if(helpTextType1.getName().equalsIgnoreCase(helpTextType.getName())){
                    helpTextTypeListToRemove.add(helpTextType1);
                }
            }
            if(helpTextTypeListToRemove.size() > 0){
                dataload.getHelpTexts().getHelpText().removeAll(helpTextTypeListToRemove);
                dataload.getHelpTexts().getHelpText().add(helpTextType);
                String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
                writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
            }else{
                throw new DataloadGeneratorException("Error - Cannot update helpText [" + helpTextType.getName() + "]. HelpText name not found into datasource.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot update helpText [" + helpTextType.getName() + "]. Error - " + e.getCause());
        }
    }

    @Override
    public void deleteActionType(ActionType actionType) throws DataloadGeneratorException {
        System.out.println("Delete actionType [" + actionType.getName() + "] from db");
        List<ActionType> actionTypeListToRemove = new ArrayList<>();
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            for(ActionType actionTypeFromDataload : dataload.getActions().getAction()) {
                if(actionType.getName().equalsIgnoreCase(actionTypeFromDataload.getName())){
                    actionTypeListToRemove.add(actionTypeFromDataload);
                }
            }
            if(actionTypeListToRemove.size() > 0){
                dataload.getActions().getAction().removeAll(actionTypeListToRemove);
                String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
                writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
                List<ActionDetails> actionDetails = buildUpdatedActionsConfigurationFile(actionType.getName(), false, true);
                if(actionDetails != null && actionDetails.size() != 0){
                    saveActionConfiguration(actionDetails);
                }
            }else{
                throw new DataloadGeneratorException("Error - Cannot delete action [" + actionType.getName() + "]. Action name not found into datasource.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot delete action [" + actionType.getName() + "]. Error - " + e.getCause());
        }
    }

    @Override
    public void saveActions(List<ActionType> actionTypesList) throws DataloadGeneratorException {
        checkListValidityInput(actionTypesList);
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            List<String> alreadyPresentActionsNameList = getAlreadyPresentActionsNameList(dataload);
            for(ActionType actionTypeItem : actionTypesList){
                if(actionTypeItem.getName() == null || EMPTY_STRING.equalsIgnoreCase(actionTypeItem.getName())){
                    throw new DataloadGeneratorException("Error - Missing action name!");
                }
                if(alreadyPresentActionsNameList.contains(actionTypeItem.getName())){
                    throw new DataloadGeneratorException("Error - The current action [" + actionTypeItem.getName() + "] is already present. You can edit or delete it before insert a new one.");
                }
            }
            dataload.getActions().getAction().addAll(actionTypesList);
            String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
            writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());

            for(ActionType actionType : actionTypesList){
                List<ActionDetails> actionDetails = buildUpdatedActionsConfigurationFile(actionType.getName(), true, false);
                if(actionDetails != null && actionDetails.size() != 0){
                    saveActionConfiguration(actionDetails);
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot insert actions." + e.getCause());
        }
    }

    @Override
    public void saveWorkflows(List<WorkflowTemplateType> workflowTemplateTypeList) throws DataloadGeneratorException {
        checkListValidityInput(workflowTemplateTypeList);
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            List<String> alreadyPresentActionAssociatedToWorkflowsList = getAlreadyPresentActionAssociatedWithWf(dataload);
            System.out.println("Adding the following workflows: ");

            for(WorkflowTemplateType workflowTemplateType : workflowTemplateTypeList){
                if(workflowTemplateType.getWorkflow() == null || EMPTY_STRING.equalsIgnoreCase(workflowTemplateType.getWorkflow())){
                    throw new DataloadGeneratorException("Error - Missing Workflow name");
                }
                if(workflowTemplateType.getActionName() == null || EMPTY_STRING.equalsIgnoreCase(workflowTemplateType.getActionName())){
                    throw new DataloadGeneratorException("Error - Missing Action name");
                }
                if(alreadyPresentActionAssociatedToWorkflowsList.contains(workflowTemplateType.getActionName())){
                    throw new DataloadGeneratorException("Error - The current action [" + workflowTemplateType.getActionName() + "] already has workflow associated. You can edit or delete it before insert a new one.");
                }
                System.out.println("  - " + workflowTemplateType.getWorkflow());
            }
            dataload.getWorkflowTemplates().getWorkflowTemplate().addAll(workflowTemplateTypeList);
            String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
            writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());

        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot insert actions." + e.getCause());
        }
    }

    @Override
    public void updateWorkflowTemplateType(WorkflowTemplateType workflowTemplateType) throws DataloadGeneratorException {
        if(workflowTemplateType.getWorkflow() == null
                || EMPTY_STRING.equalsIgnoreCase(workflowTemplateType.getWorkflow())
                || workflowTemplateType.getActionName() == null
                || EMPTY_STRING.equalsIgnoreCase(workflowTemplateType.getActionName())){
            throw new DataloadGeneratorException("Error - Cannot update workflow item with Workflow Name empty or Action Name empty");
        }
        System.out.println("Update workflow [" + workflowTemplateType.getWorkflow() + "] on db");
        List<WorkflowTemplateType> workflowTypeListToRemove = new ArrayList<>();
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            for(WorkflowTemplateType workflowTemplateTypeItem : dataload.getWorkflowTemplates().getWorkflowTemplate()) {
                if(workflowTemplateTypeItem.getActionName().equalsIgnoreCase(workflowTemplateType.getActionName())){
                    workflowTypeListToRemove.add(workflowTemplateTypeItem);
                }
            }
            if(workflowTypeListToRemove.size() > 0){
                dataload.getWorkflowTemplates().getWorkflowTemplate().removeAll(workflowTypeListToRemove);
                dataload.getWorkflowTemplates().getWorkflowTemplate().add(workflowTemplateType);
                String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
                writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
            }else{
                throw new DataloadGeneratorException("Error - Cannot update workflow [" + workflowTemplateType.getWorkflow() + "]. Workflow name not found into datasource.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot update workflow [" + workflowTemplateType.getWorkflow() + "]. Error - " + e.getCause());
        }
    }

    @Override
    public void refreshActionsDetailsConfiguration() throws DataloadGeneratorException {
        TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
        List<ActionDetails> actionListDetails = getActionDetailList(confClassImpl.getActionDetailsPathFile());
        List<String> actionNameList = new ArrayList<>();
        List<String> actionDetailsName = new ArrayList<>();
        for(ActionType actionType : dataload.getActions().getAction()) {
            actionNameList.add(actionType.getName());
        }
        for(ActionDetails actionDetailsItem : actionListDetails){
            actionDetailsName.add(actionDetailsItem.getName());
        }
        actionNameList.removeAll(actionDetailsName);
        if(!actionNameList.isEmpty()){
            for(String actionName : actionNameList){
                actionListDetails.add(new ActionDetails(actionName));
            }
        }
        saveActionConfiguration(actionListDetails);
    }

    @Override
    public void restoreDefaultActionsDetailsConfiguration() throws DataloadGeneratorException {
        List<ActionDetails> actionListDetails = getActionDetailList(confClassImpl.getActionDetailsPathFile());
        for(ActionDetails actionDetails : actionListDetails){
            actionDetails.setReccomended(true);
            actionDetails.setPossible(false);
            actionDetails.setProceed(false);
            actionDetails.setExcluded(false);
        }
        saveActionConfiguration(actionListDetails);
    }

    @Override
    public void deleteAllActionType() throws DataloadGeneratorException {
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            if(dataload.getActions() != null){
                if(dataload.getActions().getAction() != null){
                    dataload.getActions().getAction().clear();
                }
            }
            String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
            writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot delete all actions from database - Error - " + e.getCause());
        }
    }

    @Override
    public void deleteAllWorkflows() throws DataloadGeneratorException {
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            if(dataload.getWorkflowTemplates() != null){
                if(dataload.getWorkflowTemplates().getWorkflowTemplate() != null){
                    dataload.getWorkflowTemplates().getWorkflowTemplate().clear();
                }
            }
            String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
            writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot delete all workflows from database - Error - " + e.getCause());
        }
    }

    @Override
    public void deleteAllHelpText() throws DataloadGeneratorException {
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            if(dataload.getHelpTexts() != null){
                if(dataload.getHelpTexts().getHelpText() != null){
                    dataload.getHelpTexts().getHelpText().clear();
                }
            }
            String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
            writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot delete all helptext from database - Error - " + e.getCause());
        }
    }

    private List<String> getAlreadyPresentActionsNameList(TD dataload) throws DataloadGeneratorException {
        List<String> actionNameList = new ArrayList<>();
        if(dataload != null && dataload.getActions() != null) {
            for (ActionType actionType : dataload.getActions().getAction()) {
                actionNameList.add(actionType.getName());
            }
            return actionNameList;
        }else {
            throw new DataloadGeneratorException("Error - Problems with storing object. Seems to be null or not capable to accept storing operations");
        }
    }
    private List<String> getAlreadyPresentActionAssociatedWithWf(TD dataload) throws DataloadGeneratorException {
        List<String> actionAssociatedToWorkflowsList = new ArrayList<>();
        if(dataload != null && dataload.getWorkflowTemplates() != null){
            for(WorkflowTemplateType workflowTemplateType : dataload.getWorkflowTemplates().getWorkflowTemplate()){
                actionAssociatedToWorkflowsList.add(workflowTemplateType.getActionName());
            }
            return actionAssociatedToWorkflowsList;
        }else{
            throw new DataloadGeneratorException("Error - Problems with storing object. Seems to be null or not capable to accept storing operations");
        }
    }

    private List<String> getAlreadyPresentHelpText(TD dataload) throws DataloadGeneratorException {
        List<String> helpTextPresent = new ArrayList<>();
        if(dataload != null && dataload.getHelpTexts() != null){
            for(HelpTextType helpTextType : dataload.getHelpTexts().getHelpText()){
                helpTextPresent.add(helpTextType.getName());
            }
            return helpTextPresent;
        }else{
            throw new DataloadGeneratorException("Error - Problems with storing object. Seems to be null or not capable to accept storing operations");
        }

    }

    private void checkListValidityInput(List actionTypesList) throws DataloadGeneratorException {
        if(actionTypesList == null){
            throw new DataloadGeneratorException("Error - The input list object to add is NULL");
        }
        if(actionTypesList.isEmpty()){
            throw new DataloadGeneratorException("Error - The input list object to add is EMPTY");
        }
    }

    @Override
    public void deleteWorkflowType(WorkflowTemplateType workflowTemplateType) throws DataloadGeneratorException {
        System.out.println("Delete Workflow Template Type [" + workflowTemplateType.getWorkflow() + "] from db");
        List<WorkflowTemplateType> workflowTemplateTypesToRemove = new ArrayList<>();
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            for(WorkflowTemplateType workflowTemplateItem : dataload.getWorkflowTemplates().getWorkflowTemplate()) {
                if(workflowTemplateItem.getWorkflow().equalsIgnoreCase(workflowTemplateType.getWorkflow())
                        && workflowTemplateItem.getActionName().equalsIgnoreCase(workflowTemplateType.getActionName())) {
                    workflowTemplateTypesToRemove.add(workflowTemplateItem);
                }
            }
            if(workflowTemplateTypesToRemove.size() > 0){
                dataload.getWorkflowTemplates().getWorkflowTemplate().removeAll(workflowTemplateTypesToRemove);
                String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
                writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
            }else{
                throw new DataloadGeneratorException("Error - Cannot delete workflow [" + workflowTemplateType.getWorkflow() + "]. Workflow template not found into datasource.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot delete workflow [" + workflowTemplateType.getWorkflow() + "]. Error - " + e.getCause());
        }
    }


    public void saveActionConfiguration(List<ActionDetails> actionDetails) throws DataloadGeneratorException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Saving ActionDetails List: " + actionDetails);
        try {
            mapper.writeValue(new File(confClassImpl.getActionDetailsPathFile()), actionDetails);
        } catch (JsonProcessingException e) {
            throw new DataloadGeneratorException("Error - Unable to convert action details list object to Json. " + e.getMessage());
        } catch (IOException e) {
            throw new DataloadGeneratorException("Error - Unable to save details action. " + e.getMessage());
        }
    }
    private List<ActionDetails> buildUpdatedActionsConfigurationFile(String name, boolean create, boolean delete) throws DataloadGeneratorException {

        if(create) {
            List<ActionDetails> actionListDetails = getActionDetailList(confClassImpl.getActionDetailsPathFile());
            List<String> actionListDetailsName = new ArrayList<>();
            for(ActionDetails actionDetails : actionListDetails){
                actionListDetailsName.add(actionDetails.getName());
            }
            if(!actionListDetailsName.contains(name)){
                actionListDetails.add(new ActionDetails(name));
                return actionListDetails;
            }
            return null;
        } else if (delete) {
            List<ActionDetails> actionListDetails = getActionDetailList(confClassImpl.getActionDetailsPathFile());
            List<String> actionListDetailsName = new ArrayList<>();
            for(ActionDetails actionDetails : actionListDetails){
                actionListDetailsName.add(actionDetails.getName());
            }
            if(actionListDetailsName.contains(name)){
                actionListDetails.remove(new ActionDetails(name));
                return actionListDetails;
            }
            return null;
        } else{
            throw new DataloadGeneratorException("Error - Unable to update Details Action file.");
        }
    }

    @Override
    public void saveHelpText(List<HelpTextType> helpTextTemplateTypeList) throws DataloadGeneratorException {

        checkListValidityInput(helpTextTemplateTypeList);
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            List<String> alreadyPresentHelpTextList = getAlreadyPresentHelpText(dataload);
            System.out.println("Adding the following actions: ");

            for(HelpTextType helpTextType : helpTextTemplateTypeList){
                if(helpTextType.getName() == null || EMPTY_STRING.equalsIgnoreCase(helpTextType.getName())){
                    throw new DataloadGeneratorException("Error - Missing name into HelpText object!");
                }
                if(alreadyPresentHelpTextList.contains(helpTextType.getName())){
                    throw new DataloadGeneratorException("Error - The current helptext [" + helpTextType.getName()  + "] is already present. You can edit or delete it before insert a new one.");
                }
            }
            dataload.getHelpTexts().getHelpText().addAll(helpTextTemplateTypeList);
            String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
            writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot insert help text object. " + e.getCause());
        }

    }

    @Override
    public void updateTbsPatternSavedEntity(List<TbsPatternSavedTEntity> tbsPatternSavedTEntityList) {

    }

    @Override
    public void deleteHelpTextType(HelpTextType helpTextType) throws DataloadGeneratorException {
        System.out.println("Delete HelpText Template Type [" + helpTextType.getName() + "] from db");
        List<HelpTextType> helpTextTypesList = new ArrayList<>();
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            for(HelpTextType helpTextTypeItem: dataload.getHelpTexts().getHelpText()) {

                if(helpTextTypeItem.getName().equalsIgnoreCase(helpTextType.getName())) {
                    helpTextTypesList.add(helpTextTypeItem);
                }
            }
            if(helpTextTypesList.size() > 0){
                dataload.getHelpTexts().getHelpText().removeAll(helpTextTypesList);
                String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
                writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());
            }else{
                throw new DataloadGeneratorException("Error - Cannot delete HelpText [" + helpTextType.getName() + "]. HelpText not found into datasource.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot delete HelpText [" + helpTextType.getName() + "]. Error - " + e.getCause());
        }
    }

    /**
     * This method takes in input a path, read a dataload file from file system and import the groups contained
     *
     * @param dataloadXml
     */
    @Override
    public void importGroupsFromPath(String dataloadXml) throws DataloadGeneratorException {
        TD dataloadFromPath = DataloadGenUtils.buildTDObjectFromStringXml(dataloadXml);
        if(dataloadFromPath.getGroups() != null){
            if(dataloadFromPath.getGroups().getGroup() != null || !dataloadFromPath.getGroups().getGroup().isEmpty()){
                TbsOrmBuilder.getTbsOrmImplementation().importGroupsListReplacingExisting(dataloadFromPath.getGroups().getGroup());
            }
        }else{
            throw new DataloadGeneratorException("Error - No actions are configured for the current file.");
        }
    }

    /**
     * This method takes in input a path, read a dataload file from file system and import the actions contained
     *
     * @param dataloadXml
     */
    @Override
    public void importActionsFromPath(String dataloadXml) throws DataloadGeneratorException {
        TD dataloadFromPath = DataloadGenUtils.buildTDObjectFromStringXml(dataloadXml);
        if(dataloadFromPath.getActions() != null){
            if(dataloadFromPath.getActions().getAction() != null || !dataloadFromPath.getActions().getAction().isEmpty()){
                TbsOrmBuilder.getTbsOrmImplementation().importActionListReplacingExisting(dataloadFromPath.getActions().getAction());
            }
        }else{
            throw new DataloadGeneratorException("Error - No actions are configured for the current file.");
        }
    }

    @Override
    public void importActionListReplacingExisting(List<ActionType> actionTypesList) throws DataloadGeneratorException {
        checkListValidityInput(actionTypesList);
        checkIfActionTypeListContainsDuplicatedActions(actionTypesList);
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            List<String> alreadyPresentActionsNameList = getAlreadyPresentActionsNameList(dataload);
            List<String> actionsNameToRemove = new ArrayList<>();
            for(ActionType actionTypeItem : actionTypesList){
                if(actionTypeItem.getName() == null || EMPTY_STRING.equalsIgnoreCase(actionTypeItem.getName())){
                    throw new DataloadGeneratorException("Error - Missing action name!");
                }
                if(alreadyPresentActionsNameList.contains(actionTypeItem.getName())){
                    actionsNameToRemove.add(actionTypeItem.getName());
                }
            }
            dataload.getActions().getAction().removeAll(getActionTypeListFromDataloadAndListNamesToRemove(dataload,actionsNameToRemove));
            dataload.getActions().getAction().addAll(actionTypesList);
            String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
            writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());

            for(ActionType actionType : actionTypesList){
                List<ActionDetails> actionDetails = buildUpdatedActionsConfigurationFile(actionType.getName(), true, false);
                if(actionDetails != null && actionDetails.size() != 0){
                    saveActionConfiguration(actionDetails);
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot insert actions." + e.getCause());
        }
    }

    private List<ActionType> getActionTypeListFromDataloadAndListNamesToRemove(TD dataload, List<String> actionsNameToRemove) {
        List<ActionType> actionTypes = new ArrayList<>();
        for(ActionType actionType : dataload.getActions().getAction()) {

            if(actionsNameToRemove.contains(actionType.getName())) {
                actionTypes.add(actionType);
            }
        }
        return actionTypes;
    }

    @Override
    public void importWorkflowsFromPath(String dataloadXml) throws DataloadGeneratorException {
        TD dataloadFromPath = DataloadGenUtils.buildTDObjectFromStringXml(dataloadXml);
        if(dataloadFromPath.getWorkflowTemplates() != null){
            if(dataloadFromPath.getWorkflowTemplates().getWorkflowTemplate() != null || !dataloadFromPath.getWorkflowTemplates().getWorkflowTemplate().isEmpty()){
                TbsOrmBuilder.getTbsOrmImplementation().importWorkflowsListReplacingExisting(dataloadFromPath.getWorkflowTemplates().getWorkflowTemplate());
            }
        }else{
            throw new DataloadGeneratorException("Error - No workflows are configured for the current file.");
        }
    }

    @Override
    public void importHelpTextFromPath(String dataloadXml) throws DataloadGeneratorException {
        TD dataloadFromPath = DataloadGenUtils.buildTDObjectFromStringXml(dataloadXml);
        if(dataloadFromPath.getHelpTexts() != null){
            if(dataloadFromPath.getHelpTexts().getHelpText() != null || !dataloadFromPath.getHelpTexts().getHelpText().isEmpty()){
                TbsOrmBuilder.getTbsOrmImplementation().importHelpTextListReplacingExisting(dataloadFromPath.getHelpTexts().getHelpText());
            }
        }else{
            throw new DataloadGeneratorException("Error - No HelpText are configured for the current file.");
        }
    }

    @Override
    public void importGroupsListReplacingExisting(List<GroupType> groupTypesListToAdd) throws DataloadGeneratorException {
        checkListValidityInput(groupTypesListToAdd);
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            List<String> alreadyPresentGroup = getAlreadyPresentGroupsAssociatedWithWf(dataload);
            List<String> groupTypeToDeleteList = new ArrayList<>();

            for(GroupType groupType : groupTypesListToAdd){
                if(groupType.getName() == null || EMPTY_STRING.equalsIgnoreCase(groupType.getName())){
                    throw new DataloadGeneratorException("Error - Missing Group name");
                }
                if(EMPTY_STRING.equalsIgnoreCase(String.valueOf(groupType.getOrder()))){
                    throw new DataloadGeneratorException("Error - Missing Order group");
                }
                if(alreadyPresentGroup.contains(groupType.getName())){
                    groupTypeToDeleteList.add(groupType.getName());
                }
            }
            dataload.getGroups().getGroup().removeAll(getGroupsListFromDataloadToRemove(dataload, groupTypeToDeleteList));
            dataload.getGroups().getGroup().addAll(groupTypesListToAdd);
            String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
            writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());

        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot insert groups." + e.getCause());
        }
    }

    private Collection<?> getGroupsListFromDataloadToRemove(TD dataload, List<String> groupTypeToDeleteList) {
        List<GroupType> groupTypesToRemove = new ArrayList<>();
        for(GroupType groupType : dataload.getGroups().getGroup()){
            if(groupTypeToDeleteList.contains(groupType.getName())){
                groupTypesToRemove.add(groupType);
            }
        }
        return groupTypesToRemove;
    }

    private List<String> getAlreadyPresentGroupsAssociatedWithWf(TD dataload) {
        List<String> groupsNameList = new ArrayList<>();
        for(GroupType groupType : dataload.getGroups().getGroup()){
            groupsNameList.add(groupType.getName());
        }
        return groupsNameList;
    }

    @Override
    public void importWorkflowsListReplacingExisting(List<WorkflowTemplateType> workflowTemplateTypeList) throws DataloadGeneratorException {
        checkListValidityInput(workflowTemplateTypeList);
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            List<String> alreadyPresentGroupsAssociatedToWorkflowsList = getAlreadyPresentActionAssociatedWithWf(dataload);
            List<String> workflowTemplateListToDelete = new ArrayList<>();

            for(WorkflowTemplateType workflowTemplateType : workflowTemplateTypeList){
                if(workflowTemplateType.getWorkflow() == null || EMPTY_STRING.equalsIgnoreCase(workflowTemplateType.getWorkflow())){
                    throw new DataloadGeneratorException("Error - Missing Workflow name");
                }
                if(workflowTemplateType.getActionName() == null || EMPTY_STRING.equalsIgnoreCase(workflowTemplateType.getActionName())){
                    throw new DataloadGeneratorException("Error - Missing Action name");
                }
                if(alreadyPresentGroupsAssociatedToWorkflowsList.contains(workflowTemplateType.getActionName())){
                    workflowTemplateListToDelete.add(workflowTemplateType.getActionName());
                }
            }
            dataload.getWorkflowTemplates().getWorkflowTemplate().removeAll(getWorkflowsListFromDataloadAndListNamesToRemove(dataload, workflowTemplateListToDelete));
            dataload.getWorkflowTemplates().getWorkflowTemplate().addAll(workflowTemplateTypeList);
            String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
            writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());

        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot insert actions." + e.getCause());
        }
    }

    @Override
    public void importHelpTextListReplacingExisting(List<HelpTextType> helpTextTypeList) throws DataloadGeneratorException {
        checkListValidityInput(helpTextTypeList);
        try {
            TD dataload = DataloadGenUtils.getDataloadFromPath(confClassImpl.getPATH_DATALOAD());
            List<String> alreadyPresentHelpTextList = getAlreadyPresentHelpText(dataload);
            List<String> helpTextToDelete = new ArrayList<>();

            for(HelpTextType helpTextType : helpTextTypeList){
                if(helpTextType.getName() == null || EMPTY_STRING.equalsIgnoreCase(helpTextType.getName())){
                    throw new DataloadGeneratorException("Error - Missing HelpText name");
                }
                if(alreadyPresentHelpTextList.contains(helpTextType.getName())){
                    helpTextToDelete.add(helpTextType.getName());
                }
            }
            List<HelpTextType> helpTextTypeListToRemove = getHelpTextListFromListName(helpTextToDelete, dataload);
            dataload.getHelpTexts().getHelpText().removeAll(helpTextTypeListToRemove);
            dataload.getHelpTexts().getHelpText().addAll(helpTextTypeList);
            String dataloadString = DataloadGenUtils.getStringXmlFromTDObject(dataload);
            writeObjectOnFile(dataloadString, confClassImpl.getPATH_DATALOAD());

        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DataloadGeneratorException("Error - Cannot insert help texts." + e.getCause());
        }
    }

    private List<HelpTextType> getHelpTextListFromListName(List<String> helpTextToDelete, TD dataload) {
        List<HelpTextType> helpTextTypes = new ArrayList<>();
        for(HelpTextType helpTextType : dataload.getHelpTexts().getHelpText()){
            if(helpTextToDelete.contains(helpTextType.getName())){
                helpTextTypes.add(helpTextType);
            }
        }
        return helpTextTypes;
    }

    private List<WorkflowTemplateType> getWorkflowsListFromDataloadAndListNamesToRemove(TD dataload, List<String> workflowTemplateListToDelete) {
        List<WorkflowTemplateType> workflowTemplateTypeToRemove = new ArrayList<>();
        for(WorkflowTemplateType workflowTemplateType : dataload.getWorkflowTemplates().getWorkflowTemplate()) {
            if(workflowTemplateListToDelete.contains(workflowTemplateType.getActionName())) {
                workflowTemplateTypeToRemove.add(workflowTemplateType);
            }
        }
        return workflowTemplateTypeToRemove;
    }
}
