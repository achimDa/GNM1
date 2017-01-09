package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.MetaDataType;
import com.servustech.spring.model.dto.MetaDataTypeModelDTO;


public class MetaDataTypeModelConverter {
	public static MetaDataTypeModelDTO toDTO(final MetaDataType model) {
		final MetaDataTypeModelDTO metatypedto = new MetaDataTypeModelDTO();
		metatypedto.setDenumire(model.getDenumire());
		metatypedto.setId(model.getId());
		
		return metatypedto;
	}

	public static List<MetaDataTypeModelDTO> toDTOList(final Collection<MetaDataType> metatypeList) {
		final List<MetaDataTypeModelDTO> metatype = new ArrayList<>();
		for (final MetaDataType metatypeModel : metatypeList) {
			metatype.add(toDTO(metatypeModel));
		}
		return metatype;
	}

	public static void toEntity(final MetaDataTypeModelDTO metatypemod, final MetaDataType metatypeentity) {
	

		metatypeentity.setDenumire(metatypemod.getDenumire());
		metatypeentity.setId(metatypemod.getId());
	
		
	}

}
