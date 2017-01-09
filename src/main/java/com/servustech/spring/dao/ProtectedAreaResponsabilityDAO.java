package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.ProtectedAreaResponsability;
import com.servustech.spring.model.base.BaseEntity;

public interface ProtectedAreaResponsabilityDAO {

	void addProtectedAreaResponsability(BaseEntity protectedarearesponsability);

	void updateProtectedAreaResponsability(BaseEntity protectedarearesponsability);

	List<ProtectedAreaResponsability> listProtectedAreaResponsability(String protectedarearesponsabilityName);

	List<ProtectedAreaResponsability> listProtectedAreaResponsability();

	ProtectedAreaResponsability getProtectedAreaResponsabilityById(int id, Class<? extends BaseEntity> protectedarearesponsabilityclass);

	void removeProtectedAreaResponsability(int id, final Class<? extends BaseEntity> protectedarearesponsabilityclass);
}