package com.hpe.dataload.generator.orm.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbsPatternSavedTEntity.class)
public abstract class TbsPatternSavedTEntity_ {

	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> toDelete;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> isUcpe;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> intermediateXml;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> solutionName;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> technology;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> operator;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> isContainedInLastDataload;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> rgType;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, Timestamp> recordInsertDate;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> service;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> segment;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, Timestamp> recordUpdateDate;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> clazz;
	public static volatile SingularAttribute<TbsPatternSavedTEntity, String> problemType;

	public static final String TO_DELETE = "toDelete";
	public static final String IS_UCPE = "isUcpe";
	public static final String INTERMEDIATE_XML = "intermediateXml";
	public static final String SOLUTION_NAME = "solutionName";
	public static final String TECHNOLOGY = "technology";
	public static final String OPERATOR = "operator";
	public static final String IS_CONTAINED_IN_LAST_DATALOAD = "isContainedInLastDataload";
	public static final String RG_TYPE = "rgType";
	public static final String RECORD_INSERT_DATE = "recordInsertDate";
	public static final String SERVICE = "service";
	public static final String SEGMENT = "segment";
	public static final String RECORD_UPDATE_DATE = "recordUpdateDate";
	public static final String CLAZZ = "clazz";
	public static final String PROBLEM_TYPE = "problemType";

}

