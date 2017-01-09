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

import com.servustech.spring.model.ProtectedAreaResponsability;
import com.servustech.spring.model.dto.ProtectedAreaResponsabilityModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.ProtectedAreaResponsabilityService;

@Controller
public class ProtectedAreaResponsabilityController {

	@Autowired
	private ProtectedAreaResponsabilityService protectedarearesponsabilityService;

	/**
	 * List Protected Area Responsability method
	 * 
	 * @param id
	 * @return the Protected Area Responsability stored in the database
	 */

	
	@RequestMapping(value = "/protectedarearesponsability", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<ProtectedAreaResponsabilityModelDTO> listProtectedAreaResponsability() {
		return	new CollectionResponse<>(protectedarearesponsabilityService.listProtectedAreaResponsability(), ProtectedAreaResponsabilityModelDTO.HEADERS);
		 
	}

	/**
	 * add Protected Area Responsability method
	 * 
	 * @param protectedModelDTO
	 * @return adds a new Protected Area Responsability to the list and redirects to the Protected Area Responsability list
	 */
	
	
	@RequestMapping(value = "/protectedarearesponsability", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<ProtectedAreaResponsabilityModelDTO> addProtectedAreaResponsability(
			@RequestBody final ProtectedAreaResponsabilityModelDTO protectedModelDTO) {
		protectedarearesponsabilityService.save(protectedModelDTO);
		return new ItemResponse<ProtectedAreaResponsabilityModelDTO>(protectedModelDTO, ProtectedAreaResponsabilityModelDTO.HEADERS);

	}
	
	
	/**
	 * remove Protected Area Responsability method
	 * 
	 * @param id
	 * @return removes a Protected Area Responsability from the list based on protected area ID and
	 *         redirects to the Protected Area Responsability list
	 */
	

	@RequestMapping(value = "/protectedarearesponsability/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeProtectedAreaResponsability(@PathVariable("id") final int id) {

		protectedarearesponsabilityService.removeProtectedAreaResponsability(id, ProtectedAreaResponsability.class);
		
	}
	/**
	 * edit country method
	 * 
	 * @param protectedModelDTO
	 * @param model
	 * @return edits a Protected Area Responsability based on id and loads it at the end of the
	 *         Protected Area Responsability list
	 */
	

	@RequestMapping(value = "/protectedarearesponsability/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<ProtectedAreaResponsabilityModelDTO> editProtectedAreaResponsability(@RequestBody final ProtectedAreaResponsabilityModelDTO protectedModelDTO) {
		protectedarearesponsabilityService.editProtectedAreaResponsability(protectedModelDTO);
		return new ItemResponse<ProtectedAreaResponsabilityModelDTO>(protectedModelDTO, ProtectedAreaResponsabilityModelDTO.HEADERS);
	}

}