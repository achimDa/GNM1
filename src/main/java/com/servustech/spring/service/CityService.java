package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.City;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.CityModelDTO;

public interface CityService {

	void addCity(BaseEntity city);

	void save(CityModelDTO cityModelDTO);

	void updateCity(BaseEntity city);

	List<City> listCity(String cityName);

	List<CityModelDTO> listCity(int objectID);

	Object getCityById(int id, Class<? extends BaseEntity> clazz);

	void removeCity(int id, Class<? extends BaseEntity> clazz);

	/**
	 * @param areaModelDTO
	 */
	void editCity(CityModelDTO cityModelDTO);
}
