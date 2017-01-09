package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.CertifiedIssuingUnitDAO;
import com.servustech.spring.model.CertifiedIssuingUnit;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.CertifiedIssuingUnitModelConverter;
import com.servustech.spring.model.dto.CertifiedIssuingUnitModelDTO;

@Service
@Transactional

public class CertifiedIssuingUnitServiceImpl implements CertifiedIssuingUnitService {
	@Autowired
	private CertifiedIssuingUnitDAO crtissunitDAO;

	@Override
	public void addCertifiedIssuingUnit(final BaseEntity crtissunit) {
		crtissunitDAO.addCertifiedIssuingUnit(crtissunit);
	}

	@Override
	public void updateCertifiedIssuingUnit(final BaseEntity crtissunit) {
		crtissunitDAO.updateCertifiedIssuingUnit(crtissunit);
	}

	@Override
	public void save(final CertifiedIssuingUnitModelDTO crtissunitModelDTO) {
		final CertifiedIssuingUnit crtissunit = new CertifiedIssuingUnit();
		CertifiedIssuingUnitModelConverter.toEntity(crtissunitModelDTO, crtissunit);
		if (crtissunit.getId() == 0) {
			addCertifiedIssuingUnit(crtissunit);
		} else {
			updateCertifiedIssuingUnit(crtissunit);
		}
	}

	@Override
	public List<CertifiedIssuingUnit> listCertifiedIssuingUnit(final String crtissunitName) {
		return crtissunitDAO.listCertifiedIssuingUnit(crtissunitName);
	}

	@Override
	public List<CertifiedIssuingUnitModelDTO> listCertifiedIssuingUnit() {
		return CertifiedIssuingUnitModelConverter.toDTOList(crtissunitDAO.listCertifiedIssuingUnit());
	}

	@Override
	public CertifiedIssuingUnit getCertifiedIssuingUnitById(final int id, final Class<? extends BaseEntity> crtissunitclass) {
		return crtissunitDAO.getCertifiedIssuingUnitById(id, crtissunitclass);
	}

	@Override
	public void removeCertifiedIssuingUnit(final int id, final Class<? extends BaseEntity> crtissunitclass) {
		crtissunitDAO.removeCertifiedIssuingUnit(id, crtissunitclass);
	}

	@Override
	public void editCertifiedIssuingUnit(final CertifiedIssuingUnitModelDTO crtissunitModelDTO) {
		final CertifiedIssuingUnit crtissunit = getCertifiedIssuingUnitById(crtissunitModelDTO.getId(), CertifiedIssuingUnit.class);

		crtissunit.setDenumire(crtissunitModelDTO.getDenumire());
		crtissunit.setId(crtissunitModelDTO.getId());

		crtissunitDAO.updateCertifiedIssuingUnit(crtissunit);

	}

}