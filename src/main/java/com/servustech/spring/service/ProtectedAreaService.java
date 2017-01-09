package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.ProtectedArea;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.ProtectedAreaModelDTO;

public interface ProtectedAreaService {

	void addProtectedArea(BaseEntity protectedarea);

	void save(ProtectedAreaModelDTO protectedareaModelDTO);

	void updateProtectedArea(BaseEntity protectedarea);

	List<ProtectedArea> listProtectedArea(String protectedareaName);

	List<ProtectedAreaModelDTO> listProtectedArea(int objectID);

	Object getProtectedAreaById(int id, Class<? extends BaseEntity> clazz);

	void removeProtectedArea(int id, Class<? extends BaseEntity> clazz);

	void editProtectedArea(ProtectedAreaModelDTO protectedareaModelDTO);
}
