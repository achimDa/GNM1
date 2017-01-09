package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.LegalFormDAO;
import com.servustech.spring.model.LegalForm;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.LegalFormModelConverter;
import com.servustech.spring.model.dto.LegalFormModelDTO;

@Service
@Transactional

public class LegalFormServiceImpl implements LegalFormService {
	@Autowired
	private LegalFormDAO legalDAO;

	@Override
	public void addLegalForm(final BaseEntity legalform) {
		legalDAO.addLegalForm(legalform);
	}

	@Override
	public void updateLegalForm(final BaseEntity legalform) {
		legalDAO.updateLegalForm(legalform);
	}

	@Override
	public void save(final LegalFormModelDTO legalformModelDTO) {
		final LegalForm legalform = new LegalForm();
		LegalFormModelConverter.toEntity(legalformModelDTO, legalform);
		if (legalform.getId() == 0) {
			addLegalForm(legalform);
		} else {
			updateLegalForm(legalform);
		}
	}

	@Override
	public List<LegalForm> listLegalForm(final String legalformName) {
		return legalDAO.listLegalForm(legalformName);
	}

	@Override
	public List<LegalFormModelDTO> listLegalForm() {
		return LegalFormModelConverter.toDTOList(legalDAO.listLegalForm());
	}

	@Override
	public LegalForm getLegalFormById(final int id, final Class<? extends BaseEntity> legalformclass) {
		return legalDAO.getLegalFormById(id, legalformclass);
	}

	@Override
	public void removeLegalForm(final int id, final Class<? extends BaseEntity> legalformclass) {
		legalDAO.removeLegalForm(id, legalformclass);
	}

	@Override
	public void editLegalForm(final LegalFormModelDTO legalformModelDTO) {
		final LegalForm legalform = getLegalFormById(legalformModelDTO.getId(), LegalForm.class);

		legalform.setDenumire(legalformModelDTO.getDenumire());
		legalform.setId(legalformModelDTO.getId());

		legalDAO.updateLegalForm(legalform);

	}

}