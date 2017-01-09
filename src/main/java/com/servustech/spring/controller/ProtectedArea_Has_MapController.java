
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

import com.servustech.spring.model.ProtectedArea_Has_Map;
import com.servustech.spring.model.dto.ProtectedArea_Has_MapModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.ProtectedArea_Has_MapService;

@Controller
public class ProtectedArea_Has_MapController {

	@Autowired
	private ProtectedArea_Has_MapService protectedareahasmapService;
	
	/**
	 * List ProtectedArea_Has_Map method
	 *
	 * @param model
	 * @return lists the ProtectedArea_Has_Map of a certain map based on protected area id
	 */

	@RequestMapping(value = "/protectedareahasmap", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<ProtectedArea_Has_MapModelDTO> listProtectedAreaHapMap(@PathVariable("protectedarea.id") final int id) {
		return	new CollectionResponse<>(protectedareahasmapService.listProtectedArea_Has_Map(id), ProtectedArea_Has_MapModelDTO.HEADERS);
	}

	/**
	 * add ProtectedArea_Has_Map method
	 *
	 * @param areahasmapModelDTO
	 * @return adds a new ProtectedArea_Has_Map in the list 
	 */
	@RequestMapping(value = "/protectedareahasmap", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<ProtectedArea_Has_MapModelDTO> addProtectedAreaHasMap(@RequestBody final ProtectedArea_Has_MapModelDTO areahasmapModelDTO) {
		protectedareahasmapService.save(areahasmapModelDTO);
		return new ItemResponse<ProtectedArea_Has_MapModelDTO>(areahasmapModelDTO, ProtectedArea_Has_MapModelDTO.HEADERS);

	}
	
	/**
	 * remove ProtectedArea_Has_Map method
	 *
	 * @param id
	 * @return removes an ProtectedArea_Has_Map from the list
	 */

	@RequestMapping(value = "/protectedareahasmap/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeProtectedAreaHasMap(@PathVariable("id") final int id) {
		protectedareahasmapService.removeProtectedArea_Has_Map(id, ProtectedArea_Has_Map.class);
	}
	
	/**
	 * edit ProtectedArea_Has_Map method
	 *
	 * @param areahasmapModelDTO
	
	 * @return edits an ProtectedArea_Has_Map based on id 
	 */

	@RequestMapping(value = "/protectedareahasmap/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<ProtectedArea_Has_MapModelDTO> editProtectedAreaHasMap(@RequestBody final ProtectedArea_Has_MapModelDTO areahasmapModelDTO) {
		protectedareahasmapService.editProtectedArea_Has_Map(areahasmapModelDTO);
		return new ItemResponse<ProtectedArea_Has_MapModelDTO>(areahasmapModelDTO, ProtectedArea_Has_MapModelDTO.HEADERS);
	}

}