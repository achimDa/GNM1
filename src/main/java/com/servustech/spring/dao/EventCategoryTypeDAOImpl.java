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

import com.servustech.spring.model.EventCategoryType;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class EventCategoryTypeDAOImpl implements EventCategoryTypeDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(EventCategoryTypeDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory eventtype) {
		sessionFactory = eventtype;
	}

	@Override
	public void addEventCategoryType(final BaseEntity eventtype) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(eventtype);
		LOGGER.info("Event Category Type saved successfully, Event Category Type Details=" + eventtype);
	}

	@Override
	public void updateEventCategoryType(final BaseEntity eventtype) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(eventtype);
		LOGGER.info("Event Category Type updated successfully, Event Category Type Details=" + eventtype);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventCategoryType> listEventCategoryType(final String eventtypeName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<EventCategoryType> objectList = session.createQuery("from " + eventtypeName).list();
		for (final Object eventtype : objectList) {
			LOGGER.info("Event Category Type List::" + eventtype);
		}
		return objectList;
	}

	@Override
	public List<EventCategoryType> listEventCategoryType() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(EventCategoryType.class);
	//	crit.add(Restrictions.eq("eventcategory.id", id));
		final List<EventCategoryType> results = crit.list();

		return results;
	}

	@Override
	public EventCategoryType getEventCategoryTypeById(final int id, final Class<? extends BaseEntity> eventtypeclass) {
		final Session session = sessionFactory.getCurrentSession();
		final EventCategoryType eventtype = (EventCategoryType) session.load(eventtypeclass, new Integer(id));
		LOGGER.info("Event Category Type loaded successfully, Event Category Type details=" + eventtype);
		return eventtype;
	}

	@Override
	public void removeEventCategoryType(final int id, final Class<? extends BaseEntity> eventtypeclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object eventtype = getEventCategoryTypeById(id, eventtypeclass);
		session.delete(eventtype);
		LOGGER.info("Event Category Type deleted successfully, Event Category Type details=" + eventtype);
	}

}
