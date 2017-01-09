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

import com.servustech.spring.model.Adress;
import com.servustech.spring.model.dto.AdressModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.AdressService;

@Controller
public class AdressController {

	@Autowired
	private AdressService adressService;

	/**
	 * List address method
	 *
	 * @param model
	 * @return lists the address of a certain id (user or company or issuing
	 *         unit) based on user id
	 */

	@RequestMapping(value = "/city/{city.id}/adress", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<AdressModelDTO> listAddress(@PathVariable("city.id") final int id) {
		return new CollectionResponse<>(this.adressService.listAdress(id), AdressModelDTO.HEADERS);
	}

	/**
	 * Save address
	 *
	 * @param adressModelDTO
	 *            address of the user
	 * @return redirect to addresses list
	 */

	@RequestMapping(value = "/city/{city.id}/adress", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<AdressModelDTO> addAdress(@RequestBody final AdressModelDTO adressModelDTO) {
		this.adressService.save(adressModelDTO);
		return new ItemResponse<AdressModelDTO>(adressModelDTO, AdressModelDTO.HEADERS);
	}

	/**
	 * remove adress method
	 *
	 * @param id
	 * @return removes an adress from the list
	 */

	@RequestMapping(value = "/city/{city.id}/adress/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeAdress(@PathVariable("id") final int id) {
		this.adressService.removeAdress(id, Adress.class);
	}

	/**
	 * edit adress method
	 *
	 * @param adressModelDTO
	 * @return edits an adress based on id and loads it at the end of the adress
	 *         list
	 */

	@RequestMapping(value = "/city/{city.id}/adress/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<AdressModelDTO> editAdress(@RequestBody final AdressModelDTO adressModelDTO) {
		this.adressService.editAdress(adressModelDTO);
		return new ItemResponse<AdressModelDTO>(adressModelDTO, AdressModelDTO.HEADERS);
	}

}