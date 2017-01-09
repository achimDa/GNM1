package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.Country;
import com.servustech.spring.model.dto.CountryModelDTO;


public class CountryModelConverter {
	public static CountryModelDTO toDTO(final Country model) {
		final CountryModelDTO countrydto = new CountryModelDTO();
		countrydto.setDenumire(model.getDenumire());
		countrydto.setIndicativ(model.getIndicativ());
		countrydto.setId(model.getId());
		
		return countrydto;
	}

	public static List<CountryModelDTO> toDTOList(final Collection<Country> countryList) {
		final List<CountryModelDTO> countries = new ArrayList<>();
		for (final Country countryModel : countryList) {
			countries.add(toDTO(countryModel));
		}
		return countries;
	}

	public static void toEntity(final CountryModelDTO countrymod, final Country countryentity) {
	

		countryentity.setDenumire(countrymod.getDenumire());
		countryentity.setId(countrymod.getId());
		countryentity.setIndicativ(countrymod.getIndicativ());
		
	}

}
