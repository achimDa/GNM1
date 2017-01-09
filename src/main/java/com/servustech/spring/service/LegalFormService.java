package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.LegalForm;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.LegalFormModelDTO;

public interface LegalFormService {

	void addLegalForm(BaseEntity legalform);

	void save(LegalFormModelDTO legalformModelDTO);

	void updateLegalForm(BaseEntity legalform);

	List<LegalForm> listLegalForm(String legalformName);

	List<LegalFormModelDTO> listLegalForm();

	Object getLegalFormById(int id, Class<? extends BaseEntity> clazz);

	void removeLegalForm(int id, Class<? extends BaseEntity> clazz);

	void editLegalForm(LegalFormModelDTO legalformModelDTO);
}
