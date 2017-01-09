package com.servustech.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servustech.spring.model.MetaDataType;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class MetaDataTypeDAOImpl implements MetaDataTypeDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(MetaDataTypeDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory metadatatype) {
		sessionFactory = metadatatype;
	}

	@Override
	public void addMetaDataType(final BaseEntity metadatatype) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(metadatatype);
		LOGGER.info("Meta Data Type saved successfully, Meta Data Type Details=" + metadatatype);
	}

	@Override
	public void updateMetaDataType(final BaseEntity metadatatype) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(metadatatype);
		LOGGER.info("Meta Data Type updated successfully, Meta Data Type Details=" + metadatatype);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaDataType> listMetaDataType(final String metadatatypeName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<MetaDataType> objectList = session.createQuery("from " + metadatatypeName).list();
		for (final Object metadatatype : objectList) {
			LOGGER.info("Meta Data Type List::" + metadatatype);
		}
		return objectList;
	}

	@Override
	public List<MetaDataType> listMetaDataType() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(MetaDataType.class);
//		crit.addOrder(Order.asc("id"));
		final List<MetaDataType> results = crit.list();

		return results;
	}

	@Override
	public MetaDataType getMetaDataTypeById(final int id, final Class<? extends BaseEntity> metadatatypeclass) {
		final Session session = sessionFactory.getCurrentSession();
		final MetaDataType metadatatype = (MetaDataType) session.load(metadatatypeclass, new Integer(id));
		LOGGER.info("Meta Data Type loaded successfully, Meta Data Type details=" + metadatatype);
		return metadatatype;
	}

	@Override
	public void removeMetaDataType(final int id, final Class<? extends BaseEntity> metadatatypeclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object metadatatype = getMetaDataTypeById(id, metadatatypeclass);
		session.delete(metadatatype);
		LOGGER.info("Meta Data Type deleted successfully, Meta Data Type details=" + metadatatype);
	}

}
