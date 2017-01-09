package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.Contacts;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.ContactsModelDTO;

public interface ContactsService {

	void addContacts(BaseEntity contacts);

	void save(ContactsModelDTO contactsModelDTO);

	void updateContacts(BaseEntity contacts);

	List<Contacts> listContacts(String contactsName);

	List<ContactsModelDTO> listContacts(int objectID);

	Object getContactsById(int id, Class<? extends BaseEntity> clazz);

	void removeContacts(int id, Class<? extends BaseEntity> clazz);

	void editContacts(ContactsModelDTO contactsModelDTO);
}
