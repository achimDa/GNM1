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

import com.servustech.spring.model.Country;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class CountryDAOImpl implements CountryDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(CountryDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory country) {
		sessionFactory = country;
	}

	@Override
	public void addCountry(final BaseEntity country) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(country);
		LOGGER.info("Country saved successfully, Country Details=" + country);
	}

	@Override
	public void updateCountry(final BaseEntity country) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(country);
		LOGGER.info("Country updated successfully, Country Details=" + country);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> listCountry(final String countryName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<Country> objectList = session.createQuery("from " + countryName).list();
		for (final Object country : objectList) {
			LOGGER.info("Country List::" + country);
		}
		return objectList;
	}

	@Override
	public List<Country> listCountry() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(Country.class);
//		crit.addOrder(Order.asc("id"));
		final List<Country> results = crit.list();

		return results;
	}

	@Override
	public Country getCountryById(final int id, final Class<? extends BaseEntity> countryclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Country country = (Country) session.load(countryclass, new Integer(id));
		LOGGER.info("Country loaded successfully, Country details=" + country);
		return country;
	}

	@Override
	public void removeCountry(final int id, final Class<? extends BaseEntity> countryclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object country = getCountryById(id, countryclass);
		session.delete(country);
		LOGGER.info("Country deleted successfully, Country details=" + country);
	}

}
