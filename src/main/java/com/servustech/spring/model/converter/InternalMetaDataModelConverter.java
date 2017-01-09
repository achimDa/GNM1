
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.InternalMetaData;
import com.servustech.spring.model.MetaDataMap;
import com.servustech.spring.model.dto.InternalMetaDataModelDTO;

public class InternalMetaDataModelConverter {
	public static InternalMetaDataModelDTO toDTO(final InternalMetaData model) {
		final InternalMetaDataModelDTO internaldto = new InternalMetaDataModelDTO();
		internaldto.setDenumire(model.getDenumire());
		internaldto.setMetadatamap(model.getMetadatamap().getId());
		internaldto.setId(model.getId());
		internaldto.setExternalId(model.getExternalId());
		
		return internaldto;
	}

	public static List<InternalMetaDataModelDTO> toDTOList(final Collection<InternalMetaData> internalList) {
		final List<InternalMetaDataModelDTO> internal = new ArrayList<>();
		for (final InternalMetaData internalModel : internalList) {
			internal.add(toDTO(internalModel));
		}
		return internal;
	}

	public static void toEntity(final InternalMetaDataModelDTO internalmod, final InternalMetaData internalentity) {
		final MetaDataMap metadatamap = new MetaDataMap();

		metadatamap.setId(internalmod.getMetadatamap());
		internalentity.setMetadatamap(metadatamap);
		internalentity.setDenumire(internalmod.getDenumire());
		internalentity.setExternalId(internalmod.getExternalId());
		internalentity.setId(internalmod.getId());
		
	}

}
