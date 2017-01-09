package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.Agency;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.AgencyModelDTO;

public interface AgencyService {

	void addAgency(BaseEntity agency);

	void save(AgencyModelDTO agencyModelDTO);

	void updateAgency(BaseEntity agency);

	List<Agency> listAgency(String agencyName);

	List<AgencyModelDTO> listAgency(int objectID);

	Object getAgencyById(int id, Class<? extends BaseEntity> clazz);

	void removeAgency(int id, Class<? extends BaseEntity> clazz);

	void editAgency(AgencyModelDTO agencyModelDTO);
}
