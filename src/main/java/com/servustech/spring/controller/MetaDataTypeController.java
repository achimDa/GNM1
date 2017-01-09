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

import com.servustech.spring.model.MetaDataType;
import com.servustech.spring.model.dto.MetaDataTypeModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.MetaDataTypeService;

@Controller

public class MetaDataTypeController {

	@Autowired
	private MetaDataTypeService metadatatypeService;
	
	/**
	 * List MetaDataType method
	 * 
	 * @param id
	 * @return the MetaDataType stored in the database
	 */


	@RequestMapping(value = "/metadatatype", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<MetaDataTypeModelDTO> listMetaDataType() {
		return	new CollectionResponse<>(metadatatypeService.listMetaDataType(), MetaDataTypeModelDTO.HEADERS);
		
	}

	/**
	 * add counMetaDataTypetry method
	 * 
	 * @param metatypeModelDTO
	 * @return adds a new MetaDataType to the list and redirects to the MetaDataType list
	 */

	@RequestMapping(value = "/metadatatype", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<MetaDataTypeModelDTO> addMetaDataType(@RequestBody final MetaDataTypeModelDTO metatypeModelDTO) {
		metadatatypeService.save(metatypeModelDTO);
		return new ItemResponse<MetaDataTypeModelDTO>(metatypeModelDTO, MetaDataTypeModelDTO.HEADERS);

	}
	
	/**
	 * remove MetaDataType method
	 * 
	 * @param id
	 * @return removes a MetaDataType from the list based on MetaDatatype ID and
	 *         redirects to the MetaDataType list
	 */

	@RequestMapping(value = "/metadatatype/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeMetaDataType(@PathVariable("id") final int id) {
		metadatatypeService.removeMetaDataType(id, MetaDataType.class);

	}
	
	/**
	 * edit MetaDataType method
	 * 
	 * @param metatypeModelDTO
	 * @return edits a MetaDataType based on id and loads it at the end of the
	 *         MetaDataType list
	 */

	@RequestMapping(value = "/metadatatype/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<MetaDataTypeModelDTO> editMetaDataType(@RequestBody final MetaDataTypeModelDTO metatypeModelDTO) {
		 metadatatypeService.editMetaDataType(metatypeModelDTO);
		return new ItemResponse<MetaDataTypeModelDTO>(metatypeModelDTO, MetaDataTypeModelDTO.HEADERS);
	}

}
