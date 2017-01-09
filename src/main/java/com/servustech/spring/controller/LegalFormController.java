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

import com.servustech.spring.model.LegalForm;
import com.servustech.spring.model.dto.LegalFormModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.LegalFormService;

@Controller
public class LegalFormController {
	@Autowired
	private LegalFormService legalformService;
	
	/**
	 * List legalform  method
	 * 
	 * @param id
	 * @return returns the legal forms stored in the database
	 */

	@RequestMapping(value = "/legalform", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<LegalFormModelDTO> listLegalForm() {
		return	new CollectionResponse<>(legalformService.listLegalForm(), LegalFormModelDTO.HEADERS);
		
	}


	/**
	 * add legal form method
	 * 
	 * @param legalformModelDTO
	 * @return adds a new legal form to the list and redirects to the legal form list
	 */

	@RequestMapping(value = "/legalform", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<LegalFormModelDTO> addLegalForm(@RequestBody final LegalFormModelDTO legalformModelDTO) {
		legalformService.save(legalformModelDTO);
		return new ItemResponse<LegalFormModelDTO>(legalformModelDTO, LegalFormModelDTO.HEADERS);

	}
	
	
	/**
	 * remove legal form method
	 * 
	 * @param id
	 * @return removes a legal form from the list based on ID and
	 *         redirects to the legal form list
	 */
	
	
	@RequestMapping(value ="/legalform/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeLegalForm(@PathVariable("id") final int id) {
		legalformService.removeLegalForm(id, LegalForm.class);
		
	}
	
	
	/**
	 * edit country method
	 * 
	 * @param countryModelDTO
	 * @param model
	 * @return edits a country based on id and loads it at the end of the
	 *         country list
	 */
	
	@RequestMapping(value = "/legalform/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<LegalFormModelDTO> editLegalForm(@RequestBody final LegalFormModelDTO legalformModelDTO) {
	 legalformService.editLegalForm(legalformModelDTO);
		return new ItemResponse<LegalFormModelDTO>(legalformModelDTO, LegalFormModelDTO.HEADERS);
	}

}