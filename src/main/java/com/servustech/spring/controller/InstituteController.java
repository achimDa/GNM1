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

import com.servustech.spring.model.Institute;
import com.servustech.spring.model.dto.InstituteModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.InstituteService;
/**
 *
 * @author user
 *
 */
@Controller
public class InstituteController {
	@Autowired
	private InstituteService instituteService;
	
	/**
	 * List institute method
	 *
	 * @param model
	 * @return lists the institutes of a certain area based on a id
	 */


	@RequestMapping(value = "/institute", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<InstituteModelDTO> listInstitute(@PathVariable("adress.id") final int id) {
		return new CollectionResponse<>(instituteService.listInstitute(id), InstituteModelDTO.HEADERS);
		
	}

	/**
	 * add institute method
	 *
	 * @param instituteModelDTO
	 * @return adds a new institute in the list and redirrects to the institute list
	 */

	@RequestMapping(value = "/institute", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<InstituteModelDTO> addInstitute(@RequestBody final InstituteModelDTO instituteModelDTO) {
		instituteService.save(instituteModelDTO);
		return new ItemResponse<InstituteModelDTO>(instituteModelDTO, InstituteModelDTO.HEADERS);

	}
	
	/**
	 * remove institute method
	 *
	 * @param id
	 * @return removes an institute from the list
	 */

	@RequestMapping(value = "/institute/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeInstitute(@PathVariable("id") final int id) {
		instituteService.removeInstitute(id, Institute.class);
		
	}

	/**
	 * edit institute method
	 *
	 * @param id
	 * @param instituteModelDTO
	 * @return edits an institute based on id and loads it at the end of the institute
	 *         list
	 */
	
	@RequestMapping(value = "/institute/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<InstituteModelDTO> editInstitute(@RequestBody final InstituteModelDTO instituteModelDTO) {
		instituteService.editInstitute(instituteModelDTO);
		return new ItemResponse<InstituteModelDTO>(instituteModelDTO, InstituteModelDTO.HEADERS);
	}

}