package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.Regulations;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.RegulationsModelDTO;

public interface RegulationsService {

	void addRegulations(BaseEntity regulations);

	void save(RegulationsModelDTO regulationsModelDTO);

	void updateRegulations(BaseEntity regulations);

	List<Regulations> listRegulations(String regulationsName);

	List<RegulationsModelDTO> listRegulations(int objectID);

	Object getRegulationsById(int id, Class<? extends BaseEntity> clazz);

	void removeRegulations(int id, Class<? extends BaseEntity> clazz);

	void editRegulations(RegulationsModelDTO regulationsModelDTO);
}
