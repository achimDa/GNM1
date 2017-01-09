package com.servustech.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.servustech.spring.model.Contacts;
import com.servustech.spring.model.dto.ContactsModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.ContactsService;

@Controller
public class ContactsController {

	@Autowired
	private ContactsService contactsService;

	/**
	 * List contacts method
	 *
	 * @param model
	 * @return lists all contacts of a certain agency based on agency id
	 */

	@RequestMapping(value = "/contacts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<ContactsModelDTO> listContacts(@PathVariable("agency.id")final int id) {
		return	new CollectionResponse<>(contactsService.listContacts(id), ContactsModelDTO.HEADERS);
		 
	}

	/**
	 * add contacts method
	 *
	 * @param contacts
	 * @return adds a new contact in the list and redirrects to the contacts list
	 */

	@RequestMapping(value = "/contacte", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<ContactsModelDTO> addContacts(@RequestBody final ContactsModelDTO contactsModelDTO) {
		contactsService.save(contactsModelDTO);
		return new ItemResponse<ContactsModelDTO>(contactsModelDTO, ContactsModelDTO.HEADERS);

	}

	/**
	 * remove contacts method
	 *
	 * @param id
	 * @return removes a contact from the list
	 */


	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeContacts(@PathVariable("id") final int id) {

		contactsService.removeContacts(id, Contacts.class);

	}

	/**
	 * edit contact method
	 *
	 * @param id
	 * @param model
	 * @return edits a contact based on id and loads it at the end of the contacts
	 *         list
	 */
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<ContactsModelDTO> editContacts(@RequestBody final ContactsModelDTO contactsModelDTO) {
		contactsService.editContacts(contactsModelDTO);
		return new ItemResponse<ContactsModelDTO>(contactsModelDTO, ContactsModelDTO.HEADERS);
	}

}