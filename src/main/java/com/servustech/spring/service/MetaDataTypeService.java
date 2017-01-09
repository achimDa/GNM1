package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.MetaDataType;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.MetaDataTypeModelDTO;

public interface MetaDataTypeService {

	void addMetaDataType(BaseEntity metatype);

	void save(MetaDataTypeModelDTO metatypeModelDTO);

	void updateMetaDataType(BaseEntity metatype);

	List<MetaDataType> listMetaDataType(String metatypeName);

	List<MetaDataTypeModelDTO> listMetaDataType();

	Object getMetaDataTypeById(int id, Class<? extends BaseEntity> clazz);

	void removeMetaDataType(int id, Class<? extends BaseEntity> clazz);

	void editMetaDataType(MetaDataTypeModelDTO metatypeModelDTO);
}
