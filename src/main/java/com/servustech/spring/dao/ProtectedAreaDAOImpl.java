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

import com.servustech.spring.model.ProtectedArea;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class ProtectedAreaDAOImpl implements ProtectedAreaDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ProtectedAreaDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory protectedarea) {
		sessionFactory = protectedarea;
	}

	@Override
	public void addProtectedArea(final BaseEntity protectedarea) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(protectedarea);
		LOGGER.info("Protected Area saved successfully, Protected Area Details=" + protectedarea);
	}

	@Override
	public void updateProtectedArea(final BaseEntity protectedarea) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(protectedarea);
		LOGGER.info("Protected Area updated successfully, Protected Area Details=" + protectedarea);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProtectedArea> listProtectedArea(final String protectedareaName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<ProtectedArea> objectList = session.createQuery("from " + protectedareaName).list();
		for (final Object protectedarea : objectList) {
			LOGGER.info("Protected Area List::" + protectedarea);
		}
		return objectList;
	}

	@Override
	public List<ProtectedArea> listProtectedArea(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(ProtectedArea.class);
		crit.add(Restrictions.eq("protectedareatype.id", id));
		final List<ProtectedArea> results = crit.list();

		return results;
	}

	@Override
	public ProtectedArea getProtectedAreaById(final int id, final Class<? extends BaseEntity> protectedareaclass) {
		final Session session = sessionFactory.getCurrentSession();
		final ProtectedArea protectedarea = (ProtectedArea) session.load(protectedareaclass, new Integer(id));
		LOGGER.info("Protected Area loaded successfully, Protected Area details=" + protectedarea);
		return protectedarea;
	}

	@Override
	public void removeProtectedArea(final int id, final Class<? extends BaseEntity> protectedareaclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object protectedarea = getProtectedAreaById(id, protectedareaclass);
		session.delete(protectedarea);
		LOGGER.info("Protected Area deleted successfully, Protected Area details=" + protectedarea);
	}

}
