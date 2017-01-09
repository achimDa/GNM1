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

import com.servustech.spring.model.City;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class CityDAOImpl implements CityDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(CityDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory city) {
		sessionFactory = city;
	}

	@Override
	public void addCity(final BaseEntity city) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(city);
		LOGGER.info("City saved successfully, City Details=" + city);
	}

	@Override
	public void updateCity(final BaseEntity city) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(city);
		LOGGER.info("City updated successfully, City Details=" + city);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> listCity(final String cityName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<City> objectList = session.createQuery("from " + cityName).list();
		for (final Object city : objectList) {
			LOGGER.info("City List::" + city);
		}
		return objectList;
	}

	@Override
	public List<City> listCity(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(City.class);
		crit.add(Restrictions.eq("area.id", id));
		final List<City> results = crit.list();

		return results;
	}

	@Override
	public City getCityById(final int id, final Class<? extends BaseEntity> cityclass) {
		final Session session = sessionFactory.getCurrentSession();
		final City city = (City) session.load(cityclass, new Integer(id));
		LOGGER.info("City loaded successfully, City details=" + city);
		return city;
	}

	@Override
	public void removeCity(final int id, final Class<? extends BaseEntity> cityclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object city = getCityById(id, cityclass);
		session.delete(city);
		LOGGER.info("City deleted successfully, City details=" + city);
	}

}
