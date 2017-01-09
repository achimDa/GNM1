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

import com.servustech.spring.model.ProtectedAreaType;
import com.servustech.spring.model.dto.ProtectedAreaTypeModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.ProtectedAreaTypeService;

@Controller
public class ProtectedAreaTypeController {

	@Autowired
	private ProtectedAreaTypeService protectedareatypeService;
	
	/**
	 * List Protected Area Type method
	 * 
	 * @param id
	 * @return the Protected Area Types stored in the database
	 */

	@RequestMapping(value = "/protectedareatype", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<ProtectedAreaTypeModelDTO> listProtectedAreaType() {
		return	new CollectionResponse<>(protectedareatypeService.listProtectedAreaType(), ProtectedAreaTypeModelDTO.HEADERS);
		 
	}

	/**
	 * add Protected Area Type method
	 * 
	 * @param protectedModelDTO
	 * @return adds a new Protected Area Type to the list and redirects to the Protected Area Type list
	 */

	@RequestMapping(value = "/protectedareatype", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<ProtectedAreaTypeModelDTO> addProtectedAreaType(@RequestBody final ProtectedAreaTypeModelDTO protectedModelDTO) {
		protectedareatypeService.save(protectedModelDTO);
	  return new ItemResponse<ProtectedAreaTypeModelDTO>(protectedModelDTO, ProtectedAreaTypeModelDTO.HEADERS);

	}
	
	/**
	 * remove protected area type method
	 * 
	 * @param id
	 * @return removes a protected area type from the list based on ID and
	 *         redirects to the protected area type list
	 */

	@RequestMapping(value ="/protectedareatype/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeProtectedAreaType(@PathVariable("id") final int id) {

		protectedareatypeService.removeProtectedAreaType(id, ProtectedAreaType.class);
	}

	/**
	 * edit Protected Area Type method
	 * 
	 * @param protectedModelDTO
	 * @param model
	 * @return edits a protected area type based on id and loads it at the end of the
	 *         protected area type list
	 */
	
	@RequestMapping(value = "/protectedareatype/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ItemResponse<ProtectedAreaTypeModelDTO> editProtectedAreaType(@RequestBody final ProtectedAreaTypeModelDTO protectedModelDTO) {
				protectedareatypeService.editProtectedAreaType(protectedModelDTO);
		return new ItemResponse<ProtectedAreaTypeModelDTO>(protectedModelDTO, ProtectedAreaTypeModelDTO.HEADERS);
	}

}