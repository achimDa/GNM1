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

import com.servustech.spring.model.Regulations;
import com.servustech.spring.model.dto.RegulationsModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.RegulationsService;

@Controller
public class RegulationsController {
	@Autowired
	private RegulationsService regulationsService;

	/**
	 * List Regulations method
	 *
	 * @param id
	 * @return lists the Regulations of a certain EventCategory based on
	 *         EventCategory id
	 */

	@RequestMapping(value = "/eventcategory/{eventCategory_Legislations.id}/regulations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<RegulationsModelDTO> listRegulations(
			@PathVariable("eventCategory_Regulations.id") final int id) {
		return new CollectionResponse<>(this.regulationsService.listRegulations(id), RegulationsModelDTO.HEADERS);
	}

	/**
	 * add Regulations method
	 *
	 * @param regulationsModelDTO
	 * @return adds a new Regulations in the list and redirects to the
	 *         Regulations list
	 */

	@RequestMapping(value = "/eventcategory/{eventCategory_Regulations.id}/regulations", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<RegulationsModelDTO> addRegulations(
			@RequestBody final RegulationsModelDTO regulationsModelDTO) {
		this.regulationsService.save(regulationsModelDTO);
		return new ItemResponse<RegulationsModelDTO>(regulationsModelDTO, RegulationsModelDTO.HEADERS);
	}

	/**
	 * remove Regulations method
	 *
	 * @param id
	 * @return removes a Regulations from the list
	 */

	@RequestMapping(value = "/eventcategory/{eventCategory_Regulations.id}/regulations/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeRegulations(@PathVariable("id") final int id) {
		this.regulationsService.removeRegulations(id, Regulations.class);

	}

	/**
	 * edit Legislation method
	 *
	 * @param legislationModelDTO
	 *
	 * @return edits a Legislation based on id and loads it at the end of the
	 *         Legislation list
	 */

	@RequestMapping(value = "/eventcategory/{eventCategory_Regulations.id}/regulations/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<RegulationsModelDTO> editRegulations(
			@RequestBody final RegulationsModelDTO regulationsModelDTO) {
		this.regulationsService.editRegulations(regulationsModelDTO);
		return new ItemResponse<RegulationsModelDTO>(regulationsModelDTO, RegulationsModelDTO.HEADERS);
	}

}