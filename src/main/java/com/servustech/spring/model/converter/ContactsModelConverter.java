package com.servustech.spring.model.converter; 

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.Contacts;
import com.servustech.spring.model.dto.ContactsModelDTO;



public class ContactsModelConverter {
	public static ContactsModelDTO toDTO(final Contacts model) {
		final ContactsModelDTO contactsdto = new ContactsModelDTO();
		contactsdto.setId(model.getId());
		contactsdto.setEmail(model.getEmail());
		contactsdto.setNume(model.getNume());
		contactsdto.setPrenume(model.getPrenume());
		contactsdto.setFax(model.getFax());
		contactsdto.setFunctie(model.getFunctie());
		contactsdto.setTelefon1(model.getTelefon1());
		
		
		return contactsdto;
	}

	public static List<ContactsModelDTO> toDTOList(final Collection<Contacts> contactsList) {
		final List<ContactsModelDTO> contacts = new ArrayList<>();
		for (final Contacts contactsModel : contactsList) {
			contacts.add(toDTO(contactsModel));
		}
		return contacts;
	}

	public static void toEntity(final ContactsModelDTO contactsmod, final Contacts contactsentity) {
	
		contactsentity.setId(contactsmod.getId());
		contactsentity.setEmail(contactsmod.getEmail());
		contactsentity.setNume(contactsmod.getNume());
		contactsentity.setPrenume(contactsmod.getPrenume());
		contactsentity.setFax(contactsmod.getFax());
		contactsentity.setFunctie(contactsmod.getFunctie());
		contactsentity.setTelefon1(contactsmod.getTelefon1());
		
	}
	
}