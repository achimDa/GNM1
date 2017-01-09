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

import com.servustech.spring.model.Map;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class MapDAOImpl implements MapDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(MapDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory map) {
		sessionFactory = map;
	}

	@Override
	public void addMap(final BaseEntity map) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(map);
		LOGGER.info("Map saved successfully, Map Details=" + map);
	}

	@Override
	public void updateMap(final BaseEntity map) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(map);
		LOGGER.info("Map updated successfully, Map Details=" + map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> listMap(final String mapName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<Map> objectList = session.createQuery("from " + mapName).list();
		for (final Object map : objectList) {
			LOGGER.info("Map List::" + map);
		}
		return objectList;
	}

	@Override
	public List<Map> listMap(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(Map.class);		
		final List<Map> results = crit.list();

		return results;
	}

	@Override
	public Map getMapById(final int id, final Class<? extends BaseEntity> mapclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Map map = (Map) session.load(mapclass, new Integer(id));
		LOGGER.info("Map loaded successfully, Map details=" + map);
		return map;
	}

	@Override
	public void removeMap(final int id, final Class<? extends BaseEntity> mapclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object map = getMapById(id, mapclass);
		session.delete(map);
		LOGGER.info("Map deleted successfully, Map details=" + map);
	}

}
