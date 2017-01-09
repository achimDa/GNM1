package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.MetaDataMap;
import com.servustech.spring.model.base.BaseEntity;

public interface MetaDataMapDAO {

	void addMetaDataMap(BaseEntity metadatamap);

	void updateMetaDataMap(BaseEntity metadatamap);

	List<MetaDataMap> listMetaDataMap(String metadatamapName);

	List<MetaDataMap> listMetaDataMap(int id);

	MetaDataMap getMetaDataMapById(int id, Class<? extends BaseEntity> metadatamapclass);

	void removeMetaDataMap(int id, final Class<? extends BaseEntity> metadatamapclass);
}