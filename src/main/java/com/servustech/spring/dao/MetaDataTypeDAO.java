package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.MetaDataType;
import com.servustech.spring.model.base.BaseEntity;

public interface MetaDataTypeDAO {

	void addMetaDataType(BaseEntity metadatatype);

	void updateMetaDataType(BaseEntity metadatatype);

	List<MetaDataType> listMetaDataType(String metadatatypeName);

	List<MetaDataType> listMetaDataType();

	MetaDataType getMetaDataTypeById(int id, Class<? extends BaseEntity> metadatatypeclass);

	void removeMetaDataType(int id, final Class<? extends BaseEntity> metadatatypeclass);
}