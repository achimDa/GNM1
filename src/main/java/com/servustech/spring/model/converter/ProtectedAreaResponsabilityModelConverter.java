package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.ProtectedAreaResponsability;
import com.servustech.spring.model.dto.ProtectedAreaResponsabilityModelDTO;


public class ProtectedAreaResponsabilityModelConverter {
	public static ProtectedAreaResponsabilityModelDTO toDTO(final ProtectedAreaResponsability model) {
		final ProtectedAreaResponsabilityModelDTO protecteddto = new ProtectedAreaResponsabilityModelDTO();
		protecteddto.setDenumire(model.getDenumire());
		protecteddto.setDescriere(model.getDescriere());
		protecteddto.setId(model.getId());
		
		return protecteddto;
	}

	public static List<ProtectedAreaResponsabilityModelDTO> toDTOList(final Collection<ProtectedAreaResponsability> protectedarearesponsabilityList) {
		final List<ProtectedAreaResponsabilityModelDTO> protectedarearesponsability = new ArrayList<>();
		for (final ProtectedAreaResponsability protectedarearesponsabilityModel : protectedarearesponsabilityList) {
			protectedarearesponsability.add(toDTO(protectedarearesponsabilityModel));
		}
		return protectedarearesponsability;
	}

	public static void toEntity(final ProtectedAreaResponsabilityModelDTO protectedmod, final ProtectedAreaResponsability protectedentity) {
	

		protectedentity.setDenumire(protectedmod.getDenumire());
		protectedentity.setId(protectedmod.getId());
		protectedentity.setDescriere(protectedmod.getDescriere());
		
	}

}
