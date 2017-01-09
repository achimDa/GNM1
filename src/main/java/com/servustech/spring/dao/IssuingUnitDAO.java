package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.IssuingUnit;
import com.servustech.spring.model.base.BaseEntity;

public interface IssuingUnitDAO {

	void addIssuingUnit(BaseEntity issunit);

	void updateIssuingUnit(BaseEntity issunit);

	List<IssuingUnit> listIssuingUnit(String issunitName);

	List<IssuingUnit> listIssuingUnit();

	IssuingUnit getIssuingUnitById(int id, Class<? extends BaseEntity> issunitclass);

	void removeIssuingUnit(int id, final Class<? extends BaseEntity> issunitclass);
}