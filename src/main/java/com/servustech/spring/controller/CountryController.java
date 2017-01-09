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

import com.servustech.spring.model.Country;
import com.servustech.spring.model.dto.CountryModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.CountryService;

@Controller
public class CountryController {
	@Autowired
	private CountryService countryService;

	/**
	 * List country method
	 * 
	 * @param id
	 * @return the countries stored in the database
	 */

	@RequestMapping(value = "/country", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<CountryModelDTO> listCountry() {
		return  new CollectionResponse<>(countryService.listCountry(), CountryModelDTO.HEADERS);
		
	}

	/**
	 * add country method
	 * 
	 * @param countryModelDTO
	 * @return adds a new country to the list and redirects to the country list
	 */

	@RequestMapping(value = "/country", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<CountryModelDTO> addCountry(@RequestBody final CountryModelDTO countryModelDTO) {
		countryService.save(countryModelDTO);
		return new ItemResponse<CountryModelDTO>(countryModelDTO, CountryModelDTO.HEADERS);

	}

	/**
	 * remove country method
	 * 
	 * @param id
	 * @return removes a country from the list based on country ID and
	 *         redirects to the country list
	 */
	
	@RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeCountry(@PathVariable("id") final int id) {
		countryService.removeCountry(id, Country.class);
	}

	/**
	 * edit country method
	 * 
	 * @param countryModelDTO
	 * @param model
	 * @return edits a country based on id and loads it at the end of the
	 *         country list
	 */

	@RequestMapping(value = "/country/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<CountryModelDTO> editCountry(@RequestBody  final CountryModelDTO countryModelDTO){ 
		 countryService.editCountry(countryModelDTO);
		return new ItemResponse<CountryModelDTO>(countryModelDTO, CountryModelDTO.HEADERS);
	}

}