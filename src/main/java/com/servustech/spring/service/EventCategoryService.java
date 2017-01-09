package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.EventCategoryModelDTO;

public interface EventCategoryService {

	void addEventCategory(BaseEntity event);

	void save(EventCategoryModelDTO eventModelDTO);

	void updateEventCategory(BaseEntity event);

	List<EventCategory> listEventCategory(String eventName);

	List<EventCategoryModelDTO> listEventCategory();

	Object getEventCategoryById(int id, Class<? extends BaseEntity> clazz);

	void removeEventCategory(int id, Class<? extends BaseEntity> clazz);

	void editEventCategory(EventCategoryModelDTO eventModelDTO);
}
