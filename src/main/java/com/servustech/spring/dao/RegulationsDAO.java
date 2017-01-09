package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.Regulations;
import com.servustech.spring.model.base.BaseEntity;

public interface RegulationsDAO {

	void addRegulations(BaseEntity regulations);

	void updateRegulations(BaseEntity regulations);

	List<Regulations> listRegulations(String regulationsName);

	List<Regulations> listRegulations(int id);

	Regulations getRegulationsById(int id, Class<? extends BaseEntity> regulationsclass);

	void removeRegulations(int id, final Class<? extends BaseEntity> regulationsclass);
}