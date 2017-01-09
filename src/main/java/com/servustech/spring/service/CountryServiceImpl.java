package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.CountryDAO;
import com.servustech.spring.model.Country;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.CountryModelConverter;
import com.servustech.spring.model.dto.CountryModelDTO;

@Service
@Transactional

public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryDAO countryDAO;

	@Override
	public void addCountry(final BaseEntity country) {
		countryDAO.addCountry(country);
	}

	@Override
	public void updateCountry(final BaseEntity country) {
		countryDAO.updateCountry(country);
	}

	@Override
	public void save(final CountryModelDTO countryModelDTO) {
		final Country country = new Country();
		CountryModelConverter.toEntity(countryModelDTO, country);
		if (country.getId() == 0) {
			addCountry(country);
		} else {
			updateCountry(country);
		}
	}

	@Override
	public List<Country> listCountry(final String countryName) {
		return countryDAO.listCountry(countryName);
	}

	@Override
	public List<CountryModelDTO> listCountry() {
		return CountryModelConverter.toDTOList(countryDAO.listCountry());
	}

	@Override
	public Country getCountryById(final int id, final Class<? extends BaseEntity> countryclass) {
		return countryDAO.getCountryById(id, countryclass);
	}

	@Override
	public void removeCountry(final int id, final Class<? extends BaseEntity> countryclass) {
		countryDAO.removeCountry(id, countryclass);
	}

	@Override
	public void editCountry(final CountryModelDTO countryModelDTO) {
		final Country country = getCountryById(countryModelDTO.getId(), Country.class);

		country.setDenumire(countryModelDTO.getDenumire());
		country.setIndicativ(countryModelDTO.getIndicativ());

		countryDAO.updateCountry(country);

	}

}