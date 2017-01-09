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

import com.servustech.spring.model.ProtectedAreaType;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class ProtectedAreaTypeDAOImpl implements ProtectedAreaTypeDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(CountryDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory protectedareatype) {
		sessionFactory = protectedareatype;
	}

	@Override
	public void addProtectedAreaType(final BaseEntity protectedareatype) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(protectedareatype);
		LOGGER.info("Protected Area Type saved successfully, Protected Area Type Details=" + protectedareatype);
	}

	@Override
	public void updateProtectedAreaType(final BaseEntity protectedareatype) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(protectedareatype);
		LOGGER.info("Protected Area Type updated successfully, Protected Area Type Details=" + protectedareatype);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProtectedAreaType> listProtectedAreaType(final String protectedareatypeName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<ProtectedAreaType> objectList = session.createQuery("from " + protectedareatypeName).list();
		for (final Object protectedareatype : objectList) {
			LOGGER.info("Protected Area Type List::" + protectedareatype);
		}
		return objectList;
	}

	@Override
	public List<ProtectedAreaType> listProtectedAreaType() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(ProtectedAreaType.class);
//		crit.addOrder(Order.asc("id"));
		final List<ProtectedAreaType> results = crit.list();

		return results;
	}

	@Override
	public ProtectedAreaType getProtectedAreaTypeById(final int id, final Class<? extends BaseEntity> protectedareatypeclass) {
		final Session session = sessionFactory.getCurrentSession();
		final ProtectedAreaType protectedareatype = (ProtectedAreaType) session.load(protectedareatypeclass, new Integer(id));
		LOGGER.info("Protected Area Type loaded successfully, Protected Area Type details=" + protectedareatype);
		return protectedareatype;
	}

	@Override
	public void removeProtectedAreaType(final int id, final Class<? extends BaseEntity> protectedareatypeclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object protectedareatype = getProtectedAreaTypeById(id, protectedareatypeclass);
		session.delete(protectedareatype);
		LOGGER.info("Protected Area Type deleted successfully, Protected Area Type details=" + protectedareatype);
	}

}
