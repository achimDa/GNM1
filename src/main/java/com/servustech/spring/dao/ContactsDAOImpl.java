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

import com.servustech.spring.model.Contacts;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class ContactsDAOImpl implements ContactsDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ContactsDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory contacts) {
		sessionFactory = contacts;
	}

	@Override
	public void addContacts(final BaseEntity contacts) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(contacts);
		LOGGER.info("Contact saved successfully, Contact Details=" + contacts);
	}

	@Override
	public void updateContacts(final BaseEntity contacts) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(contacts);
		LOGGER.info("Contact updated successfully, Contact Details=" + contacts);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contacts> listContacts(final String contactsName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<Contacts> objectList = session.createQuery("from " + contactsName).list();
		for (final Object contacts : objectList) {
			LOGGER.info("Contacts List::" + contacts);
		}
		return objectList;
	}

	@Override
	public List<Contacts> listContacts(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(Contacts.class);
		crit.add(Restrictions.eq("agency.id", id));
		final List<Contacts> results = crit.list();

		return results;
	}

	@Override
	public Contacts getContactsById(final int id, final Class<? extends BaseEntity> contactsclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Contacts contacts = (Contacts) session.load(contactsclass, new Integer(id));
		LOGGER.info("Contacts loaded successfully, Contacts details=" + contacts);
		return contacts;
	}

	@Override
	public void removeContacts(final int id, final Class<? extends BaseEntity> contactsclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object contacts = getContactsById(id, contactsclass);
		session.delete(contacts);
		LOGGER.info("Contact deleted successfully, Contact details=" + contacts);
	}

}
