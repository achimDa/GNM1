package com.servustech.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servustech.spring.model.IssuingUnit;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class IssuingUnitDAOImpl implements IssuingUnitDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(IssuingUnitDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory issunit) {
		sessionFactory = issunit;
	}

	@Override
	public void addIssuingUnit(final BaseEntity issunit) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(issunit);
		LOGGER.info("Issuing Unit saved successfully, Issuing Unit Details=" + issunit);
	}

	@Override
	public void updateIssuingUnit(final BaseEntity issunit) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(issunit);
		LOGGER.info("Issuing Unit updated successfully, Issuing Unit Details=" + issunit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IssuingUnit> listIssuingUnit(final String issunitName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<IssuingUnit> objectList = session.createQuery("from " + issunitName).list();
		for (final Object issunit : objectList) {
			LOGGER.info("Issuing Unit List::" + issunit);
		}
		return objectList;
	}

	@Override
	public List<IssuingUnit> listIssuingUnit() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(IssuingUnit.class);
		final List<IssuingUnit> results = crit.list();
			return results;
	}

	@Override
	public IssuingUnit getIssuingUnitById(final int id, final Class<? extends BaseEntity> issunitclass) {
		final Session session = sessionFactory.getCurrentSession();
		final IssuingUnit issunit = (IssuingUnit) session.load(issunitclass, new Integer(id));
		LOGGER.info("Issuing Unit loaded successfully, Issuing Unit details=" + issunit);
		return issunit;
	}

	@Override
	public void removeIssuingUnit(final int id, final Class<? extends BaseEntity> issunitclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object issunit = getIssuingUnitById(id, issunitclass);
		session.delete(issunit);
		LOGGER.info("Issuing Unit deleted successfully, Issuing Unit details=" + issunit);
	}

}
