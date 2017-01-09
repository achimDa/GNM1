package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.ProtectedArea_Has_Map;
import com.servustech.spring.model.base.BaseEntity;

public interface ProtectedArea_Has_MapDAO {

	void addProtectedArea_Has_Map(BaseEntity areahasmap);

	void updateProtectedArea_Has_Map(BaseEntity areahasmap);

	List<ProtectedArea_Has_Map> listProtectedArea_Has_Map(String areahasmapName);

	List<ProtectedArea_Has_Map> listProtectedArea_Has_Map(int id);

	ProtectedArea_Has_Map getProtectedArea_Has_MapById(int id, Class<? extends BaseEntity> areahasmapclass);

	void removeProtectedArea_Has_Map(int id, final Class<? extends BaseEntity> areahasmapclass);
}