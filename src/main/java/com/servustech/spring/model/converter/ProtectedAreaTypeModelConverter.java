package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.ProtectedAreaType;
import com.servustech.spring.model.dto.ProtectedAreaTypeModelDTO;


public class ProtectedAreaTypeModelConverter {
	public static ProtectedAreaTypeModelDTO toDTO(final ProtectedAreaType model) {
		final ProtectedAreaTypeModelDTO protecteddto = new ProtectedAreaTypeModelDTO();
		protecteddto.setDenumire(model.getDenumire());
		protecteddto.setId(model.getId());
		
		return protecteddto;
	}

	public static List<ProtectedAreaTypeModelDTO> toDTOList(final Collection<ProtectedAreaType> protectedList) {
		final List<ProtectedAreaTypeModelDTO> protectedarea = new ArrayList<>();
		for (final ProtectedAreaType protectedModel : protectedList) {
			protectedarea.add(toDTO(protectedModel));
		}
		return protectedarea;
	}

	public static void toEntity(final ProtectedAreaTypeModelDTO protectedmod, final ProtectedAreaType protectedentity) {
	

		protectedentity.setDenumire(protectedmod.getDenumire());
		protectedentity.setId(protectedmod.getId());
		
		
	}

}
