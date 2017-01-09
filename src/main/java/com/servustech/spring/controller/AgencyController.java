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

import com.servustech.spring.model.Agency;
import com.servustech.spring.model.dto.AgencyModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.AgencyService;


@Controller
public class AgencyController {

	@Autowired
	private AgencyService agencyService;

	/**
	 * List agency method
	 *
	 * @param model
	 * @return lists the agency of a certain region based on id
	 */

	@RequestMapping(value = "/agency", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<AgencyModelDTO> listAgency(@PathVariable("societate.id") final int id) {	
		return new CollectionResponse<>(agencyService.listAgency(id), AgencyModelDTO.HEADERS);
	}

	/**
	 * add agency method
	 *
	 * @param agencyModelDTO
	 * @return adds a new agency in the list and redirrects to the area list
	 */
	@RequestMapping(value = "/agency", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<AgencyModelDTO> addAgency(@RequestBody final AgencyModelDTO agencyModelDTO) {
		agencyService.save(agencyModelDTO);
		return new ItemResponse<AgencyModelDTO>(agencyModelDTO, AgencyModelDTO.HEADERS);

	}

	/**
	 * remove agency method
	 *
	 * @param id
	 * @return removes an agency from the list
	 */

	@RequestMapping(value = "/agency/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeAgency(@PathVariable("id") final int id) {
		agencyService.removeAgency(id, Agency.class);

	}

	/**
	 * edit agency method
	 *
	 * @param agencyModelDTO
	 * @return edits an agency based on id and loads it at the end of the agency
	 *         list
	 */

	@RequestMapping(value = "/agency/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<AgencyModelDTO> editAgency(@RequestBody final AgencyModelDTO agencyModelDTO) {
		agencyService.editAgency(agencyModelDTO);
		return new ItemResponse<AgencyModelDTO>(agencyModelDTO, AgencyModelDTO.HEADERS);
	}

}