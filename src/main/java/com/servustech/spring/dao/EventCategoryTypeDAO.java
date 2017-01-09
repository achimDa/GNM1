package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.EventCategoryType;
import com.servustech.spring.model.base.BaseEntity;

public interface EventCategoryTypeDAO {

	void addEventCategoryType(BaseEntity eventtype);

	void updateEventCategoryType(BaseEntity eventtype);

	List<EventCategoryType> listEventCategoryType(String eventtypeName);

	List<EventCategoryType> listEventCategoryType();

	EventCategoryType getEventCategoryTypeById(int id, Class<? extends BaseEntity> eventtypeclass);

	void removeEventCategoryType(int id, final Class<? extends BaseEntity> eventtypeclass);
}