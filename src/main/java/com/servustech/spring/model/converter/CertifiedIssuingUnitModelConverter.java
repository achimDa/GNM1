package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.CertifiedIssuingUnit;
import com.servustech.spring.model.dto.CertifiedIssuingUnitModelDTO;


public class CertifiedIssuingUnitModelConverter {
	public static CertifiedIssuingUnitModelDTO toDTO(final CertifiedIssuingUnit model) {
		final CertifiedIssuingUnitModelDTO crtissunitdto = new CertifiedIssuingUnitModelDTO();
		crtissunitdto.setDenumire(model.getDenumire());
		crtissunitdto.setId(model.getId());
		
		return crtissunitdto;
	}

	public static List<CertifiedIssuingUnitModelDTO> toDTOList(final Collection<CertifiedIssuingUnit> crtissunitList) {
		final List<CertifiedIssuingUnitModelDTO> crtissunit = new ArrayList<>();
		for (final CertifiedIssuingUnit crtissunitModel : crtissunitList) {
			crtissunit.add(toDTO(crtissunitModel));
		}
		return crtissunit;
	}

	public static void toEntity(final CertifiedIssuingUnitModelDTO crtissunitmod, final CertifiedIssuingUnit crtissunitentity) {
	

		crtissunitentity.setDenumire(crtissunitmod.getDenumire());
		crtissunitentity.setId(crtissunitmod.getId());

		
	}

}
