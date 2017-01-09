package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.ProtectedAreaType;
import com.servustech.spring.model.base.BaseEntity;

public interface ProtectedAreaTypeDAO {

	void addProtectedAreaType(BaseEntity protectedareatype);

	void updateProtectedAreaType(BaseEntity protectedareatype);

	List<ProtectedAreaType> listProtectedAreaType(String protectedareatypeName);

	List<ProtectedAreaType> listProtectedAreaType();

	ProtectedAreaType getProtectedAreaTypeById(int id, Class<? extends BaseEntity> protectedareatypeclass);

	void removeProtectedAreaType(int id, final Class<? extends BaseEntity> protectedareatypeclass);
}