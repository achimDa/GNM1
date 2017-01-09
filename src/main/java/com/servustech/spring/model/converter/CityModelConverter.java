
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.City;
import com.servustech.spring.model.dto.CityModelDTO;
import com.servustech.spring.model.Area;

public class CityModelConverter {
	public static CityModelDTO toDTO(final City model) {
		final CityModelDTO citydto = new CityModelDTO();
		citydto.setDenumire(model.getDenumire());
		citydto.setJudetId(model.getArea().getId());
		citydto.setId(model.getId());
		
		return citydto;
	}

	public static List<CityModelDTO> toDTOList(final Collection<City> cityList) {
		final List<CityModelDTO> cities = new ArrayList<>();
		for (final City cityModel : cityList) {
			cities.add(toDTO(cityModel));
		}
		return cities;
	}

	public static void toEntity(final CityModelDTO citymod, final City cityentity) {
		final Area area = new Area();

		area.setId(citymod.getJudetId());
		cityentity.setArea(area);
		cityentity.setDenumire(citymod.getDenumire());
		cityentity.setId(citymod.getId());
		
	}

}
