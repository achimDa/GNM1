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

import com.servustech.spring.model.Adress;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class AdressDAOImpl implements AdressDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(AdressDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory adress) {
		sessionFactory = adress;
	}

	@Override
	public void addAdress(final BaseEntity adress) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(adress);
		LOGGER.info("Adress saved successfully, Adress Details=" + adress);
	}

	@Override
	public void updateAdress(final BaseEntity adress) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(adress);
		LOGGER.info("Adress updated successfully, Adress Details=" + adress);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Adress> listAdress(final String adressName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<Adress> adressList = session.createQuery("from " + adressName).list();
		for (final Object adress : adressList) {
			LOGGER.info("Adress List::" + adress);
		}
		return adressList;
	}

	@Override
	public List<Adress> listAdress(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(Adress.class);
		crit.add(Restrictions.eq("city.id", id));
		final List<Adress> results = crit.list();

		return results;
	}

	@Override
	public Adress getAdressById(final int id, final Class<? extends BaseEntity> adressclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Adress adress = (Adress) session.load(adressclass, new Integer(id));
		LOGGER.info("Adress loaded successfully, Adress details=" + adress);
		return adress;
	}

	@Override
	public void removeAdress(final int id, final Class<? extends BaseEntity> adressclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object adress = getAdressById(id, adressclass);
		session.delete(adress);
		LOGGER.info("Adress deleted successfully, Adress details=" + adress);
	}

}
