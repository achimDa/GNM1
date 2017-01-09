package com.servustech.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servustech.spring.model.EcologicalCorridor;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class EcologicalCorridorDAOImpl implements EcologicalCorridorDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(EcologicalCorridorDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(final SessionFactory ecologicalcorridor) {
		this.sessionFactory = ecologicalcorridor;
	}

	@Override
	public void addEcologicalCorridor(final BaseEntity ecologicalcorridor) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.persist(ecologicalcorridor);
		LOGGER.info("EcologicalCorridor saved successfully, EcologicalCorridor Details=" + ecologicalcorridor);
	}

	@Override
	public void updateEcologicalCorridor(final BaseEntity ecologicalcorridor) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.update(ecologicalcorridor);
		LOGGER.info("EcologicalCorridor updated successfully, EcologicalCorridor Details=" + ecologicalcorridor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcologicalCorridor> listEcologicalCorridor(final String ecologicalcorridorName) {
		final Session session = this.sessionFactory.getCurrentSession();
		final List<EcologicalCorridor> objectList = session.createQuery("from " + ecologicalcorridorName).list();
		for (final Object ecologicalcorridor : objectList) {
			LOGGER.info("EcologicalCorridor List::" + ecologicalcorridor);
		}
		return objectList;
	}

	@Override
	public List<EcologicalCorridor> listEcologicalCorridor() {
		final Session session = this.sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(EcologicalCorridor.class);
		// crit.add(Restrictions.eq("eventcategorytype.id", id));
		final List<EcologicalCorridor> results = crit.list();

		return results;
	}

	@Override
	public EcologicalCorridor getEcologicalCorridorById(final int id,
			final Class<? extends BaseEntity> ecologicalcorridorclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final EcologicalCorridor ecologicalcorridor = (EcologicalCorridor) session.load(ecologicalcorridorclass,
				new Integer(id));
		LOGGER.info("EcologicalCorridor loaded successfully, EcologicalCorridor details=" + ecologicalcorridor);
		return ecologicalcorridor;
	}

	@Override
	public void removeEcologicalCorridor(final int id, final Class<? extends BaseEntity> ecologicalcorridorclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Object ecologicalcorridor = getEcologicalCorridorById(id, ecologicalcorridorclass);
		session.delete(ecologicalcorridor);
		LOGGER.info("EcologicalCorridor deleted successfully, EcologicalCorridor details=" + ecologicalcorridor);
	}

}
