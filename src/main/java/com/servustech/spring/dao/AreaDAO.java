package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.Area;
import com.servustech.spring.model.base.BaseEntity;

public interface AreaDAO {

	void addArea(BaseEntity area);

	void updateArea(BaseEntity area);

	List<Area> listArea(String areaName);

	List<Area> listArea(int id);

	Area getAreaById(int id, Class<? extends BaseEntity> areaclass);

	void removeArea(int id, final Class<? extends BaseEntity> areaclass);
}