package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.CertifiedIssuingUnit;
import com.servustech.spring.model.base.BaseEntity;

public interface CertifiedIssuingUnitDAO {

	void addCertifiedIssuingUnit(BaseEntity crtissunit);

	void updateCertifiedIssuingUnit(BaseEntity crtissunit);

	List<CertifiedIssuingUnit> listCertifiedIssuingUnit(String crtissunitName);

	List<CertifiedIssuingUnit> listCertifiedIssuingUnit();

	CertifiedIssuingUnit getCertifiedIssuingUnitById(int id, Class<? extends BaseEntity> crtissunitclass);

	void removeCertifiedIssuingUnit(int id, final Class<? extends BaseEntity> crtissunitclass);
}