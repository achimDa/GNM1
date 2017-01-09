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

import com.servustech.spring.model.Event;
import com.servustech.spring.model.dto.EventModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.EventService;

@Controller
public class EventController {
	@Autowired
	private EventService eventService;

	/**
	 * List event method
	 *
	 * @param id
	 * @return lists the events of a certain event category  based on event category  id
	 */

	@RequestMapping(value = "/eventcategory/{eventcategory.id}/event", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<EventModelDTO> listEvent(@PathVariable("eventcategory.id") final int id) {
		return new CollectionResponse<>(eventService.listEvent(id), EventModelDTO.HEADERS);
	}

	/**
	 * add event method
	 *
	 * @param eventModelDTO
	 * @return adds a new event in the list and redirects to the event list
	 */

	@RequestMapping(value = "/eventcategory/{eventcategory.id}/event", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public  ItemResponse<EventModelDTO> addEvent(@RequestBody final EventModelDTO eventModelDTO) {
		eventService.save(eventModelDTO);
		return new ItemResponse<EventModelDTO>(eventModelDTO, EventModelDTO.HEADERS);

	}

	/**
	 * remove event method
	 *
	 * @param id
	 * @return removes an event from the list
	 */

	@RequestMapping(value = "/eventcategory/{eventcategory.id}/event/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeEvent(@PathVariable("id") final int id) {
		eventService.removeEvent(id, Event.class);

	}

	/**
	 * edit event method
	 *
	 * @param eventModelDTO
	
	 * @return edits an event based on id and loads it at the end of the event
	 *         list
	 */

	@RequestMapping(value = "/eventcategory/{eventcategory.id}/event/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<EventModelDTO> editEvent(@RequestBody final EventModelDTO eventModelDTO) {
		eventService.editEvent(eventModelDTO);
		return new ItemResponse<EventModelDTO>(eventModelDTO, EventModelDTO.HEADERS);
	}

}