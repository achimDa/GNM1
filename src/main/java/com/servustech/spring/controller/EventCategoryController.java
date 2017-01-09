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

import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.dto.EventCategoryModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.EventCategoryService;

@Controller
public class EventCategoryController {

	@Autowired
	private EventCategoryService eventcategoryService;

	/**
	 * List event method
	 *
	 * @param model
	 * @return lists the events from all the areas
	 */

	@RequestMapping(value = "/eventcategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	@ResponseBody
	public CollectionResponse<EventCategoryModelDTO> listEventCategory() {
		return new CollectionResponse<>(this.eventcategoryService.listEventCategory(), EventCategoryModelDTO.HEADERS);
	}

	/**
	 * add event category method
	 *
	 * @param eventModelDTO
	 * @return adds a new event category in the list
	 */

	@RequestMapping(value = "/eventcategory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<EventCategoryModelDTO> addEventCategory(
			@RequestBody final EventCategoryModelDTO eventcategoryModelDTO) {
		this.eventcategoryService.save(eventcategoryModelDTO);
		return new ItemResponse<EventCategoryModelDTO>(eventcategoryModelDTO, EventCategoryModelDTO.HEADERS);

	}

	/**
	 * remove event category method
	 *
	 * @param id
	 * @return removes an event category from the list
	 */

	@RequestMapping(value = "/eventcategory/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeEventCategory(@PathVariable("id") final int id) {
		this.eventcategoryService.removeEventCategory(id, EventCategory.class);

	}

	/**
	 * edit event category method
	 *
	 * @param eventcategoryModelDTO
	 * @return edits an event category based on id and loads it at the end of
	 *         the event category list
	 */

	@RequestMapping(value = "/eventcategory/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<EventCategoryModelDTO> editEventCategory(
			@RequestBody final EventCategoryModelDTO eventcategoryModelDTO) {
		this.eventcategoryService.editEventCategory(eventcategoryModelDTO);
		return new ItemResponse<EventCategoryModelDTO>(eventcategoryModelDTO, EventCategoryModelDTO.HEADERS);
	}

}