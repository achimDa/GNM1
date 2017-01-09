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

import com.servustech.spring.model.IssuingUnit;
import com.servustech.spring.model.dto.IssuingUnitModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.IssuingUnitService;


@Controller
public class IssuingUnitController {
	@Autowired
	private IssuingUnitService issuingunitService;
	

	/**
	 * List issunit unit method
	 * 
	 * @param id
	 * @return the issuing units stored in the database
	 */

	@RequestMapping(value = "/issuingunit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<IssuingUnitModelDTO>  listIssuingUnit() {
		return	new CollectionResponse<>(issuingunitService.listIssuingUnit(), IssuingUnitModelDTO.HEADERS);
		
	}

	/**
	 * add issunig unit method
	 * 
	 * @param issunitModelDTO
	 * @return adds a new issunig unit to the list and redirects to the issunig unit list
	 */
	
	
	@RequestMapping(value = "/issuingunit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<IssuingUnitModelDTO> addIssuingUnit(@RequestBody final IssuingUnitModelDTO issunitModelDTO) {
	issuingunitService.save(issunitModelDTO);
		return new ItemResponse<IssuingUnitModelDTO>(issunitModelDTO, IssuingUnitModelDTO.HEADERS);

	}
	
	/**
	 * remove issuing unit method
	 * 
	 * @param id
	 * @return removes an issuing unit from the list based on ID and
	 *         redirrects to the issunig unit list
	 */

	@RequestMapping(value = "/issuingunit/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeIssuingUnit(@PathVariable("id") final int id) {
		issuingunitService.removeIssuingUnit(id, IssuingUnit.class);
		
	}
	
	/**
	 * edit issuing unit method
	 * 
	 * @param issunitModelDTO
	 * @return edits an issunig unit based on id and loads it at the end of the
	 *         issunig unit list
	 */

	@RequestMapping(value = "/issuingunit/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<IssuingUnitModelDTO> editIssuingUnit(@RequestBody final IssuingUnitModelDTO issunitModelDTO) {
		
		issuingunitService.editIssuingUnit(issunitModelDTO);
		return new ItemResponse<IssuingUnitModelDTO>(issunitModelDTO, IssuingUnitModelDTO.HEADERS);
	}

}