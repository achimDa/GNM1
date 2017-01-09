package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.MetaDataMap;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.MetaDataMapModelDTO;

public interface MetaDataMapService {

	void addMetaDataMap(BaseEntity metamap);

	void save(MetaDataMapModelDTO metamapModelDTO);

	void updateMetaDataMap(BaseEntity metamap);

	List<MetaDataMap> listMetaDataMap(String metamapName);

	List<MetaDataMapModelDTO> listMetaDataMap(int objectID);

	Object getMetaDataMapById(int id, Class<? extends BaseEntity> clazz);

	void removeMetaDataMap(int id, Class<? extends BaseEntity> clazz);

	void editMetaDataMap(MetaDataMapModelDTO metamapModelDTO);
}
