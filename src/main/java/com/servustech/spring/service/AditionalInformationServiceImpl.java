package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.AditionalInformationDAO;
import com.servustech.spring.dao.EcologicalCorridorDAO;
import com.servustech.spring.model.AditionalInformation;
import com.servustech.spring.model.EcologicalCorridor;
import com.servustech.spring.model.EcologicalCorridor_AditionalInformation;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.AditionalInformationModelConverter;
import com.servustech.spring.model.dto.AditionalInformationModelDTO;

@Service
@Transactional

public class AditionalInformationServiceImpl implements AditionalInformationService {
	@Autowired
	private AditionalInformationDAO aditionalinformationDAO;

	@Autowired
	private EcologicalCorridorDAO ecologicalcorridorDAO;

	@Override
	public void addAditionalInformation(final BaseEntity aditionalinformation) {
		this.aditionalinformationDAO.addAditionalInformation(aditionalinformation);
	}

	@Override
	public void updateAditionalInformation(final BaseEntity aditionalinformation) {
		this.aditionalinformationDAO.updateAditionalInformation(aditionalinformation);
	}

	@Override
	public void save(final AditionalInformationModelDTO aditionalinformationModelDTO) {
		final AditionalInformation aditionalinformation = new AditionalInformation();
		AditionalInformationModelConverter.toEntity(aditionalinformationModelDTO, aditionalinformation);

		// create connection between 2 tables
		final EcologicalCorridor_AditionalInformation ecologicalCorridor_aditionalInformation = new EcologicalCorridor_AditionalInformation();
		ecologicalCorridor_aditionalInformation.setAditionalinformation(aditionalinformation);
		ecologicalCorridor_aditionalInformation
				.setEcologicalcorridor(this.ecologicalcorridorDAO.getEcologicalCorridorById(
						aditionalinformationModelDTO.getEcologicalCorridorId(), EcologicalCorridor.class));
		aditionalinformation.getEcologicalcorridor_Aditionalinformation().add(ecologicalCorridor_aditionalInformation);

		if (aditionalinformation.getId() == 0) {
			addAditionalInformation(aditionalinformation);
		} else {
			updateAditionalInformation(aditionalinformation);
		}

	}

	@Override
	public List<AditionalInformation> listAditionalInformation(final String aditionalinformationName) {
		return this.aditionalinformationDAO.listAditionalInformation(aditionalinformationName);
	}

	@Override
	public List<AditionalInformationModelDTO> listAditionalInformation(final int objectID) {
		return AditionalInformationModelConverter
				.toDTOList(this.aditionalinformationDAO.listAditionalInformation(objectID));
	}

	@Override
	public AditionalInformation getAditionalInformationById(final int id,
			final Class<? extends BaseEntity> aditionalinformationclass) {
		return this.aditionalinformationDAO.getAditionalInformationById(id, aditionalinformationclass);
	}

	@Override
	public void removeAditionalInformation(final int id, final Class<? extends BaseEntity> aditionalinformationclass) {
		this.aditionalinformationDAO.removeAditionalInformation(id, aditionalinformationclass);
	}

	@Override
	public void editAditionalInformation(final AditionalInformationModelDTO aditionalinformationModelDTO) {
		final AditionalInformation aditionalinformation = getAditionalInformationById(
				aditionalinformationModelDTO.getId(), AditionalInformation.class);

		aditionalinformation.setDenumire(aditionalinformationModelDTO.getDenumire());
		aditionalinformation.setDescriere(aditionalinformationModelDTO.getDescriere());
		aditionalinformation.setId(aditionalinformationModelDTO.getId());
		aditionalinformation.setInactiv(aditionalinformationModelDTO.isInactiv());

		this.aditionalinformationDAO.updateAditionalInformation(aditionalinformation);

	}

}