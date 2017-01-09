package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.City;
import com.servustech.spring.model.base.BaseEntity;

public interface CityDAO {

	void addCity(BaseEntity city);

	void updateCity(BaseEntity city);

	List<City> listCity(String cityName);

	List<City> listCity(int id);

	City getCityById(int id, Class<? extends BaseEntity> cityclass);

	void removeCity(int id, final Class<? extends BaseEntity> cityclass);
}