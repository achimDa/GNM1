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

import com.servustech.spring.model.City;
import com.servustech.spring.model.dto.CityModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.CityService;

@Controller
public class CityController {
	@Autowired
	private CityService cityService;

	/**
	 * List city method
	 *
	 * @param id
	 * @return lists the cities of a certain area based on area id
	 */

	@RequestMapping(value = "/area/{area.id}/city", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<CityModelDTO> listCity(@PathVariable("area.id") final int id) {
		return new CollectionResponse<>(cityService.listCity(id), CityModelDTO.HEADERS);
	}

	/**
	 * add city method
	 *
	 * @param city
	 * @return adds a new city in the list and redirrects to the city list
	 */

	@RequestMapping(value = "/area/{area.id}/city", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<CityModelDTO> addCity(@RequestBody final CityModelDTO cityModelDTO) {
		cityService.save(cityModelDTO);

		return new ItemResponse<CityModelDTO>(cityModelDTO, CityModelDTO.HEADERS);

	}

	/**
	 * remove city method
	 *
	 * @param id
	 * @return removes an city from the list
	 */

	@RequestMapping(value = "/area/{area.id}/city/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeCity(@PathVariable("id") final int id) {

		cityService.removeCity(id, City.class);

	}

	/**
	 * edit city method
	 *
	 * @param cityModelDTO
	 * @return edits a city based on id and loads it at the end of the city list
	 */

	@RequestMapping(value = "/area/{area.id}/city/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<CityModelDTO> editCity(@RequestBody  final CityModelDTO cityModelDTO) {
	cityService.editCity(cityModelDTO);
	return new ItemResponse<CityModelDTO>(cityModelDTO, CityModelDTO.HEADERS);
	
	}

}