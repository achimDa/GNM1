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

import com.servustech.spring.model.InternalMetaData;
import com.servustech.spring.model.dto.InternalMetaDataModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.InternalMetaDataService;

@Controller
public class InternalMetaDataController {
	@Autowired
	private InternalMetaDataService internalmetadataService;

	/**
	 * List InternalMetaData method
	 *
	 * @param model
	 * @return lists the InternalMetaData of a certain MetaDatamap based on MetaDataMap id
	 */
	
	@RequestMapping(value = "/internalmetadata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<InternalMetaDataModelDTO> listInternalMetaData(@PathVariable("metadatamap.id") final int id) {
		return	new CollectionResponse<>(internalmetadataService.listInternalMetaData(id), InternalMetaDataModelDTO.HEADERS);
		
	}

	/**
	 * add InternalMetaData method
	 *
	 * @param internalModelDTO
	 * @return adds a new InternalMetaData in the list and redirrects to the InternalMetaData list
	 */
	
	
	@RequestMapping(value = "/internalmetadata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<InternalMetaDataModelDTO> addInternalMetaData(@RequestBody final InternalMetaDataModelDTO internalModelDTO) {
		internalmetadataService.save(internalModelDTO);
		return new ItemResponse<InternalMetaDataModelDTO>(internalModelDTO, InternalMetaDataModelDTO.HEADERS);

	}
	
	/**
	 * remove InternalMetaData method
	 *
	 * @param id
	 * @return removes an InternalMetaData from the list
	 */

	@RequestMapping(value = "/internalmetadata/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeInternalMetaData(@PathVariable("id") final int id) {
		internalmetadataService.removeInternalMetaData(id, InternalMetaData.class);

	}

	/**
	 * edit InternalMetaData method
	 *
	 * @param internalModelDTO
	
	 * @return edits an InternalMetaData based on MetaDataMap id and loads it at the end of the InternalMetaData
	 *         list
	 */
	
	@RequestMapping(value = "/internalmetadata/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<InternalMetaDataModelDTO> editInternalMetaData(@RequestBody final InternalMetaDataModelDTO internalModelDTO) {
				internalmetadataService.editInternalMetaData(internalModelDTO);
		return new ItemResponse<InternalMetaDataModelDTO>(internalModelDTO, InternalMetaDataModelDTO.HEADERS);
	}

}