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

import com.servustech.spring.model.Area;
import com.servustech.spring.model.dto.AreaModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.AreaService;

@Controller
public class AreaController {
	@Autowired
	private AreaService areaService;

	/**
	 * List area method
	 *
	 * @param id
	 * @return lists the areas of a certain country based on country id
	 */

	@RequestMapping(value = "/country/{country.id}/area", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<AreaModelDTO> listArea(@PathVariable("country.id") final int id) {
		return new CollectionResponse<>(areaService.listArea(id), AreaModelDTO.HEADERS);
	}

	/**
	 * add area method
	 *
	 * @param areaModelDTO
	 * @return adds a new area in the list and redirects to the area list
	 */

	@RequestMapping(value = "/country/{country.id}/area", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<AreaModelDTO> addArea(@RequestBody final AreaModelDTO areaModelDTO) {
		areaService.save(areaModelDTO);
		return new ItemResponse<AreaModelDTO>(areaModelDTO, AreaModelDTO.HEADERS);
	}

	/**
	 * remove area method
	 *
	 * @param id
	 * @return removes an area from the list
	 */

	@RequestMapping(value = "/country/{country.id}/area/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeArea(@PathVariable("id") final int id) {
		areaService.removeArea(id, Area.class);

	}

	/**
	 * edit area method
	 *
	 * @param areaModelDTO
	 * 
	 * @return edits an area based on id and loads it at the end of the area
	 *         list
	 */

	@RequestMapping(value = "/country/{country.id}/area/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<AreaModelDTO> editArea(@RequestBody final AreaModelDTO areaModelDTO) {
		areaService.editArea(areaModelDTO);
	return new ItemResponse<AreaModelDTO>(areaModelDTO, AreaModelDTO.HEADERS);
	}

}