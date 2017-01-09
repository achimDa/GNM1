
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.EventCategoryType;
import com.servustech.spring.model.dto.EventCategoryTypeModelDTO;

public class EventCategoryTypeModelConverter {
	public static EventCategoryTypeModelDTO toDTO(final EventCategoryType model) {
		final EventCategoryTypeModelDTO eventtypedto = new EventCategoryTypeModelDTO();
		eventtypedto.setDescriere(model.getDescriere());
		eventtypedto.setPicture(model.getPicture());
		eventtypedto.setId(model.getId());

		return eventtypedto;
	}

	public static List<EventCategoryTypeModelDTO> toDTOList(final Collection<EventCategoryType> eventtypeList) {
		final List<EventCategoryTypeModelDTO> eventtype = new ArrayList<>();
		for (final EventCategoryType eventtypeModel : eventtypeList) {
			eventtype.add(toDTO(eventtypeModel));
		}
		return eventtype;
	}

	public static void toEntity(final EventCategoryTypeModelDTO eventtypemod, final EventCategoryType eventtypeentity) {

		eventtypeentity.setDescriere(eventtypemod.getDescriere());
		eventtypeentity.setPicture(eventtypemod.getPicture());
		eventtypeentity.setId(eventtypemod.getId());

	}

}
