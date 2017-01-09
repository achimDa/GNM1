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

import com.servustech.spring.model.Area;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class AreaDAOImpl implements AreaDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(AreaDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory area) {
		sessionFactory = area;
	}

	@Override
	public void addArea(final BaseEntity area) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(area);
		LOGGER.info("Area saved successfully, Area Details=" + area);
	}

	@Override
	public void updateArea(final BaseEntity area) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(area);
		LOGGER.info("Area updated successfully, Area Details=" + area);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> listArea(final String areaName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<Area> objectList = session.createQuery("from " + areaName).list();
		for (final Object area : objectList) {
			LOGGER.info("Area List::" + area);
		}
		return objectList;
	}

	@Override
	public List<Area> listArea(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(Area.class);
		crit.add(Restrictions.eq("country.id", id));
		final List<Area> results = crit.list();

		return results;
	}

	@Override
	public Area getAreaById(final int id, final Class<? extends BaseEntity> areaclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Area area = (Area) session.load(areaclass, new Integer(id));
		LOGGER.info("Area loaded successfully, Area details=" + area);
		return area;
	}

	@Override
	public void removeArea(final int id, final Class<? extends BaseEntity> areaclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object area = getAreaById(id, areaclass);
		session.delete(area);
		LOGGER.info("Area deleted successfully, Area details=" + area);
	}

}
