package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.Map;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.MapModelDTO;

public interface MapService {

	void addMap(BaseEntity map);

	void save(MapModelDTO mapModelDTO);

	void updateMap(BaseEntity map);

	List<Map> listMap(String mapName);

	List<MapModelDTO> listMap(int objectID);

	Object getMapById(int id, Class<? extends BaseEntity> clazz);

	void removeMap(int id, Class<? extends BaseEntity> clazz);

	/**
	 * @param mapModelDTO
	 */
	void editMap(MapModelDTO mapModelDTO);
}
