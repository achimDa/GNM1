package com.servustech.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servustech.spring.model.AnimalKingdom;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class AnimalKingdomDAOImpl implements AnimalKingdomDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnimalKingdomDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(final SessionFactory animalkingdom) {
		this.sessionFactory = animalkingdom;
	}

	@Override
	public void addAnimalKingdom(final BaseEntity animalkingdom) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.persist(animalkingdom);
		LOGGER.info("Animal Kingdom saved successfully, Animal Kingdom Details=" + animalkingdom);
	}

	@Override
	public void updateAnimalKingdom(final BaseEntity animalkingdom) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.update(animalkingdom);
		LOGGER.info("Animal Kingdom updated successfully, Animal Kingdom Details=" + animalkingdom);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnimalKingdom> listAnimalKingdom(final String animalkingdomName) {
		final Session session = this.sessionFactory.getCurrentSession();
		final List<AnimalKingdom> objectList = session.createQuery("from " + animalkingdomName).list();
		for (final Object animalkingdom : objectList) {
			LOGGER.info("Animal Kingdom List::" + animalkingdom);
		}
		return objectList;
	}

	@Override
	public List<AnimalKingdom> listAnimalKingdom() {
		final Session session = this.sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(AnimalKingdom.class);
		// crit.add(Restrictions.eq("eventcategory.id", id));
		final List<AnimalKingdom> results = crit.list();

		return results;
	}

	@Override
	public AnimalKingdom getAnimalKingdomById(final int id, final Class<? extends BaseEntity> animalkingdomclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final AnimalKingdom animalkingdom = (AnimalKingdom) session.load(animalkingdomclass, new Integer(id));
		LOGGER.info("Animal Kingdom loaded successfully, Animal Kingdom details=" + animalkingdom);
		return animalkingdom;
	}

	@Override
	public void removeAnimalKingdom(final int id, final Class<? extends BaseEntity> animalkingdomclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Object animalkingdom = getAnimalKingdomById(id, animalkingdomclass);
		session.delete(animalkingdom);
		LOGGER.info("Animal Kingdom deleted successfully, Animal Kingdom details=" + animalkingdom);
	}

}
