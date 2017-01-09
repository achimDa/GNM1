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

import com.servustech.spring.model.ProtectedArea;
import com.servustech.spring.model.dto.ProtectedAreaModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.ProtectedAreaService;
@Controller
public class ProtectedAreaController {

	@Autowired
	private ProtectedAreaService protectedareaService;
	
	/**
	 * List Protected area method
	 *
	 * @param model
	 * @return lists the Protected areas of a certain map based on id
	 */


	@RequestMapping(value = "/protectedarea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<ProtectedAreaModelDTO>  listProtectedArea(@PathVariable("protectedareatype.id")final int id) {
	return	new CollectionResponse<>(protectedareaService.listProtectedArea(id), ProtectedAreaModelDTO.HEADERS);
	
	}
	/**
	 * add protected area method
	 *
	 * @param protectedareaModelDTO
	 * @return adds a new protected area in the list and redirects to the area list
	 */
	
	
	@RequestMapping(value = "/protectedarea", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<ProtectedAreaModelDTO> addProtectedArea(@RequestBody final ProtectedAreaModelDTO protectedareaModelDTO) {
		protectedareaService.save(protectedareaModelDTO);
		return new ItemResponse<ProtectedAreaModelDTO>(protectedareaModelDTO, ProtectedAreaModelDTO.HEADERS);

	}
	
	/**
	 * remove protected area method
	 *
	 * @param id
	 * @return removes an protected area from the list
	 */

	@RequestMapping(value = "/protectedarea/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeProtectedArea(@PathVariable("id") final int id) {
		protectedareaService.removeProtectedArea(id, ProtectedArea.class);

	}

	/**
	 * edit protected area method
	 *
	 * @param protectedareaModelDTO

	 * @return edits a protected area based on id and loads it at the end of the protected area
	 *         list
	 */
	
	@RequestMapping(value = "/protectedarea/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<ProtectedAreaModelDTO> editProtectedArea(@RequestBody final ProtectedAreaModelDTO protectedareaModelDTO) {
	 protectedareaService.editProtectedArea(protectedareaModelDTO);
		return new ItemResponse<ProtectedAreaModelDTO>(protectedareaModelDTO, ProtectedAreaModelDTO.HEADERS);
	}

}