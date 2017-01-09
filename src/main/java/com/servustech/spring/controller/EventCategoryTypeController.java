package com.servustech.spring.controller;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.servustech.spring.model.EventCategoryType;
import com.servustech.spring.model.dto.EventCategoryTypeModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.EventCategoryTypeService;

@Controller
public class EventCategoryTypeController {
	/** Logger */
	private static final Logger LOGGER = LogManager.getLogger(EventCategoryTypeController.class);

	@Autowired
	private EventCategoryTypeService eventcategorytypeService;

	/**
	 * List types of Event category method
	 *
	 * @param id
	 * @return the returns the types of event categories stored in the database
	 */

	@RequestMapping(value = "/eventcategorytype", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<EventCategoryTypeModelDTO> listEventCategoryType() {
		return new CollectionResponse<>(this.eventcategorytypeService.listEventCategoryType(),
				EventCategoryTypeModelDTO.HEADERS);
	}

	/**
	 * add country method
	 *
	 * @param eventtypeModelDTO
	 * @return adds a new type of event category to the list and redirects to
	 *         the event category type list
	 */

	@RequestMapping(value = "/eventcategorytype", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<EventCategoryTypeModelDTO> addEventCategoryType(
			@RequestBody final EventCategoryTypeModelDTO eventtypeModelDTO) {
		this.eventcategorytypeService.save(eventtypeModelDTO);
		return new ItemResponse<EventCategoryTypeModelDTO>(eventtypeModelDTO, EventCategoryTypeModelDTO.HEADERS);

	}

	/**
	 * remove type of event category method
	 *
	 * @param id
	 * @return removes a type of event category from the list based on
	 *         eventcategory ID and redirects to the event category type list
	 */

	@RequestMapping(value = "/eventcategorytype/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeEventCategoryType(@PathVariable("id") final int id) {
		this.eventcategorytypeService.removeEventCategoryType(id, EventCategoryType.class);

	}

	/**
	 * edit country method
	 *
	 * @param eventtypeModelDTO
	 * @param model
	 * @return edits a country based on id and loads it at the end of the
	 *         country list
	 */

	@RequestMapping(value = "/eventcategorytype/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<EventCategoryTypeModelDTO> editEventCategoryType(
			@RequestBody final EventCategoryTypeModelDTO eventtypeModelDTO) {
		this.eventcategorytypeService.editEventCategoryType(eventtypeModelDTO);
		return new ItemResponse<EventCategoryTypeModelDTO>(eventtypeModelDTO, EventCategoryTypeModelDTO.HEADERS);
	}

	@RequestMapping(value = "/eventcategorytype/{id}/pictures", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addPicture(@PathVariable("id") final int eventCategoryTypeID,
			@RequestParam("file") final MultipartFile file) throws IOException {
		// upload
		LOGGER.info("Uploading file of " + file.getSize() + " bytes");
		this.eventcategorytypeService.addEventCategoryPicture(file.getBytes());
	}

}