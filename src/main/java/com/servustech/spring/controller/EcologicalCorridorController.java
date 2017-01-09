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

import com.servustech.spring.model.EcologicalCorridor;
import com.servustech.spring.model.dto.EcologicalCorridorModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.EcologicalCorridorService;

@Controller
public class EcologicalCorridorController {

	@Autowired
	private EcologicalCorridorService ecologicalcorridorService;

	/**
	 * List Ecological Corridor method
	 *
	 * @param model
	 * @return lists the Ecological Corridors from all the areas
	 */

	@RequestMapping(value = "/ecologicalcorridor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<EcologicalCorridorModelDTO> listEcologicalCorridor() {
		return new CollectionResponse<>(this.ecologicalcorridorService.listEcologicalCorridor(),
				EcologicalCorridorModelDTO.HEADERS);
	}

	/**
	 * add EcologicalCorridor method
	 *
	 * @param ecologicalcorridorModelDTO
	 * @return adds a new Ecological Corridor in the list
	 */

	@RequestMapping(value = "/ecologicalcorridor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<EcologicalCorridorModelDTO> addEcologicalCorridor(
			@RequestBody final EcologicalCorridorModelDTO ecologicalcorridorModelDTO) {
		this.ecologicalcorridorService.save(ecologicalcorridorModelDTO);
		return new ItemResponse<EcologicalCorridorModelDTO>(ecologicalcorridorModelDTO,
				EcologicalCorridorModelDTO.HEADERS);

	}

	/**
	 * remove Ecological Corridor method
	 *
	 * @param id
	 * @return removes an Ecological Corridor from the list
	 */

	@RequestMapping(value = "/ecologicalcorridor/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeEcologicalCorridor(@PathVariable("id") final int id) {
		this.ecologicalcorridorService.removeEcologicalCorridor(id, EcologicalCorridor.class);

	}

	/**
	 * edit event category method
	 *
	 * @param eventcategoryModelDTO
	 * @return edits an event category based on id and loads it at the end of
	 *         the event category list
	 */

	@RequestMapping(value = "/ecologicalcorridor/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<EcologicalCorridorModelDTO> editEcologicalCorridor(
			@RequestBody final EcologicalCorridorModelDTO ecologicalcorridorModelDTO) {
		this.ecologicalcorridorService.editEcologicalCorridor(ecologicalcorridorModelDTO);
		return new ItemResponse<EcologicalCorridorModelDTO>(ecologicalcorridorModelDTO,
				EcologicalCorridorModelDTO.HEADERS);
	}

}