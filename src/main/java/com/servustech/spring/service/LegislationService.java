package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.Legislation;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.LegislationModelDTO;

public interface LegislationService {

	void addLegislation(BaseEntity legislation);

	void save(LegislationModelDTO legislationModelDTO);

	void updateLegislation(BaseEntity legislation);

	List<Legislation> listLegislation(String legislationName);

	List<LegislationModelDTO> listLegislation(int objectID);

	Object getLegislationById(int id, Class<? extends BaseEntity> clazz);

	void removeLegislation(int id, Class<? extends BaseEntity> clazz);

	void editLegislation(LegislationModelDTO legislationModelDTO);
}
