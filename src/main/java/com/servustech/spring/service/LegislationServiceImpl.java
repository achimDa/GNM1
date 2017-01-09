package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.EventCategoryDAO;
import com.servustech.spring.dao.LegislationDAO;
import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.EventCategory_Legislation;
import com.servustech.spring.model.Legislation;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.LegislationModelConverter;
import com.servustech.spring.model.dto.LegislationModelDTO;

@Service
@Transactional

public class LegislationServiceImpl implements LegislationService {
	@Autowired
	private LegislationDAO legislationDAO;

	@Autowired
	private EventCategoryDAO eventCategoryDAO;

	@Override
	public void addLegislation(final BaseEntity legislation) {
		this.legislationDAO.addLegislation(legislation);
	}

	@Override
	public void updateLegislation(final BaseEntity legislation) {
		this.legislationDAO.updateLegislation(legislation);
	}

	@Override
	public void save(final LegislationModelDTO legislationModelDTO) {
		final Legislation legislation = new Legislation();
		LegislationModelConverter.toEntity(legislationModelDTO, legislation);

		// create connection between 2 tables
		final EventCategory_Legislation eventCategory_Legislation = new EventCategory_Legislation();
		eventCategory_Legislation.setLegislation(legislation);
		eventCategory_Legislation.setEventcategory(this.eventCategoryDAO
				.getEventCategoryById(legislationModelDTO.getEventCategoryId(), EventCategory.class));
		legislation.getEventCategory_Legislations().add(eventCategory_Legislation);

		if (legislation.getId() == 0) {
			addLegislation(legislation);
		} else {
			updateLegislation(legislation);
		}

	}

	@Override
	public List<Legislation> listLegislation(final String legislationName) {
		return this.legislationDAO.listLegislation(legislationName);
	}

	@Override
	public List<LegislationModelDTO> listLegislation(final int objectID) {
		return LegislationModelConverter.toDTOList(this.legislationDAO.listLegislation(objectID));
	}

	@Override
	public Legislation getLegislationById(final int id, final Class<? extends BaseEntity> legislationclass) {
		return this.legislationDAO.getLegislationById(id, legislationclass);
	}

	@Override
	public void removeLegislation(final int id, final Class<? extends BaseEntity> legislationclass) {
		this.legislationDAO.removeLegislation(id, legislationclass);
	}

	@Override
	public void editLegislation(final LegislationModelDTO legislationModelDTO) {
		final Legislation legislation = getLegislationById(legislationModelDTO.getId(), Legislation.class);

		legislation.setContinut(legislationModelDTO.getContinut());
		legislation.setCheckmark(legislationModelDTO.isCheckmark());
		legislation.setId(legislationModelDTO.getId());
		legislation.setNumar(legislationModelDTO.getNumar());
		legislation.setTip(legislationModelDTO.getTip());
		legislation.setTitlu(legislationModelDTO.getTitlu());

		this.legislationDAO.updateLegislation(legislation);

	}

}