
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.ProtectedArea_Has_Map;
import com.servustech.spring.model.dto.ProtectedArea_Has_MapModelDTO;
import com.servustech.spring.model.Map;
import com.servustech.spring.model.ProtectedArea;

public class ProtectedArea_Has_MapModelConverter {
	public static ProtectedArea_Has_MapModelDTO toDTO(final ProtectedArea_Has_Map model) {
		final ProtectedArea_Has_MapModelDTO areadto = new ProtectedArea_Has_MapModelDTO();
		areadto.setMap(model.getMap().getId());
		areadto.setProtectedarea(model.getProtectedarea().getId());
	
		return areadto;
	}

	public static List<ProtectedArea_Has_MapModelDTO> toDTOList(final Collection<ProtectedArea_Has_Map> areaList) {
		final List<ProtectedArea_Has_MapModelDTO> areas = new ArrayList<>();
		for (final ProtectedArea_Has_Map areaModel : areaList) {
			areas.add(toDTO(areaModel));
		}
		return areas;
	}

	public static void toEntity(final ProtectedArea_Has_MapModelDTO areamod, final ProtectedArea_Has_Map areaentity) {
		final Map map = new Map();
		final ProtectedArea protectedarea = new ProtectedArea();

		map.setId(areamod.getMap());
		areaentity.setMap(map);
		
		protectedarea.setId(areamod.getProtectedarea());
		areaentity.setProtectedarea(protectedarea);

	}

}
