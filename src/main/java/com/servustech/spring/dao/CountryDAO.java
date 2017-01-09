package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.Country;
import com.servustech.spring.model.base.BaseEntity;

public interface CountryDAO {

	void addCountry(BaseEntity country);

	void updateCountry(BaseEntity country);

	List<Country> listCountry(String countryName);

	List<Country> listCountry();

	Country getCountryById(int id, Class<? extends BaseEntity> countryclass);

	void removeCountry(int id, final Class<? extends BaseEntity> countryclass);
}