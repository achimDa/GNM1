package com.servustech.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class EventCategoryDAOImpl implements EventCategoryDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventCategoryDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(final SessionFactory event) {
		this.sessionFactory = event;
	}

	@Override
	public void addEventCategory(final BaseEntity event) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.persist(event);
		LOGGER.info("EventCategory saved successfully, EventCategory Details=" + event);
	}

	@Override
	public void updateEventCategory(final BaseEntity event) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.update(event);
		LOGGER.info("EventCategory updated successfully, EventCategory Details=" + event);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventCategory> listEventCategory(final String eventName) {
		final Session session = this.sessionFactory.getCurrentSession();
		final List<EventCategory> objectList = session.createQuery("from " + eventName).list();
		for (final Object event : objectList) {
			LOGGER.info("Map List::" + event);
		}
		return objectList;
	}

	@Override
	public List<EventCategory> listEventCategory() {
		final Session session = this.sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(EventCategory.class);
		// crit.add(Restrictions.eq("eventcategorytype.id", id));
		final List<EventCategory> results = crit.list();

		return results;
	}

	@Override
	public EventCategory getEventCategoryById(final int id, final Class<? extends BaseEntity> eventclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final EventCategory event = (EventCategory) session.load(eventclass, new Integer(id));
		LOGGER.info("EventCategory loaded successfully, EventCategory details=" + event);
		return event;
	}

	@Override
	public void removeEventCategory(final int id, final Class<? extends BaseEntity> eventclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Object event = getEventCategoryById(id, eventclass);
		session.delete(event);
		LOGGER.info("EventCategory deleted successfully, EventCategory details=" + event);
	}

}
