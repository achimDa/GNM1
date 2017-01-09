package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.EventCategoryTypeDAO;
import com.servustech.spring.model.EventCategoryType;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.EventCategoryTypeModelConverter;
import com.servustech.spring.model.dto.EventCategoryTypeModelDTO;

@Service
@Transactional

public class EventCategoryTypeServiceImpl implements EventCategoryTypeService {
	@Autowired
	private EventCategoryTypeDAO eventtypeDAO;

	@Override
	public void addEventCategoryType(final BaseEntity eventtype) {
		this.eventtypeDAO.addEventCategoryType(eventtype);
	}

	@Override
	public void updateEventCategoryType(final BaseEntity eventtype) {
		this.eventtypeDAO.updateEventCategoryType(eventtype);
	}

	@Override
	public void save(final EventCategoryTypeModelDTO eventtypeModelDTO) {
		final EventCategoryType eventtype = new EventCategoryType();
		EventCategoryTypeModelConverter.toEntity(eventtypeModelDTO, eventtype);
		if (eventtype.getId() == 0) {
			addEventCategoryType(eventtype);
		} else {
			updateEventCategoryType(eventtype);
		}
	}

	@Override
	public List<EventCategoryType> listEventCategoryType(final String eventtypeName) {
		return this.eventtypeDAO.listEventCategoryType(eventtypeName);
	}

	@Override
	public List<EventCategoryTypeModelDTO> listEventCategoryType() {
		return EventCategoryTypeModelConverter.toDTOList(this.eventtypeDAO.listEventCategoryType());
	}

	@Override
	public EventCategoryType getEventCategoryTypeById(final int id, final Class<? extends BaseEntity> eventtypeclass) {
		return this.eventtypeDAO.getEventCategoryTypeById(id, eventtypeclass);
	}

	@Override
	public void removeEventCategoryType(final int id, final Class<? extends BaseEntity> eventtypeclass) {
		this.eventtypeDAO.removeEventCategoryType(id, eventtypeclass);
	}

	@Override
	public void editEventCategoryType(final EventCategoryTypeModelDTO eventtypeModelDTO) {
		final EventCategoryType eventtype = getEventCategoryTypeById(eventtypeModelDTO.getId(),
				EventCategoryType.class);

		eventtype.setDescriere(eventtypeModelDTO.getDescriere());
		eventtype.setPicture(eventtypeModelDTO.getPicture());
		eventtype.setId(eventtypeModelDTO.getId());

		this.eventtypeDAO.updateEventCategoryType(eventtype);

	}

	@Override
	public void addEventCategoryPicture(final byte[] bytes) {

	}

}