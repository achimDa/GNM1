package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.LegalForm;
import com.servustech.spring.model.base.BaseEntity;

public interface LegalFormDAO {

	void addLegalForm(BaseEntity legalform);

	void updateLegalForm(BaseEntity legalform);

	List<LegalForm> listLegalForm(String legalformName);

	List<LegalForm> listLegalForm();

	LegalForm getLegalFormById(int id, Class<? extends BaseEntity> legalformclass);

	void removeLegalForm(int id, final Class<? extends BaseEntity> legalformclass);
}