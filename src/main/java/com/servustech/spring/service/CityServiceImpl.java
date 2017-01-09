package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.CityDAO;
import com.servustech.spring.model.City;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.CityModelConverter;
import com.servustech.spring.model.dto.CityModelDTO;

@Service
@Transactional

public class CityServiceImpl implements CityService {
	@Autowired
	private CityDAO cityDAO;

	@Override
	public void addCity(final BaseEntity city) {
		cityDAO.addCity(city);
	}

	@Override
	public void updateCity(final BaseEntity city) {
		cityDAO.updateCity(city);
	}

	@Override
	public void save(final CityModelDTO cityModelDTO) {
		final City city = new City();
		CityModelConverter.toEntity(cityModelDTO, city);
		if (city.getId() == 0) {
			addCity(city);
		} else {
			updateCity(city);
		}
	}

	@Override
	public List<City> listCity(final String cityName) {
		return cityDAO.listCity(cityName);
	}

	@Override
	public List<CityModelDTO> listCity(final int objectID) {
		return CityModelConverter.toDTOList(cityDAO.listCity(objectID));
	}

	@Override
	public City getCityById(final int id, final Class<? extends BaseEntity> cityclass) {
		return cityDAO.getCityById(id, cityclass);
	}

	@Override
	public void removeCity(final int id, final Class<? extends BaseEntity> cityclass) {
		cityDAO.removeCity(id, cityclass);
	}

	@Override
	public void editCity(final CityModelDTO cityModelDTO) {
		final City city = getCityById(cityModelDTO.getId(), City.class);

		city.setDenumire(cityModelDTO.getDenumire());
		city.setId(cityModelDTO.getId());

		cityDAO.updateCity(city);

	}

}