package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.EcologicalCorridor;
import com.servustech.spring.model.base.BaseEntity;

public interface EcologicalCorridorDAO {

	void addEcologicalCorridor(BaseEntity event);

	void updateEcologicalCorridor(BaseEntity event);

	List<EcologicalCorridor> listEcologicalCorridor(String ecologicalcorridorName);

	List<EcologicalCorridor> listEcologicalCorridor();

	EcologicalCorridor getEcologicalCorridorById(int id, Class<? extends BaseEntity> ecologicalcorridorclass);

	void removeEcologicalCorridor(int id, final Class<? extends BaseEntity> ecologicalcorridorclass);
}