package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.EventCategoryDAO;
import com.servustech.spring.dao.RegulationsDAO;
import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.EventCategory_Regulations;
import com.servustech.spring.model.Regulations;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.RegulationsModelConverter;
import com.servustech.spring.model.dto.RegulationsModelDTO;

@Service
@Transactional

public class RegulationsServiceImpl implements RegulationsService {
	@Autowired
	private RegulationsDAO regulationsDAO;

	@Autowired
	private EventCategoryDAO eventCategoryDAO;

	@Override
	public void addRegulations(final BaseEntity regulations) {
		this.regulationsDAO.addRegulations(regulations);
	}

	@Override
	public void updateRegulations(final BaseEntity regulations) {
		this.regulationsDAO.updateRegulations(regulations);
	}

	@Override
	public void save(final RegulationsModelDTO regulationsModelDTO) {
		final Regulations regulations = new Regulations();
		RegulationsModelConverter.toEntity(regulationsModelDTO, regulations);

		// create connection between 2 tables
		final EventCategory_Regulations eventCategory_Regulations = new EventCategory_Regulations();
		eventCategory_Regulations.setRegulations(regulations);
		eventCategory_Regulations.setEventcategory(this.eventCategoryDAO
				.getEventCategoryById(regulationsModelDTO.getEventCategoryId(), EventCategory.class));
		regulations.getEventCategory_Regulations().add(eventCategory_Regulations);

		if (regulations.getId() == 0) {
			addRegulations(regulations);
		} else {
			updateRegulations(regulations);
		}

	}

	@Override
	public List<Regulations> listRegulations(final String regulationsName) {
		return this.regulationsDAO.listRegulations(regulationsName);
	}

	@Override
	public List<RegulationsModelDTO> listRegulations(final int objectID) {
		return RegulationsModelConverter.toDTOList(this.regulationsDAO.listRegulations(objectID));
	}

	@Override
	public Regulations getRegulationsById(final int id, final Class<? extends BaseEntity> regulationsclass) {
		return this.regulationsDAO.getRegulationsById(id, regulationsclass);
	}

	@Override
	public void removeRegulations(final int id, final Class<? extends BaseEntity> regulationsclass) {
		this.regulationsDAO.removeRegulations(id, regulationsclass);
	}

	@Override
	public void editRegulations(final RegulationsModelDTO regulationsModelDTO) {
		final Regulations regulations = getRegulationsById(regulationsModelDTO.getId(), Regulations.class);

		regulations.setContinut(regulationsModelDTO.getContinut());
		regulations.setCheckmark(regulationsModelDTO.isCheckmark());
		regulations.setId(regulationsModelDTO.getId());
		regulations.setNumar(regulationsModelDTO.getNumar());
		regulations.setTip(regulationsModelDTO.getTip());
		regulations.setTitlu(regulationsModelDTO.getTitlu());

		this.regulationsDAO.updateRegulations(regulations);

	}

}