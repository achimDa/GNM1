package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.ContactsDAO;
import com.servustech.spring.model.Contacts;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.ContactsModelConverter;
import com.servustech.spring.model.dto.ContactsModelDTO;

@Service
@Transactional

public class ContactsServiceImpl implements ContactsService {
	@Autowired
	private ContactsDAO contactsDAO;

	@Override
	public void addContacts(final BaseEntity contacts) {
		contactsDAO.addContacts(contacts);
	}

	@Override
	public void updateContacts(final BaseEntity contacts) {
		contactsDAO.updateContacts(contacts);
	}

	@Override
	public void save(final ContactsModelDTO contactsModelDTO) {
		final Contacts contacts = new Contacts();
		ContactsModelConverter.toEntity(contactsModelDTO, contacts);
		if (contacts.getId() == 0) {
			addContacts(contacts);
		} else {
			updateContacts(contacts);
		}
	}

	@Override
	public List<Contacts> listContacts(final String contactsName) {
		return contactsDAO.listContacts(contactsName);
	}

	@Override
	public List<ContactsModelDTO> listContacts(final int objectID) {
		return ContactsModelConverter.toDTOList(contactsDAO.listContacts(objectID));
	}

	@Override
	public Contacts getContactsById(final int id, final Class<? extends BaseEntity> contactsclass) {
		return contactsDAO.getContactsById(id, contactsclass);
	}

	@Override
	public void removeContacts(final int id, final Class<? extends BaseEntity> contactsclass) {
		contactsDAO.removeContacts(id, contactsclass);
	}

	@Override
	public void editContacts(final ContactsModelDTO contactsModelDTO) {
		final Contacts contacts = getContactsById(contactsModelDTO.getId(), Contacts.class);

		contacts.setEmail(contactsModelDTO.getEmail());
		contacts.setFax(contactsModelDTO.getFax());
		contacts.setFunctie(contactsModelDTO.getFunctie());
		contacts.setId(contactsModelDTO.getId());
		contacts.setNume(contactsModelDTO.getNume());
		contacts.setPrenume(contactsModelDTO.getPrenume());
		contacts.setTelefon1(contactsModelDTO.getTelefon1());

		contactsDAO.updateContacts(contacts);

	}

}