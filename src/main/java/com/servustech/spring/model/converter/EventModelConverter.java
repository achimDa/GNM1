
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.Event;
import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.dto.EventModelDTO;

public class EventModelConverter {
	public static EventModelDTO toDTO(final Event model) {
		final EventModelDTO eventdto = new EventModelDTO();
		eventdto.setDescriere(model.getDescriere());
		eventdto.setAnimal(model.getAnimal());
		eventdto.setIdcategorieeveniment(model.getEventcategory().getId());
		eventdto.setId(model.getId());
		eventdto.setCategorie(model.getCategorie());
		eventdto.setDimensiune(model.getDimensiune());
		eventdto.setSpecie(model.getSpecie());
		
		return eventdto;
	}

	public static List<EventModelDTO> toDTOList(final Collection<Event> eventList) {
		final List<EventModelDTO> event = new ArrayList<>();
		for (final Event eventModel : eventList) {
			event.add(toDTO(eventModel));
		}
		return event;
	}

	public static void toEntity(final EventModelDTO eventmod, final Event evententity) {
		final EventCategory eventcategory = new EventCategory();

		eventcategory.setId(eventmod.getIdcategorieeveniment());
		evententity.setEventcategory(eventcategory);
		evententity.setAnimal(eventmod.getAnimal());
		evententity.setCategorie(eventmod.getCategorie());
		evententity.setDescriere(eventmod.getDescriere());
		evententity.setDimensiune(eventmod.getDimensiune());
		evententity.setId(eventmod.getId());
		evententity.setSpecie(eventmod.getSpecie());
		
	}

}
