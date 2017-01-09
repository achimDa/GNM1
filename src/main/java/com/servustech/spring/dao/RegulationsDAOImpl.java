package com.servustech.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servustech.spring.model.Regulations;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class RegulationsDAOImpl implements RegulationsDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegulationsDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(final SessionFactory regulations) {
		this.sessionFactory = regulations;
	}

	@Override
	public void addRegulations(final BaseEntity regulations) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.persist(regulations);
		LOGGER.info("Regulations saved successfully, Regulations Details=" + regulations);
	}

	@Override
	public void updateRegulations(final BaseEntity regulations) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.update(regulations);
		LOGGER.info("Regulations updated successfully, Regulations Details=" + regulations);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Regulations> listRegulations(final String regulationsName) {
		final Session session = this.sessionFactory.getCurrentSession();
		final List<Regulations> objectList = session.createQuery("from " + regulationsName).list();
		for (final Object regulations : objectList) {
			LOGGER.info("Regulations List::" + regulations);
		}
		return objectList;
	}

	@Override
	public List<Regulations> listRegulations(final int id) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(Regulations.class);
		// crit.add(Restrictions.eq("eventCategory_Legislations.id", id));
		final List<Regulations> results = crit.list();

		return results;
	}

	@Override
	public Regulations getRegulationsById(final int id, final Class<? extends BaseEntity> regulationsclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Regulations regulations = (Regulations) session.load(regulationsclass, new Integer(id));
		LOGGER.info("Regulations loaded successfully, Regulations details=" + regulations);
		return regulations;
	}

	@Override
	public void removeRegulations(final int id, final Class<? extends BaseEntity> regulationsclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Object regulations = getRegulationsById(id, regulationsclass);
		session.delete(regulations);
		LOGGER.info("Regulations deleted successfully, Regulations details=" + regulations);
	}

}
