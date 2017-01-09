package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.Area;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.AreaModelDTO;

public interface AreaService {

	void addArea(BaseEntity area);

	void save(AreaModelDTO areaModelDTO);

	void updateArea(BaseEntity area);

	List<Area> listArea(String areaName);

	List<AreaModelDTO> listArea(int objectID);

	Object getAreaById(int id, Class<? extends BaseEntity> clazz);

	void removeArea(int id, Class<? extends BaseEntity> clazz);

	void editArea(AreaModelDTO areaModelDTO);
}
