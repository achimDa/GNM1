
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.MetaDataMap;
import com.servustech.spring.model.Map;
import com.servustech.spring.model.MetaDataType;
import com.servustech.spring.model.dto.MetaDataMapModelDTO;

public class MetaDataMapModelConverter {
	public static MetaDataMapModelDTO toDTO(final MetaDataMap model) {
		final MetaDataMapModelDTO metamapdto = new MetaDataMapModelDTO();
		
		
		metamapdto.setId(model.getId());
		metamapdto.setIdHarta(model.getMap().getId());
		metamapdto.setTipMetadataId(model.getMetadatatype().getId());
		metamapdto.setNumar(model.getNumar());
		metamapdto.setEtc(model.getEtc());
		
		return metamapdto;
	}

	public static List<MetaDataMapModelDTO> toDTOList(final Collection<MetaDataMap> metamapList) {
		final List<MetaDataMapModelDTO> metamap = new ArrayList<>();
		for (final MetaDataMap metamapModel : metamapList) {
			metamap.add(toDTO(metamapModel));
		}
		return metamap;
	}

	public static void toEntity(final MetaDataMapModelDTO metamapmod, final MetaDataMap metamapentity) {
		final Map map = new Map();
		final MetaDataType metadatatype = new MetaDataType();
		
		map.setId(metamapmod.getIdHarta());
		metamapentity.setMap(map);
		metadatatype.setId(metamapmod.getTipMetadataId());
		metamapentity.setMetadatatype(metadatatype);
				
		metamapentity.setEtc(metamapmod.getEtc());
		metamapentity.setNumar(metamapmod.getNumar());
		metamapentity.setId(metamapmod.getId());

	}

}
