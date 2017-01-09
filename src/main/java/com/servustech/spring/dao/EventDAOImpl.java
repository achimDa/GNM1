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

import com.servustech.spring.model.Event;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class EventDAOImpl implements EventDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(EventDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory eveniment) {
		sessionFactory = eveniment;
	}

	@Override
	public void addEvent(final BaseEntity event) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(event);
		LOGGER.info("Event saved successfully, Event Details=" + event);
	}

	@Override
	public void updateEvent(final BaseEntity event) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(event);
		LOGGER.info("Event updated successfully, Event Details=" + event);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> listEvent(final String eventName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<Event> objectList = session.createQuery("from " + eventName).list();
		for (final Object event : objectList) {
			LOGGER.info("Event List::" + event);
		}
		return objectList;
	}

	@Override
	public List<Event> listEvent(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(Event.class);
		crit.add(Restrictions.eq("eventcategory.id", id));
		final List<Event> results = crit.list();

		return results;
	}

	@Override
	public Event getEventById(final int id, final Class<? extends BaseEntity> eventclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Event event = (Event) session.load(eventclass, new Integer(id));
		LOGGER.info("Event loaded successfully, Event details=" + event);
		return event;
	}

	@Override
	public void removeEvent(final int id, final Class<? extends BaseEntity> eventclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object event = getEventById(id, eventclass);
		session.delete(event);
		LOGGER.info("Event deleted successfully, Event details=" + event);
	}

}
