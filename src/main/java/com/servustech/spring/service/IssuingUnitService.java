package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.IssuingUnit;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.IssuingUnitModelDTO;

public interface IssuingUnitService {

	void addIssuingUnit(BaseEntity issunit);

	void save(IssuingUnitModelDTO issunitModelDTO);

	void updateIssuingUnit(BaseEntity issunit);

	List<IssuingUnit> listIssuingUnit(String issunitName);

	List<IssuingUnitModelDTO> listIssuingUnit();

	Object getIssuingUnitById(int id, Class<? extends BaseEntity> clazz);

	void removeIssuingUnit(int id, Class<? extends BaseEntity> clazz);
	
	void editIssuingUnit(IssuingUnitModelDTO issunitModelDTO);
}
