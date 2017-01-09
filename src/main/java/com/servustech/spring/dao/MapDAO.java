package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.Map;
import com.servustech.spring.model.base.BaseEntity;

public interface MapDAO {

	void addMap(BaseEntity map);

	void updateMap(BaseEntity map);

	List<Map> listMap(String mapName);

	List<Map> listMap(int id);

	Map getMapById(int id, Class<? extends BaseEntity> mapclass);

	void removeMap(int id, final Class<? extends BaseEntity> mapclass);
}