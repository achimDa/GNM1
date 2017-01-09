package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.base.BaseEntity;

public interface EventCategoryDAO {

	void addEventCategory(BaseEntity event);

	void updateEventCategory(BaseEntity event);

	List<EventCategory> listEventCategory(String eventName);

	List<EventCategory> listEventCategory();

	EventCategory getEventCategoryById(int id, Class<? extends BaseEntity> eventclass);

	void removeEventCategory(int id, final Class<? extends BaseEntity> eventclass);
}