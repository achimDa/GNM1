package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.Event;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.EventModelDTO;

public interface EventService {

	void addEvent(BaseEntity event);

	void save(EventModelDTO eventModelDTO);

	void updateEvent(BaseEntity event);

	List<Event> listEvent(String eventName);

	List<EventModelDTO> listEvent(int objectID);

	Object getEventById(int id, Class<? extends BaseEntity> clazz);

	void removeEvent(int id, Class<? extends BaseEntity> clazz);

	void editEvent(EventModelDTO eventModelDTO);
}
