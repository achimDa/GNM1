package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.EcologicalCorridor;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.EcologicalCorridorModelDTO;

public interface EcologicalCorridorService {

	void addEcologicalCorridor(BaseEntity ecologicalcorridor);

	void save(EcologicalCorridorModelDTO ecologicalcorridorModelDTO);

	void updateEcologicalCorridor(BaseEntity ecologicalcorridor);

	List<EcologicalCorridor> listEcologicalCorridor(String ecologicalcorridorName);

	List<EcologicalCorridorModelDTO> listEcologicalCorridor();

	Object getEcologicalCorridorById(int id, Class<? extends BaseEntity> clazz);

	void removeEcologicalCorridor(int id, Class<? extends BaseEntity> clazz);

	void editEcologicalCorridor(EcologicalCorridorModelDTO ecologicalcorridorModelDTO);
}
