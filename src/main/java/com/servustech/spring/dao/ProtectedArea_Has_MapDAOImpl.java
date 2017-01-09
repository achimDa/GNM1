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

import com.servustech.spring.model.ProtectedArea_Has_Map;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class ProtectedArea_Has_MapDAOImpl implements ProtectedArea_Has_MapDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ProtectedArea_Has_MapDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory areahasmap) {
		sessionFactory = areahasmap;
	}

	@Override
	public void addProtectedArea_Has_Map(final BaseEntity areahasmap) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(areahasmap);
		LOGGER.info("ProtectedArea_Has_Map saved successfully, ProtectedArea_Has_Map Details=" + areahasmap);
	}

	@Override
	public void updateProtectedArea_Has_Map(final BaseEntity areahasmap) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(areahasmap);
		LOGGER.info("ProtectedArea_Has_Map updated successfully, ProtectedArea_Has_Map Details=" + areahasmap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProtectedArea_Has_Map> listProtectedArea_Has_Map(final String areahasmapName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<ProtectedArea_Has_Map> objectList = session.createQuery("from " + areahasmapName).list();
		for (final Object areahasmap : objectList) {
			LOGGER.info("ProtectedArea_Has_Map List::" + areahasmap);
		}
		return objectList;
	}

	@Override
	public List<ProtectedArea_Has_Map> listProtectedArea_Has_Map(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(ProtectedArea_Has_Map.class);
		crit.add(Restrictions.eq("protectedarea.id", id));
		final List<ProtectedArea_Has_Map> results = crit.list();

		return results;
	}

	@Override
	public ProtectedArea_Has_Map getProtectedArea_Has_MapById(final int id, final Class<? extends BaseEntity> areahasmapclass) {
		final Session session = sessionFactory.getCurrentSession();
		final ProtectedArea_Has_Map areahasmap = (ProtectedArea_Has_Map) session.load(areahasmapclass, new Integer(id));
		LOGGER.info("ProtectedArea_Has_Map loaded successfully, ProtectedArea_Has_Map details=" + areahasmap);
		return areahasmap;
	}

	@Override
	public void removeProtectedArea_Has_Map(final int id, final Class<? extends BaseEntity> areahasmapclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object areahasmap = getProtectedArea_Has_MapById(id, areahasmapclass);
		session.delete(areahasmap);
		LOGGER.info("ProtectedArea_Has_Map deleted successfully, ProtectedArea_Has_Map details=" + areahasmap);
	}

}
