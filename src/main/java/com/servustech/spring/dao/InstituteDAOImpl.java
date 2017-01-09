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

import com.servustech.spring.model.Institute;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class InstituteDAOImpl implements InstituteDAO {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(InstituteDAOImpl.class);

	@Autowired
	private SessionFactory		sessionFactory;

	public void setSessionFactory(final SessionFactory institute) {
		sessionFactory = institute;
	}

	@Override
	public void addInstitute(final BaseEntity institute) {
		final Session session = sessionFactory.getCurrentSession();
		session.persist(institute);
		LOGGER.info("Institute saved successfully, Institute Details=" + institute);
	}

	@Override
	public void updateInstitute(final BaseEntity institute) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(institute);
		LOGGER.info("Institute updated successfully, Institute Details=" + institute);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Institute> listInstitute(final String instituteName) {
		final Session session = sessionFactory.getCurrentSession();
		final List<Institute> objectList = session.createQuery("from " + instituteName).list();
		for (final Object institute : objectList) {
			LOGGER.info("Institute List::" + institute);
		}
		return objectList;
	}

	@Override
	public List<Institute> listInstitute(final int id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(Institute.class);
		crit.add(Restrictions.eq("adress.id", id));
		final List<Institute> results = crit.list();

		return results;
	}

	@Override
	public Institute getInstituteById(final int id, final Class<? extends BaseEntity> instituteclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Institute institute = (Institute) session.load(instituteclass, new Integer(id));
		LOGGER.info("Institute loaded successfully, Institute details=" + institute);
		return institute;
	}

	@Override
	public void removeInstitute(final int id, final Class<? extends BaseEntity> instituteclass) {
		final Session session = sessionFactory.getCurrentSession();
		final Object institute = getInstituteById(id, instituteclass);
		session.delete(institute);
		LOGGER.info("Institute deleted successfully, Institute details=" + institute);
	}

}
