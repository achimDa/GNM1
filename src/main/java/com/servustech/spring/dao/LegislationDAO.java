package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.Legislation;
import com.servustech.spring.model.base.BaseEntity;

public interface LegislationDAO {

	void addLegislation(BaseEntity legislation);

	void updateLegislation(BaseEntity legislation);

	List<Legislation> listLegislation(String legislationName);

	List<Legislation> listLegislation(int id);

	Legislation getLegislationById(int id, Class<? extends BaseEntity> legislationclass);

	void removeLegislation(int id, final Class<? extends BaseEntity> legislationclass);
}