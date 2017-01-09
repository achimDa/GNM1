package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.Contacts;
import com.servustech.spring.model.base.BaseEntity;

public interface ContactsDAO {

	void addContacts(BaseEntity contacts);

	void updateContacts(BaseEntity contacts);

	List<Contacts> listContacts(String contactsName);

	List<Contacts> listContacts(int id);

	Contacts getContactsById(int id, Class<? extends BaseEntity> contactsclass);

	void removeContacts(int id, final Class<? extends BaseEntity> contactsclass);
}