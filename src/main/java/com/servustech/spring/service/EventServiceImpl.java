package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.EventDAO;
import com.servustech.spring.model.Event;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.EventModelConverter;
import com.servustech.spring.model.dto.EventModelDTO;

@Service
@Transactional

public class EventServiceImpl implements EventService {
	@Autowired
	private EventDAO eventDAO;

	@Override
	public void addEvent(final BaseEntity event) {
		eventDAO.addEvent(event);
	}

	@Override
	public void updateEvent(final BaseEntity event) {
		eventDAO.updateEvent(event);
	}

	@Override
	public void save(final EventModelDTO eventModelDTO) {
		final Event event = new Event();
		EventModelConverter.toEntity(eventModelDTO, event);
		if (event.getId() == 0) {
			addEvent(event);
		} else {
			updateEvent(event);
		}
	}

	@Override
	public List<Event> listEvent(final String eventName) {
		return eventDAO.listEvent(eventName);
	}

	@Override
	public List<EventModelDTO> listEvent(final int objectID) {
		return EventModelConverter.toDTOList(eventDAO.listEvent(objectID));
	}

	@Override
	public Event getEventById(final int id, final Class<? extends BaseEntity> eventclass) {
		return eventDAO.getEventById(id, eventclass);
	}

	@Override
	public void removeEvent(final int id, final Class<? extends BaseEntity> eventclass) {
		eventDAO.removeEvent(id, eventclass);
	}

	@Override
	public void editEvent(final EventModelDTO eventModelDTO) {
		final Event event = getEventById(eventModelDTO.getId(), Event.class);

		event.setDescriere(eventModelDTO.getDescriere());
		event.setAnimal(eventModelDTO.getAnimal());
		event.setCategorie(eventModelDTO.getCategorie());
		event.setDimensiune(eventModelDTO.getDimensiune());
		event.setId(eventModelDTO.getId());
		event.setSpecie(eventModelDTO.getSpecie());
		
		eventDAO.updateEvent(event);

	}

}