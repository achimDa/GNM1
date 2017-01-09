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

import com.servustech.spring.model.Legislation;
import com.servustech.spring.model.dto.LegislationModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.LegislationService;

@Controller
public class LegislationController {
	@Autowired
	private LegislationService legislationService;

	/**
	 * List Legislation method
	 *
	 * @param id
	 * @return lists the Legislations of a certain EventCategory based on
	 *         EventCategory id
	 */

	@RequestMapping(value = "/eventcategory/{eventcategory.id}/legislation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<LegislationModelDTO> listLegislation(@PathVariable("eventcategory.id") final int id) {
		return new CollectionResponse<>(this.legislationService.listLegislation(id), LegislationModelDTO.HEADERS);
	}

	/**
	 * add Legislation method
	 *
	 * @param legislationModelDTO
	 * @return adds a new Legislation in the list and redirects to the
	 *         Legislation list
	 */

	@RequestMapping(value = "/eventcategory/{eventcategory.id}/legislation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<LegislationModelDTO> addLegislation(
			@RequestBody final LegislationModelDTO legislationModelDTO) {
		this.legislationService.save(legislationModelDTO);
		return new ItemResponse<LegislationModelDTO>(legislationModelDTO, LegislationModelDTO.HEADERS);
	}

	/**
	 * remove Legislation method
	 *
	 * @param id
	 * @return removes a Legislation from the list
	 */

	@RequestMapping(value = "/eventcategory/{eventcategory.id}/legislation/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeLegislation(@PathVariable("id") final int id) {
		this.legislationService.removeLegislation(id, Legislation.class);

	}

	/**
	 * edit Legislation method
	 *
	 * @param legislationModelDTO
	 *
	 * @return edits a Legislation based on id and loads it at the end of the
	 *         Legislation list
	 */

	@RequestMapping(value = "/eventcategory/{eventcategory.id}/legislation/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<LegislationModelDTO> editLegislation(
			@RequestBody final LegislationModelDTO legislationModelDTO) {
		this.legislationService.editLegislation(legislationModelDTO);
		return new ItemResponse<LegislationModelDTO>(legislationModelDTO, LegislationModelDTO.HEADERS);
	}

}