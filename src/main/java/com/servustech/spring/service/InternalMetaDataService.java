package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.InternalMetaData;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.InternalMetaDataModelDTO;

public interface InternalMetaDataService {

	void addInternalMetaData(BaseEntity internaldata);

	void save(InternalMetaDataModelDTO internalModelDTO);

	void updateInternalMetaData(BaseEntity internaldata);

	List<InternalMetaData> listInternalMetaData(String internalName);

	List<InternalMetaDataModelDTO> listInternalMetaData(int objectID);

	Object getInternalMetaDataById(int id, Class<? extends BaseEntity> clazz);

	void removeInternalMetaData(int id, Class<? extends BaseEntity> clazz);

	void editInternalMetaData(InternalMetaDataModelDTO internalModelDTO);
}
