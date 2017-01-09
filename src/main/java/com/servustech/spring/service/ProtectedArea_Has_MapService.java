package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.ProtectedArea_Has_Map;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.ProtectedArea_Has_MapModelDTO;

public interface ProtectedArea_Has_MapService {

	void addProtectedArea_Has_Map(BaseEntity areahasmap);

	void save(ProtectedArea_Has_MapModelDTO areahasmapModelDTO);

	void updateProtectedArea_Has_Map(BaseEntity areahasmap);

	List<ProtectedArea_Has_Map> listProtectedArea_Has_Map(String areahasmapName);

	List<ProtectedArea_Has_MapModelDTO> listProtectedArea_Has_Map(int objectID);

	Object getProtectedArea_Has_MapById(int id, Class<? extends BaseEntity> clazz);

	void removeProtectedArea_Has_Map(int id, Class<? extends BaseEntity> clazz);

	void editProtectedArea_Has_Map(ProtectedArea_Has_MapModelDTO areahasmapModelDTO);
}
