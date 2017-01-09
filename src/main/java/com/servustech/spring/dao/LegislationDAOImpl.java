package com.servustech.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servustech.spring.model.Legislation;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class LegislationDAOImpl implements LegislationDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(LegislationDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(final SessionFactory legislation) {
		this.sessionFactory = legislation;
	}

	@Override
	public void addLegislation(final BaseEntity legislation) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.persist(legislation);
		LOGGER.info("Legislation saved successfully, Legislation Details=" + legislation);
	}

	@Override
	public void updateLegislation(final BaseEntity legislation) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.update(legislation);
		LOGGER.info("Legislation updated successfully, Legislation Details=" + legislation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Legislation> listLegislation(final String legislationName) {
		final Session session = this.sessionFactory.getCurrentSession();
		final List<Legislation> objectList = session.createQuery("from " + legislationName).list();
		for (final Object legislation : objectList) {
			LOGGER.info("Legislation List::" + legislation);
		}
		return objectList;
	}

	@Override
	public List<Legislation> listLegislation(final int id) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(Legislation.class);
		// crit.add(Restrictions.eq("eventCategory_Legislations.id", id));
		final List<Legislation> results = crit.list();

		return results;
	}

	@Override
	public Legislation getLegislationById(final int id, final Class<? extends BaseEntity> legislationclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Legislation legislation = (Legislation) session.load(legislationclass, new Integer(id));
		LOGGER.info("Legislation loaded successfully, Legislation details=" + legislation);
		return legislation;
	}

	@Override
	public void removeLegislation(final int id, final Class<? extends BaseEntity> legislationclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Object legislation = getLegislationById(id, legislationclass);
		session.delete(legislation);
		LOGGER.info("Legislation deleted successfully, Legislation details=" + legislation);
	}

}
