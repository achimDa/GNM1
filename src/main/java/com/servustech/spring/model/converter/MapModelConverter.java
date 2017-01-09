package com.servustech.spring.model.converter; 

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.Map;
import com.servustech.spring.model.dto.MapModelDTO;



public class MapModelConverter {
	public static MapModelDTO toDTO(final Map model) {
		final MapModelDTO mapdto = new MapModelDTO();
		mapdto.setId(model.getId());
		mapdto.setDenumire(model.getDenumire());
		mapdto.setDescriere(model.getDescriere());
		mapdto.setFisier(model.getFisier());
		
		
		return mapdto;
	}

	public static List<MapModelDTO> toDTOList(final Collection<Map> mapList) {
		final List<MapModelDTO> map = new ArrayList<>();
		for (final Map mapModel : mapList) {
			map.add(toDTO(mapModel));
		}
		return map;
	}

	public static void toEntity(final MapModelDTO mapmod, final Map mapentity) {
	
		mapentity.setId(mapmod.getId());
		mapentity.setDenumire(mapmod.getDenumire());
		mapentity.setDescriere(mapmod.getDescriere());
		mapentity.setFisier(mapmod.getFisier());
		
	}
	
}