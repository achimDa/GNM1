package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.CertifiedIssuingUnit;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.CertifiedIssuingUnitModelDTO;

public interface CertifiedIssuingUnitService {

	void addCertifiedIssuingUnit(BaseEntity crtissunit);

	void save(CertifiedIssuingUnitModelDTO crtissunitModelDTO);

	void updateCertifiedIssuingUnit(BaseEntity crtissunit);

	List<CertifiedIssuingUnit> listCertifiedIssuingUnit(String crtissunitName);

	List<CertifiedIssuingUnitModelDTO> listCertifiedIssuingUnit();

	Object getCertifiedIssuingUnitById(int id, Class<? extends BaseEntity> clazz);

	void removeCertifiedIssuingUnit(int id, Class<? extends BaseEntity> clazz);

	void editCertifiedIssuingUnit(CertifiedIssuingUnitModelDTO crtissunitModelDTO);
}
