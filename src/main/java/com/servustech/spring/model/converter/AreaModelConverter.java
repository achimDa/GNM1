
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.Area;
import com.servustech.spring.model.Country;
import com.servustech.spring.model.dto.AreaModelDTO;

public class AreaModelConverter {
	public static AreaModelDTO toDTO(final Area model) {
		final AreaModelDTO areadto = new AreaModelDTO();
		areadto.setDenumire(model.getDenumire());
		areadto.setIndicativ(model.getIndicativ());
		areadto.setIdtara(model.getCountry().getId());
		areadto.setId(model.getId());
		return areadto;
	}

	public static List<AreaModelDTO> toDTOList(final Collection<Area> areaList) {
		final List<AreaModelDTO> areas = new ArrayList<>();
		for (final Area areaModel : areaList) {
			areas.add(toDTO(areaModel));
		}
		return areas;
	}

	public static void toEntity(final AreaModelDTO areamod, final Area areaentity) {
		final Country country = new Country();

		country.setId(areamod.getIdtara());
		areaentity.setCountry(country);
		areaentity.setDenumire(areamod.getDenumire());
		areaentity.setIndicativ(areamod.getIndicativ());
		areaentity.setId(areamod.getId());

	}

}
