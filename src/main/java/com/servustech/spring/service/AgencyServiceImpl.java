package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.AgencyDAO;
import com.servustech.spring.model.Agency;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.AgencyModelConverter;
import com.servustech.spring.model.dto.AgencyModelDTO;

@Service
@Transactional

public class AgencyServiceImpl implements AgencyService {
	@Autowired
	private AgencyDAO agencyDAO;

	@Override
	public void addAgency(final BaseEntity agency) {
		agencyDAO.addAgency(agency);
	}

	@Override
	public void updateAgency(final BaseEntity agency) {
		agencyDAO.updateAgency(agency);
	}

	@Override
	public void save(final AgencyModelDTO agencyModelDTO) {
		final Agency agency = new Agency();
		AgencyModelConverter.toEntity(agencyModelDTO, agency);
		if (agency.getId() == 0) {
			addAgency(agency);
		} else {
			updateAgency(agency);
		}
	}

	@Override
	public List<Agency> listAgency(final String agencyName) {
		return agencyDAO.listAgency(agencyName);
	}

	@Override
	public List<AgencyModelDTO> listAgency(final int objectID) {
		return AgencyModelConverter.toDTOList(agencyDAO.listAgency(objectID));
	}

	@Override
	public Agency getAgencyById(final int id, final Class<? extends BaseEntity> agencyclass) {
		return agencyDAO.getAgencyById(id, agencyclass);
	}

	@Override
	public void removeAgency(final int id, final Class<? extends BaseEntity> agencyclass) {
		agencyDAO.removeAgency(id, agencyclass);
	}

	@Override
	public void editAgency(final AgencyModelDTO agencyModelDTO) {
		final Agency agency = getAgencyById(agencyModelDTO.getId(), Agency.class);

		agency.setCui(agencyModelDTO.getCui());
		agency.setLogo(agencyModelDTO.getLogo());
		
		agencyDAO.updateAgency(agency);

	}

}