package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.dto.EventCategoryModelDTO;

public class EventCategoryModelConverter {
	public static EventCategoryModelDTO toDTO(final EventCategory model) {
		final EventCategoryModelDTO eventcategorydto = new EventCategoryModelDTO();
		eventcategorydto.setId(model.getId());
		eventcategorydto.setDenumire(model.getDenumire());
		eventcategorydto.setDescriere(model.getDescriere());
		eventcategorydto.setCoridoareecologice(model.isCoridoareecologice());
		eventcategorydto.setInfo(model.isCoridoareecologice());
		eventcategorydto.setPrioritate(model.getPrioritate());
		eventcategorydto.setSimbol(model.getSimbol());

		return eventcategorydto;
	}

	public static List<EventCategoryModelDTO> toDTOList(final Collection<EventCategory> eventcategoryList) {
		final List<EventCategoryModelDTO> eventcategory = new ArrayList<>();
		for (final EventCategory eventcategoryModel : eventcategoryList) {
			eventcategory.add(toDTO(eventcategoryModel));
		}
		return eventcategory;
	}

	public static void toEntity(final EventCategoryModelDTO eventcategorymod, final EventCategory eventcategoryentity) {

		eventcategoryentity.setId(eventcategorymod.getId());
		eventcategoryentity.setDenumire(eventcategorymod.getDenumire());
		eventcategoryentity.setDescriere(eventcategorymod.getDescriere());
		eventcategoryentity.setCoridoareecologice(eventcategorymod.isCoridoareecologice());
		eventcategoryentity.setInfo(eventcategorymod.isInfo());
		eventcategoryentity.setPrioritate(eventcategorymod.getPrioritate());
		eventcategoryentity.setSimbol(eventcategorymod.getSimbol());
	}

}