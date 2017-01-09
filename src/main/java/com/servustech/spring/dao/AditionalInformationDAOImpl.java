package com.servustech.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servustech.spring.model.AditionalInformation;
import com.servustech.spring.model.base.BaseEntity;

@Repository
public class AditionalInformationDAOImpl implements AditionalInformationDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(AditionalInformationDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(final SessionFactory aditionalinformation) {
		this.sessionFactory = aditionalinformation;
	}

	@Override
	public void addAditionalInformation(final BaseEntity aditionalinformation) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.persist(aditionalinformation);
		LOGGER.info("AditionalInformation saved successfully, AditionalInformation Details=" + aditionalinformation);
	}

	@Override
	public void updateAditionalInformation(final BaseEntity aditionalinformation) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.update(aditionalinformation);
		LOGGER.info("AditionalInformation updated successfully, AditionalInformation Details=" + aditionalinformation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AditionalInformation> listAditionalInformation(final String aditionalinformationName) {
		final Session session = this.sessionFactory.getCurrentSession();
		final List<AditionalInformation> objectList = session.createQuery("from " + aditionalinformationName).list();
		for (final Object aditionalinformation : objectList) {
			LOGGER.info("AditionalInformation List::" + aditionalinformation);
		}
		return objectList;
	}

	@Override
	public List<AditionalInformation> listAditionalInformation(final int id) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(AditionalInformation.class);
		// crit.add(Restrictions.eq("eventCategory_Legislations.id", id));
		final List<AditionalInformation> results = crit.list();

		return results;
	}

	@Override
	public AditionalInformation getAditionalInformationById(final int id,
			final Class<? extends BaseEntity> aditionalinformationclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final AditionalInformation aditionalinformation = (AditionalInformation) session.load(aditionalinformationclass,
				new Integer(id));
		LOGGER.info("AditionalInformation loaded successfully, AditionalInformation details=" + aditionalinformation);
		return aditionalinformation;
	}

	@Override
	public void removeAditionalInformation(final int id, final Class<? extends BaseEntity> aditionalinformationclass) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Object aditionalinformation = getAditionalInformationById(id, aditionalinformationclass);
		session.delete(aditionalinformation);
		LOGGER.info("AditionalInformation deleted successfully, AditionalInformation details=" + aditionalinformation);
	}

}
