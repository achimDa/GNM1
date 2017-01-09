package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.ProtectedAreaType;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.ProtectedAreaTypeModelDTO;

public interface ProtectedAreaTypeService {

	void addProtectedAreaType(BaseEntity protectedareatype);

	void save(ProtectedAreaTypeModelDTO protectedModelDTO);

	void updateProtectedAreaType(BaseEntity protectedareatype);

	List<ProtectedAreaType> listProtectedAreaType(String protectedName);

	List<ProtectedAreaTypeModelDTO> listProtectedAreaType();

	Object getProtectedAreaTypeById(int id, Class<? extends BaseEntity> clazz);

	void removeProtectedAreaType(int id, Class<? extends BaseEntity> clazz);

	void editProtectedAreaType(ProtectedAreaTypeModelDTO protectedModelDTO);
}
