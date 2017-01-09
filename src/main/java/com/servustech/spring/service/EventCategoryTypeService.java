package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.EventCategoryType;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.EventCategoryTypeModelDTO;

public interface EventCategoryTypeService {

	void addEventCategoryType(BaseEntity eventtype);

	void save(EventCategoryTypeModelDTO eventtypeModelDTO);

	void updateEventCategoryType(BaseEntity eventtype);

	List<EventCategoryType> listEventCategoryType(String eventtypeName);

	List<EventCategoryTypeModelDTO> listEventCategoryType();

	Object getEventCategoryTypeById(int id, Class<? extends BaseEntity> clazz);

	void removeEventCategoryType(int id, Class<? extends BaseEntity> clazz);

	void editEventCategoryType(EventCategoryTypeModelDTO eventtypeModelDTO);

	void addEventCategoryPicture(byte[] bytes);
}
