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

import com.servustech.spring.model.ProtectedAreaResponsability;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class ProtectedAreaResponsabilityDAOImpl implements ProtectedAreaResponsabilityDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ProtectedAreaResponsabilityDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory protectedarearesponsability) {
		sessionFactory = protectedarearesponsability;
	}

	@Override
	public void addProtectedAreaResponsability(final BaseEntity protectedarearesponsability) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(protectedarearesponsability);
		LOGGER.info("Protected Area Responsability saved successfully, Protected Area Responsability Details=" + protectedarearesponsability);
	}

	@Override
	public void updateProtectedAreaResponsability(final BaseEntity protectedarearesponsability) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(protectedarearesponsability);
		LOGGER.info("Protected Area Responsability updated successfully, Protected Area Responsability Details=" + protectedarearesponsability);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProtectedAreaResponsability> listProtectedAreaResponsability(final String protectedarearesponsabilityName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<ProtectedAreaResponsability> objectList = session.createQuery("from " + protectedarearesponsabilityName).list();
		for (final Object protectedarearesponsability : objectList) {
			LOGGER.info("Protected Area Responsability List::" + protectedarearesponsability);
		}
		return objectList;
	}

	@Override
	public List<ProtectedAreaResponsability> listProtectedAreaResponsability() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(ProtectedAreaResponsability.class);
		final List<ProtectedAreaResponsability> results = crit.list();

		return results;
	}

	@Override
	public ProtectedAreaResponsability getProtectedAreaResponsabilityById(final int id, final Class<? extends BaseEntity> protectedarearesponsabilityclass) {
		final Session session = sessionFactory.getCurrentSession();
		final ProtectedAreaResponsability protectedarearesponsability = (ProtectedAreaResponsability) session.load(protectedarearesponsabilityclass, new Integer(id));
		LOGGER.info("Protected Area Responsability loaded successfully, Protected Area Responsability details=" + protectedarearesponsability);
		return protectedarearesponsability;
	}

	@Override
	public void removeProtectedAreaResponsability(final int id, final Class<? extends BaseEntity> protectedarearesponsabilityclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object protectedarearesponsability = getProtectedAreaResponsabilityById(id, protectedarearesponsabilityclass);
		session.delete(protectedarearesponsability);
		LOGGER.info("ProtectedAreaResponsability deleted successfully, ProtectedAreaResponsability details=" + protectedarearesponsability);
	}

}
