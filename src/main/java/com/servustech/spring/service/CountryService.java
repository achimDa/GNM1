package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.Country;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.CountryModelDTO;

public interface CountryService {

	void addCountry(BaseEntity country);

	void save(CountryModelDTO countryModelDTO);

	void updateCountry(BaseEntity country);

	List<Country> listCountry(String countryName);

	List<CountryModelDTO> listCountry();

	Object getCountryById(int id, Class<? extends BaseEntity> clazz);

	void removeCountry(int id, Class<? extends BaseEntity> clazz);

	void editCountry(CountryModelDTO countryModelDTO);
}
