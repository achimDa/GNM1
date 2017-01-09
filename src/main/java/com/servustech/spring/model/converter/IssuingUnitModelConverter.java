package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.IssuingUnit;
import com.servustech.spring.model.dto.IssuingUnitModelDTO;


public class IssuingUnitModelConverter {
	public static IssuingUnitModelDTO toDTO(final IssuingUnit model) {
		final IssuingUnitModelDTO issunitdto = new IssuingUnitModelDTO();
		issunitdto.setDenumire(model.getDenumire());
		issunitdto.setNrInregistrare(model.getNrInregistrare());
		issunitdto.setId(model.getId());
		
		return issunitdto;
	}

	public static List<IssuingUnitModelDTO> toDTOList(final Collection<IssuingUnit> issunitList) {
		final List<IssuingUnitModelDTO> issunit = new ArrayList<>();
		for (final IssuingUnit issunitModel : issunitList) {
			issunit.add(toDTO(issunitModel));
		}
		return issunit;
	}

	public static void toEntity(final IssuingUnitModelDTO issunitmod, final IssuingUnit issunitentity) {
	

		issunitentity.setDenumire(issunitmod.getDenumire());
		issunitentity.setId(issunitmod.getId());
		issunitentity.setNrInregistrare(issunitmod.getNrInregistrare());
		
	}

}
