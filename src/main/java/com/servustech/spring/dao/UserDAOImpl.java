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

import com.servustech.spring.model.User;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class UserDAOImpl implements UserDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory user) {
		sessionFactory = user;
	}

	@Override
	public void addUser(final BaseEntity user) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(user);
		LOGGER.info("User saved successfully, User Details=" + user);
	}

	@Override
	public void updateUser(final BaseEntity user) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(user);
		LOGGER.info("User updated successfully, User Details=" + user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser(final String userName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<User> objectList = session.createQuery("from " + userName).list();
		for (final Object user : objectList) {
			LOGGER.info("User List::" + user);
		}
		return objectList;
	}

	@Override
	public List<User> listUser() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(User.class);
//		crit.addOrder(Order.asc("id"));
		final List<User> results = crit.list();

		return results;
	}

	@Override
	public User getUserById(final int id, final Class<? extends BaseEntity> userclass) {
		final Session session = sessionFactory.getCurrentSession();
		final User user = (User) session.load(userclass, new Integer(id));
		LOGGER.info("User loaded successfully, User details=" + user);
		return user;
	}

	@Override
	public void removeUser(final int id, final Class<? extends BaseEntity> userclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object user = getUserById(id, userclass);
		session.delete(user);
		LOGGER.info("User deleted successfully, User details=" + user);
	}

	@Override
	public User getUser(String username) {
		final Session session = sessionFactory.getCurrentSession();
		
		final Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("userId", username));
		
		return (User) crit.uniqueResult();
	}

}
