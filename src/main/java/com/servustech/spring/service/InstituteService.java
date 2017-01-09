package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.Institute;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.InstituteModelDTO;

public interface InstituteService {

	void addInstitute(BaseEntity institute);

	void save(InstituteModelDTO instituteModelDTO);

	void updateInstitute(BaseEntity institute);

	List<Institute> listInstitute(String instituteName);

	List<InstituteModelDTO> listInstitute(int objectID);

	Object getInstituteById(int id, Class<? extends BaseEntity> clazz);

	void removeInstitute(int id, Class<? extends BaseEntity> clazz);

	void editInstitute(InstituteModelDTO instituteModelDTO);
}
