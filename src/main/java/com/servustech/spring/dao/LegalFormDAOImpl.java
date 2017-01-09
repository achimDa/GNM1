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

import com.servustech.spring.model.LegalForm;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class LegalFormDAOImpl implements LegalFormDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(LegalFormDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory legalform) {
		sessionFactory = legalform;
	}

	@Override
	public void addLegalForm(final BaseEntity legalform) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(legalform);
		LOGGER.info("LegalForm saved successfully, LegalForm Details=" + legalform);
	}

	@Override
	public void updateLegalForm(final BaseEntity legalform) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(legalform);
		LOGGER.info("LegalForm updated successfully, LegalForm Details=" + legalform);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LegalForm> listLegalForm(final String legalformName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<LegalForm> objectList = session.createQuery("from " + legalformName).list();
		for (final Object legalform : objectList) {
			LOGGER.info("LegalForm List::" + legalform);
		}
		return objectList;
	}

	@Override
	public List<LegalForm> listLegalForm() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(LegalForm.class);
//		crit.addOrder(Order.asc("id"));
		final List<LegalForm> results = crit.list();

		return results;
	}

	@Override
	public LegalForm getLegalFormById(final int id, final Class<? extends BaseEntity> legalformclass) {
		final Session session = sessionFactory.getCurrentSession();
		final LegalForm legalform = (LegalForm) session.load(legalformclass, new Integer(id));
		LOGGER.info("LegalForm loaded successfully, LegalForm details=" + legalform);
		return legalform;
	}

	@Override
	public void removeLegalForm(final int id, final Class<? extends BaseEntity> legalformclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object legalform = getLegalFormById(id, legalformclass);
		session.delete(legalform);
		LOGGER.info("LegalForm deleted successfully, LegalForm details=" + legalform);
	}

}
