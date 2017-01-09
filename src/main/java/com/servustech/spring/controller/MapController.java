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

import com.servustech.spring.model.Map;
import com.servustech.spring.model.dto.MapModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.MapService;

@Controller
public class MapController {
	@Autowired
	private MapService mapService;

	/**
	 * List maps method
	 *
	 * @param id
	 * @return list the map
	 */
	@RequestMapping(value = "/map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<MapModelDTO> listMap(@PathVariable("map.id") final int id) {
			return new CollectionResponse<>(mapService.listMap(id), MapModelDTO.HEADERS);
	
	}

	/**
	 * Add a map new map method
	 * 
	 * @param map
	 * 
	 * @return if the did not exist before it will create a new one if the map
	 *         allready existed it will update it
	 */

	@RequestMapping(value = "/map", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<MapModelDTO> addMap(@RequestBody final MapModelDTO mapModelDTO) {
		mapService.save(mapModelDTO);
		return new ItemResponse<MapModelDTO>(mapModelDTO, MapModelDTO.HEADERS);
	}
	
	/**
	 * remove map method
	 *
	 * @param id
	 * @return removes a map from the list
	 */

	@RequestMapping(value = "/map/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeMap(@PathVariable("id") final int id) {
		mapService.removeMap(id, Map.class);
	}

	/**
	 * edit map method
	 *
	 * @param id
	 * @param model
	 * @return edits a map based on id and loads it at the end of the map
	 *         list
	 */
	
	@RequestMapping(value ="/map/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<MapModelDTO> editMap(@RequestBody final MapModelDTO mapModelDTO) {
	
		mapService.editMap(mapModelDTO);
		return new ItemResponse<MapModelDTO>(mapModelDTO, MapModelDTO.HEADERS);
	}

}