package com.servustech.spring.model.converter;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.EcologicalCorridor;
import com.servustech.spring.model.dto.EcologicalCorridorModelDTO;

public class EcologicalCorridorModelConverter {
	public static EcologicalCorridorModelDTO toDTO(final EcologicalCorridor model) {
		final EcologicalCorridorModelDTO ecologicalcorridordto = new EcologicalCorridorModelDTO();
		ecologicalcorridordto.setId(model.getId());
		ecologicalcorridordto.setDenumire(model.getDenumire());
		ecologicalcorridordto.setAnimalmigrator(model.isAnimalmigrator());
		ecologicalcorridordto.setAnimalmort(model.isAnimalmort());
		ecologicalcorridordto.setCategorie(model.getCategorie());
		ecologicalcorridordto.setDela(model.getDela());
		ecologicalcorridordto.setDenumirecoridor(model.getDenumirecoridor());
		ecologicalcorridordto.setDescrierecoridor(model.getDescrierecoridor());
		ecologicalcorridordto.setDimensiune(model.getDimensiune());
		ecologicalcorridordto.setNumarcoridor(model.getNumarcoridor());
		ecologicalcorridordto.setPanala(model.getPanala());
		ecologicalcorridordto.setSpecie(model.getSpecie());
		ecologicalcorridordto.setTipcoridor(model.getTipcoridor());
		ecologicalcorridordto.setUrmadeanimal(model.isUrmadeanimal());

		return ecologicalcorridordto;
	}

	public static List<EcologicalCorridorModelDTO> toDTOList(final Collection<EcologicalCorridor> ecologicalcorridorList) {
		final List<EcologicalCorridorModelDTO> ecologicalcorridor = new ArrayList<>();
		for (final EcologicalCorridor ecologicalcorridorModel : ecologicalcorridorList) {
			ecologicalcorridor.add(toDTO(ecologicalcorridorModel));
		}
		return ecologicalcorridor;
	}

	public static void toEntity(final EcologicalCorridorModelDTO ecologicalcorridormod, final EcologicalCorridor ecologicalcorridorentity) {
		
		
		ecologicalcorridorentity.setId(ecologicalcorridormod.getId());
		ecologicalcorridorentity.setDenumire(ecologicalcorridormod.getDenumire());
		ecologicalcorridorentity.setAnimalmigrator(ecologicalcorridormod.isAnimalmigrator());
		ecologicalcorridorentity.setAnimalmort(ecologicalcorridormod.isAnimalmort());
		ecologicalcorridorentity.setCategorie(ecologicalcorridormod.getCategorie());
		ecologicalcorridorentity.setDela(ecologicalcorridormod.getDela());
		ecologicalcorridorentity.setDenumirecoridor(ecologicalcorridormod.getDenumirecoridor());
		ecologicalcorridorentity.setDescrierecoridor(ecologicalcorridormod.getDescrierecoridor());
		ecologicalcorridorentity.setDimensiune(ecologicalcorridormod.getDimensiune());
		ecologicalcorridorentity.setNumarcoridor(ecologicalcorridormod.getNumarcoridor());
		ecologicalcorridorentity.setPanala(ecologicalcorridormod.getPanala());
		ecologicalcorridorentity.setSpecie(ecologicalcorridormod.getSpecie());
		ecologicalcorridorentity.setTipcoridor(ecologicalcorridormod.getTipcoridor());
		ecologicalcorridorentity.setUrmadeanimal(ecologicalcorridormod.isUrmadeanimal());

		
	}

}