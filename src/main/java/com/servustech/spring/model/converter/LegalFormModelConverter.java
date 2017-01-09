package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.LegalForm;
import com.servustech.spring.model.dto.LegalFormModelDTO;


public class LegalFormModelConverter {
	public static LegalFormModelDTO toDTO(final LegalForm model) {
		final LegalFormModelDTO legaldto = new LegalFormModelDTO();
		legaldto.setDenumire(model.getDenumire());
		legaldto.setId(model.getId());
		
		return legaldto;
	}

	public static List<LegalFormModelDTO> toDTOList(final Collection<LegalForm> legalformList) {
		final List<LegalFormModelDTO> legalform = new ArrayList<>();
		for (final LegalForm legalformModel : legalformList) {
			legalform.add(toDTO(legalformModel));
		}
		return legalform;
	}

	public static void toEntity(final LegalFormModelDTO legalmod, final LegalForm legalentity) {
	

		legalentity.setDenumire(legalmod.getDenumire());
		legalentity.setId(legalmod.getId());
		
	}

}
