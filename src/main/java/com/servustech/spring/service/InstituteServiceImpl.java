package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.InstituteDAO;
import com.servustech.spring.model.Institute;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.InstituteModelConverter;
import com.servustech.spring.model.dto.InstituteModelDTO;

@Service
@Transactional

public class InstituteServiceImpl implements InstituteService {
	@Autowired
	private InstituteDAO instituteDAO;

	@Override
	public void addInstitute(final BaseEntity institute) {
		instituteDAO.addInstitute(institute);
	}

	@Override
	public void updateInstitute(final BaseEntity institute) {
		instituteDAO.updateInstitute(institute);
	}

	@Override
	public void save(final InstituteModelDTO instituteModelDTO) {
		final Institute institute = new Institute();
		InstituteModelConverter.toEntity(instituteModelDTO, institute);
		if (institute.getId() == 0) {
			addInstitute(institute);
		} else {
			updateInstitute(institute);
		}
	}

	@Override
	public List<Institute> listInstitute(final String instituteName) {
		return instituteDAO.listInstitute(instituteName);
	}

	@Override
	public List<InstituteModelDTO> listInstitute(final int objectID) {
		return InstituteModelConverter.toDTOList(instituteDAO.listInstitute(objectID));
	}

	@Override
	public Institute getInstituteById(final int id, final Class<? extends BaseEntity> instituteclass) {
		return instituteDAO.getInstituteById(id, instituteclass);
	}

	@Override
	public void removeInstitute(final int id, final Class<? extends BaseEntity> instituteclass) {
		instituteDAO.removeInstitute(id, instituteclass);
	}

	@Override
	public void editInstitute(final InstituteModelDTO instituteModelDTO) {
		final Institute institute = getInstituteById(instituteModelDTO.getId(), Institute.class);

		institute.setAtributFiscal(instituteModelDTO.getAtributFiscal());
		institute.setCapitalSocial(instituteModelDTO.getCapitalSocial());
		institute.setCodCaen(instituteModelDTO.getCodCaen());
		institute.setComisarSef(instituteModelDTO.getComisarSef());
		institute.setDataInfiintare(instituteModelDTO.getDataInfiintare());
		institute.setDenumiresc(instituteModelDTO.getDenumiresc());
		institute.setDescriereCaen(instituteModelDTO.getDescriereCaen());
		institute.setDomeniu(instituteModelDTO.getDomeniu());
		institute.setEmail(instituteModelDTO.getEmail());
		institute.setFax(instituteModelDTO.getFax());
		institute.setId(instituteModelDTO.getId());
		institute.setMonedaCapitalSocial(instituteModelDTO.getMonedaCapitalSocial());
		
		instituteDAO.updateInstitute(institute);

	}

}