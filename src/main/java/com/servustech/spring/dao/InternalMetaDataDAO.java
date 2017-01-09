package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.InternalMetaData;
import com.servustech.spring.model.base.BaseEntity;

public interface InternalMetaDataDAO {

	void addInternalMetaData(BaseEntity internaldata);

	void updateInternalMetaData(BaseEntity internaldata);

	List<InternalMetaData> listInternalMetaData(String internaldataName);

	List<InternalMetaData> listInternalMetaData(int id);

	InternalMetaData getInternalMetaDataById(int id, Class<? extends BaseEntity> internaldataclass);

	void removeInternalMetaData(int id, final Class<? extends BaseEntity> internaldataclass);
}