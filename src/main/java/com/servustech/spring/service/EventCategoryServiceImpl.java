package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.EventCategoryDAO;
import com.servustech.spring.dao.LegislationDAO;
import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.EventCategoryModelConverter;
import com.servustech.spring.model.dto.EventCategoryModelDTO;

@Service
@Transactional

public class EventCategoryServiceImpl implements EventCategoryService {
	@Autowired
	private EventCategoryDAO eventDAO;

	@Autowired
	private LegislationDAO legislationDAO;

	@Override
	public void addEventCategory(final BaseEntity event) {
		this.eventDAO.addEventCategory(event);
	}

	@Override
	public void updateEventCategory(final BaseEntity event) {
		this.eventDAO.updateEventCategory(event);
	}

	@Override
	public void save(final EventCategoryModelDTO eventModelDTO) {
		final EventCategory event = new EventCategory();
		EventCategoryModelConverter.toEntity(eventModelDTO, event);
		// for (final LegislationModelDTO legislationModelDTO :
		// eventModelDTO.getLegislations()) {
		// final Legislation legislation = new Legislation();
		// LegislationModelConverter.toEntity(legislationModelDTO, legislation);
		// this.legislationDAO.addLegislation(legislation);
		// }
		if (event.getId() == 0) {
			addEventCategory(event);
		} else {
			updateEventCategory(event);
		}
	}

	@Override
	public List<EventCategory> listEventCategory(final String eventName) {
		return this.eventDAO.listEventCategory(eventName);
	}

	@Override
	public List<EventCategoryModelDTO> listEventCategory() {
		return EventCategoryModelConverter.toDTOList(this.eventDAO.listEventCategory());
	}

	@Override
	public EventCategory getEventCategoryById(final int id, final Class<? extends BaseEntity> eventclass) {
		return this.eventDAO.getEventCategoryById(id, eventclass);
	}

	@Override
	public void removeEventCategory(final int id, final Class<? extends BaseEntity> eventclass) {
		this.eventDAO.removeEventCategory(id, eventclass);
	}

	@Override
	public void editEventCategory(final EventCategoryModelDTO eventModelDTO) {
		final EventCategory event = getEventCategoryById(eventModelDTO.getId(), EventCategory.class);

		event.setDenumire(eventModelDTO.getDenumire());
		event.setDescriere(eventModelDTO.getDescriere());
		event.setId(eventModelDTO.getId());

		this.eventDAO.updateEventCategory(event);

	}

}