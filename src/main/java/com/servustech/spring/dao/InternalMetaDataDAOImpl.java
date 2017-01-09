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

import com.servustech.spring.model.InternalMetaData;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class InternalMetaDataDAOImpl implements InternalMetaDataDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(InternalMetaDataDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory internaldata) {
		sessionFactory = internaldata;
	}

	@Override
	public void addInternalMetaData(final BaseEntity internaldata) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(internaldata);
		LOGGER.info("InternalMetaData saved successfully, InternalMetaData Details=" + internaldata);
	}

	@Override
	public void updateInternalMetaData(final BaseEntity internaldata) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(internaldata);
		LOGGER.info("InternalMetaData updated successfully, InternalMetaData Details=" + internaldata);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InternalMetaData> listInternalMetaData(final String internaldataName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<InternalMetaData> objectList = session.createQuery("from " + internaldataName).list();
		for (final Object internaldata : objectList) {
			LOGGER.info("InternalMetaData List::" + internaldata);
		}
		return objectList;
	}

	@Override
	public List<InternalMetaData> listInternalMetaData(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(InternalMetaData.class);
		crit.add(Restrictions.eq("metadatamap.id", id));
		final List<InternalMetaData> results = crit.list();

		return results;
	}

	@Override
	public InternalMetaData getInternalMetaDataById(final int id, final Class<? extends BaseEntity> internaldataclass) {
		final Session session = sessionFactory.getCurrentSession();
		final InternalMetaData internaldata = (InternalMetaData) session.load(internaldataclass, new Integer(id));
		LOGGER.info("InternalMetaData loaded successfully, InternalMetaData details=" + internaldata);
		return internaldata;
	}

	@Override
	public void removeInternalMetaData(final int id, final Class<? extends BaseEntity> internaldataclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object internaldata = getInternalMetaDataById(id, internaldataclass);
		session.delete(internaldata);
		LOGGER.info("InternalMetaData deleted successfully, InternalMetaData details=" + internaldata);
	}

}
