package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.AditionalInformationDAO;
import com.servustech.spring.dao.EcologicalCorridorDAO;
import com.servustech.spring.model.AditionalInformation;
import com.servustech.spring.model.EcologicalCorridor;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.AditionalInformationModelConverter;
import com.servustech.spring.model.converter.EcologicalCorridorModelConverter;
import com.servustech.spring.model.dto.AditionalInformationModelDTO;
import com.servustech.spring.model.dto.EcologicalCorridorModelDTO;

@Service
@Transactional

public class EcologicalCorridorServiceImpl implements EcologicalCorridorService {
	@Autowired
	private EcologicalCorridorDAO ecologicalcorridorDAO;

	@Autowired
	private AditionalInformationDAO aditionalinformationDAO;

	@Override
	public void addEcologicalCorridor(final BaseEntity ecologicalcorridor) {
		this.ecologicalcorridorDAO.addEcologicalCorridor(ecologicalcorridor);
	}

	@Override
	public void updateEcologicalCorridor(final BaseEntity ecologicalcorridor) {
		this.ecologicalcorridorDAO.updateEcologicalCorridor(ecologicalcorridor);
	}

	@Override
	public void save(final EcologicalCorridorModelDTO ecologicalcorridorModelDTO) {
		final EcologicalCorridor ecologicalcorridor = new EcologicalCorridor();
		EcologicalCorridorModelConverter.toEntity(ecologicalcorridorModelDTO, ecologicalcorridor);
		for (final AditionalInformationModelDTO aditionalinformationModelDTO : ecologicalcorridorModelDTO
				.getAditionalinformations()) {
			final AditionalInformation aditionalinformation = new AditionalInformation();
			AditionalInformationModelConverter.toEntity(aditionalinformationModelDTO, aditionalinformation);
			this.aditionalinformationDAO.addAditionalInformation(aditionalinformation);
		}
		if (ecologicalcorridor.getId() == 0) {
			addEcologicalCorridor(ecologicalcorridor);
		} else {
			updateEcologicalCorridor(ecologicalcorridor);
		}
	}

	@Override
	public List<EcologicalCorridor> listEcologicalCorridor(final String ecologicalcorridorName) {
		return this.ecologicalcorridorDAO.listEcologicalCorridor(ecologicalcorridorName);
	}

	@Override
	public List<EcologicalCorridorModelDTO> listEcologicalCorridor() {
		return EcologicalCorridorModelConverter.toDTOList(this.ecologicalcorridorDAO.listEcologicalCorridor());
	}

	@Override
	public EcologicalCorridor getEcologicalCorridorById(final int id,
			final Class<? extends BaseEntity> ecologicalcorridorclass) {
		return this.ecologicalcorridorDAO.getEcologicalCorridorById(id, ecologicalcorridorclass);
	}

	@Override
	public void removeEcologicalCorridor(final int id, final Class<? extends BaseEntity> ecologicalcorridorclass) {
		this.ecologicalcorridorDAO.removeEcologicalCorridor(id, ecologicalcorridorclass);
	}

	@Override
	public void editEcologicalCorridor(final EcologicalCorridorModelDTO ecologicalcorridorModelDTO) {
		final EcologicalCorridor ecologicalcorridor = getEcologicalCorridorById(ecologicalcorridorModelDTO.getId(),
				EcologicalCorridor.class);

		ecologicalcorridor.setAnimalmigrator(ecologicalcorridorModelDTO.isAnimalmigrator());
		ecologicalcorridor.setAnimalmort(ecologicalcorridorModelDTO.isAnimalmort());
		ecologicalcorridor.setUrmadeanimal(ecologicalcorridorModelDTO.isUrmadeanimal());
		ecologicalcorridor.setCategorie(ecologicalcorridorModelDTO.getCategorie());
		ecologicalcorridor.setDenumire(ecologicalcorridorModelDTO.getDenumire());
		ecologicalcorridor.setDela(ecologicalcorridorModelDTO.getDela());
		ecologicalcorridor.setDenumirecoridor(ecologicalcorridorModelDTO.getDenumirecoridor());
		ecologicalcorridor.setDescrierecoridor(ecologicalcorridorModelDTO.getDescrierecoridor());
		ecologicalcorridor.setDimensiune(ecologicalcorridorModelDTO.getDimensiune());
		ecologicalcorridor.setId(ecologicalcorridorModelDTO.getId());
		ecologicalcorridor.setNumarcoridor(ecologicalcorridorModelDTO.getNumarcoridor());
		ecologicalcorridor.setPanala(ecologicalcorridorModelDTO.getPanala());
		ecologicalcorridor.setSpecie(ecologicalcorridorModelDTO.getSpecie());
		ecologicalcorridor.setTipcoridor(ecologicalcorridorModelDTO.getTipcoridor());

		this.ecologicalcorridorDAO.updateEcologicalCorridor(ecologicalcorridor);

	}

}