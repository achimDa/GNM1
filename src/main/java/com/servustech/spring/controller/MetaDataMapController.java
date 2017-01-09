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

import com.servustech.spring.model.MetaDataMap;
import com.servustech.spring.model.dto.MetaDataMapModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.MetaDataMapService;


@Controller
public class MetaDataMapController {
	@Autowired
	private MetaDataMapService metadatamapService;
	
	/**
	 * List MetaDataMap method
	 *
	 * @param id
	 * @return lists the MetaDataMap of a certain Map based on Map id
	 */

	@RequestMapping(value = "/metadatamap", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<MetaDataMapModelDTO> listMetaDataMap(@PathVariable("map.id") final int id) {
		return	new CollectionResponse<>(metadatamapService.listMetaDataMap(id), MetaDataMapModelDTO.HEADERS);
		
	}

	/**
	 * add MetaDataMap method
	 *
	 * @param metamapModelDTO
	 * @return adds a new MetaDataMap in the list and redirects to the MetaDataMap list
	 */
	@RequestMapping(value = "/metadatamap", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<MetaDataMapModelDTO> addMetaDataMap(@RequestBody final MetaDataMapModelDTO metamapModelDTO) {
		metadatamapService.save(metamapModelDTO);
		return new ItemResponse<MetaDataMapModelDTO>(metamapModelDTO, MetaDataMapModelDTO.HEADERS);

	}

	/**
	 * remove MetaDataMap method
	 *
	 * @param id
	 * @return removes an MetaDataMap from the list
	 */
	
	@RequestMapping(value ="/metadatamap/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeMetaDataMap(@PathVariable("id") final int id) {
		metadatamapService.removeMetaDataMap(id, MetaDataMap.class);

	}
	
	/**
	 * edit MetaDataMap method
	 *
	 * @param metamapModelDTO
	
	 * @return edits an MetaDataMap based on map id and loads it at the end of the MetaDataMap
	 *         list
	 */


	@RequestMapping(value = "/metadatamap/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<MetaDataMapModelDTO> editMetaDataMap(@RequestBody final MetaDataMapModelDTO metamapModelDTO) {
		metadatamapService.editMetaDataMap(metamapModelDTO);
		return new ItemResponse<MetaDataMapModelDTO>(metamapModelDTO, MetaDataMapModelDTO.HEADERS);
	}

}