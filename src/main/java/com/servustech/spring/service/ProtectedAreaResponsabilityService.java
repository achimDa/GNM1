package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.ProtectedAreaResponsability;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.ProtectedAreaResponsabilityModelDTO;

public interface ProtectedAreaResponsabilityService {

	void addProtectedAreaResponsability(BaseEntity protectedarearesponsability);

	void save(ProtectedAreaResponsabilityModelDTO protectedModelDTO);

	void updateProtectedAreaResponsability(BaseEntity protectedarearesponsability);

	List<ProtectedAreaResponsability> listProtectedAreaResponsability(String protectedName);

	List<ProtectedAreaResponsabilityModelDTO> listProtectedAreaResponsability();

	Object getProtectedAreaResponsabilityById(int id, Class<? extends BaseEntity> clazz);

	void removeProtectedAreaResponsability(int id, Class<? extends BaseEntity> clazz);

	void editProtectedAreaResponsability(ProtectedAreaResponsabilityModelDTO protectedModelDTO);
}
