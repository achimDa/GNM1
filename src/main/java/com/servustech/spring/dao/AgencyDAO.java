package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.Agency;
import com.servustech.spring.model.base.BaseEntity;

public interface AgencyDAO {

	void addAgency(BaseEntity agency);

	void updateAgency(BaseEntity agency);

	List<Agency> listAgency(String agencyName);

	List<Agency> listAgency(int id);

	Agency getAgencyById(int id, Class<? extends BaseEntity> agencyclass);

	void removeAgency(int id, final Class<? extends BaseEntity> agencyclass);
}