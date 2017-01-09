
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.ProtectedArea;
import com.servustech.spring.model.ProtectedAreaType;
import com.servustech.spring.model.dto.ProtectedAreaModelDTO;
import com.servustech.spring.model.ProtectedAreaResponsability;

public class ProtectedAreaModelConverter {
	public static ProtectedAreaModelDTO toDTO(final ProtectedArea model) {
		final ProtectedAreaModelDTO protectedareadto = new ProtectedAreaModelDTO();
		
		protectedareadto.setDenumire(model.getDenumire());
		protectedareadto.setDescriere(model.getDescriere());
		protectedareadto.setTipArieProtejataId(model.getProtectedtype().getId());
		protectedareadto.setResponsabilitateArieProtejataId(model.getProtectedarearesponsability().getId());
		protectedareadto.setId(model.getId());
		return protectedareadto;
	}

	public static List<ProtectedAreaModelDTO> toDTOList(final Collection<ProtectedArea> protectedareaList) {
		final List<ProtectedAreaModelDTO> protectedareas = new ArrayList<>();
		for (final ProtectedArea protectedareaModel : protectedareaList) {
			protectedareas.add(toDTO(protectedareaModel));
		}
		return protectedareas;
	}

	public static void toEntity(final ProtectedAreaModelDTO protectedareamod, final ProtectedArea protectedareaentity) {
		final ProtectedAreaType protectedtype = new ProtectedAreaType();
		final ProtectedAreaResponsability protectedarearesponsability = new ProtectedAreaResponsability();
		
		
		protectedtype.setId(protectedareamod.getTipArieProtejataId());
		protectedareaentity.setProtectedtype(protectedtype);
		protectedarearesponsability.setId(protectedareamod.getResponsabilitateArieProtejataId());
		protectedareaentity.setProtectedarearesponsability(protectedarearesponsability);
		
		protectedareaentity.setDenumire(protectedareamod.getDenumire());
		protectedareaentity.setDescriere(protectedareamod.getDescriere());
		protectedareaentity.setId(protectedareamod.getId());

	}

}
