package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.ProtectedArea;
import com.servustech.spring.model.base.BaseEntity;

public interface ProtectedAreaDAO {

	void addProtectedArea(BaseEntity protectedarea);

	void updateProtectedArea(BaseEntity protectedarea);

	List<ProtectedArea> listProtectedArea(String protectedareaName);

	List<ProtectedArea> listProtectedArea(int id);

	ProtectedArea getProtectedAreaById(int id, Class<? extends BaseEntity> protectedareaclass);

	void removeProtectedArea(int id, final Class<? extends BaseEntity> protectedareaclass);
}