package com.servustech.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servustech.spring.model.CertifiedIssuingUnit;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class CertifiedIssuingUnitDAOImpl implements CertifiedIssuingUnitDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(CountryDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory crtissunit) {
		sessionFactory = crtissunit;
	}

	@Override
	public void addCertifiedIssuingUnit(final BaseEntity crtissunit) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(crtissunit);
		LOGGER.info("Certified Issuing Unit saved successfully, Certified Issuing Unit Details=" + crtissunit);
	}

	@Override
	public void updateCertifiedIssuingUnit(final BaseEntity crtissunit) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(crtissunit);
		LOGGER.info("Certified Issuing Unit updated successfully, Certified Issuing Unit Details=" + crtissunit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CertifiedIssuingUnit> listCertifiedIssuingUnit(final String crtissunitName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<CertifiedIssuingUnit> objectList = session.createQuery("from " + crtissunitName).list();
		for (final Object crtissunit : objectList) {
			LOGGER.info("Certified Issuing Unit List::" + crtissunit);
		}
		return objectList;
	}

	@Override
	public List<CertifiedIssuingUnit> listCertifiedIssuingUnit() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(CertifiedIssuingUnit.class);
//		crit.addOrder(Order.asc("id"));
		final List<CertifiedIssuingUnit> results = crit.list();

		return results;
	}

	@Override
	public CertifiedIssuingUnit getCertifiedIssuingUnitById(final int id, final Class<? extends BaseEntity> crtissunitclass) {
		final Session session = sessionFactory.getCurrentSession();
		final CertifiedIssuingUnit crtissunit = (CertifiedIssuingUnit) session.load(crtissunitclass, new Integer(id));
		LOGGER.info("Certified Issuing Unit loaded successfully, Certified Issuing Unit details=" + crtissunit);
		return crtissunit;
	}

	@Override
	public void removeCertifiedIssuingUnit(final int id, final Class<? extends BaseEntity> crtissunitclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object crtissunit = getCertifiedIssuingUnitById(id, crtissunitclass);
		session.delete(crtissunit);
		LOGGER.info("Certified Issuing Unit deleted successfully, Certified Issuing Unit details=" + crtissunit);
	}

}
