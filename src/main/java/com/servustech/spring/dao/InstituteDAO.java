package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.Institute;
import com.servustech.spring.model.base.BaseEntity;

public interface InstituteDAO {

	void addInstitute(BaseEntity institute);

	void updateInstitute(BaseEntity institute);

	List<Institute> listInstitute(String instituteName);

	List<Institute> listInstitute(int id);

	Institute getInstituteById(int id, Class<? extends BaseEntity> instituteclass);

	void removeInstitute(int id, final Class<? extends BaseEntity> instituteclass);
}