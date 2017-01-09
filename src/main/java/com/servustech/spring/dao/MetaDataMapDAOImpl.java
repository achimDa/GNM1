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

import com.servustech.spring.model.MetaDataMap;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class MetaDataMapDAOImpl implements MetaDataMapDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(MetaDataMapDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory metadatamap) {
		sessionFactory = metadatamap;
	}

	@Override
	public void addMetaDataMap(final BaseEntity metadatamap) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(metadatamap);
		LOGGER.info("MetaDataMap saved successfully, MetaDataMap Details=" + metadatamap);
	}

	@Override
	public void updateMetaDataMap(final BaseEntity metadatamap) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(metadatamap);
		LOGGER.info("MetaDataMap updated successfully, MetaDataMap Details=" + metadatamap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaDataMap> listMetaDataMap(final String metadatamapName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<MetaDataMap> objectList = session.createQuery("from " + metadatamapName).list();
		for (final Object metadatamap : objectList) {
			LOGGER.info("MetaDataMap List::" + metadatamap);
		}
		return objectList;
	}

	@Override
	public List<MetaDataMap> listMetaDataMap(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(MetaDataMap.class);
		crit.add(Restrictions.eq("map.id", id));
		final List<MetaDataMap> results = crit.list();

		return results;
	}

	@Override
	public MetaDataMap getMetaDataMapById(final int id, final Class<? extends BaseEntity> metadatamapclass) {
		final Session session = sessionFactory.getCurrentSession();
		final MetaDataMap metadatamap = (MetaDataMap) session.load(metadatamapclass, new Integer(id));
		LOGGER.info("MetaDataMap loaded successfully, MetaDataMap details=" + metadatamap);
		return metadatamap;
	}

	@Override
	public void removeMetaDataMap(final int id, final Class<? extends BaseEntity> metadatamapclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object metadatamap = getMetaDataMapById(id, metadatamapclass);
		session.delete(metadatamap);
		LOGGER.info("MetaDataMap deleted successfully, MetaDataMap details=" + metadatamap);
	}

}
