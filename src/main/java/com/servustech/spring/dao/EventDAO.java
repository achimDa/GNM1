package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.Event;
import com.servustech.spring.model.base.BaseEntity;

public interface EventDAO {

	void addEvent(BaseEntity event);

	void updateEvent(BaseEntity event);

	List<Event> listEvent(String eventName);

	List<Event> listEvent(int id);

	Event getEventById(int id, Class<? extends BaseEntity> eventclass);

	void removeEvent(int id, final Class<? extends BaseEntity> eventclass);
}