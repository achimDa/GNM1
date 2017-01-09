package com.servustech.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servustech.spring.model.Agency;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class AgencyDAOImpl implements AgencyDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(AgencyDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory agency) {
		sessionFactory = agency;
	}

	@Override
	public void addAgency(final BaseEntity agency) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(agency);
		LOGGER.info("Agency saved successfully, Agency Details=" + agency);
	}

	@Override
	public void updateAgency(final BaseEntity agency) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(agency);
		LOGGER.info("Agency updated successfully, Agency Details=" + agency);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agency> listAgency(final String agencyName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<Agency> objectList = session.createQuery("from " + agencyName).list();
		for (final Object agency : objectList) {
			LOGGER.info("Agency List::" + agency);
		}
		return objectList;
	}

	@Override
	public List<Agency> listAgency(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(Agency.class);
		crit.add(Restrictions.eq("societate.id", id));
		final List<Agency> results = crit.list();

		return results;
	}

	@Override
	public Agency getAgencyById(final int id, final Class<? extends BaseEntity> agencyclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Agency agency = (Agency) session.load(agencyclass, new Integer(id));
		LOGGER.info("Agency loaded successfully, Agency details=" + agency);
		return agency;
	}

	@Override
	public void removeAgency(final int id, final Class<? extends BaseEntity> agencyclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object agency = getAgencyById(id, agencyclass);
		session.delete(agency);
		LOGGER.info("Agency deleted successfully, Agency details=" + agency);
	}

}
