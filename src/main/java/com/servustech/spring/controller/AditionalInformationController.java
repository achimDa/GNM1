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

import com.servustech.spring.model.AditionalInformation;
import com.servustech.spring.model.dto.AditionalInformationModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.AditionalInformationService;

@Controller
public class AditionalInformationController {
	@Autowired
	private AditionalInformationService aditionalinformationService;

	/**
	 * List AditionalInformation method
	 *
	 * @param id
	 * @return lists the Aditional Information of a certain Ecological Corridor
	 *         based on Ecological Corridor id
	 */

	@RequestMapping(value = "/ecologicalcorridor/{ecologicalCorridor_AditionalInformation.id}/aditionalinformation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<AditionalInformationModelDTO> listAditionalInformation(
			@PathVariable("ecologicalCorridor_AditionalInformation.id") final int id) {
		return new CollectionResponse<>(this.aditionalinformationService.listAditionalInformation(id),
				AditionalInformationModelDTO.HEADERS);
	}

	/**
	 * add Aditional Information method
	 *
	 * @param aditionalinformationModelDTO
	 * @return adds a new Aditional Information in the list and redirects to the
	 *         Aditional Information list
	 */

	@RequestMapping(value = "/ecologicalcorridor/{ecologicalCorridor_AditionalInformation.id}/aditionalinformation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<AditionalInformationModelDTO> addAditionalInformation(
			@RequestBody final AditionalInformationModelDTO aditionalinformationModelDTO) {
		this.aditionalinformationService.save(aditionalinformationModelDTO);
		return new ItemResponse<AditionalInformationModelDTO>(aditionalinformationModelDTO,
				AditionalInformationModelDTO.HEADERS);
	}

	/**
	 * remove Aditional Information method
	 *
	 * @param id
	 * @return removes a Aditional Information from the list
	 */

	@RequestMapping(value = "/ecologicalcorridor/{ecologicalCorridor_AditionalInformation.id}/aditionalinformation/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeAditionalInformation(@PathVariable("id") final int id) {
		this.aditionalinformationService.removeAditionalInformation(id, AditionalInformation.class);

	}

	/**
	 * edit Aditional Information method
	 *
	 * @param aditionalinformationModelDTO
	 *
	 * @return edits a Aditional Information based on id and loads it at the end
	 *         of the Aditional Information list
	 */

	@RequestMapping(value = "/ecologicalcorridor/{ecologicalCorridor_AditionalInformation.id}/aditionalinformation/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<AditionalInformationModelDTO> editAditionalInformation(
			@RequestBody final AditionalInformationModelDTO aditionalinformationModelDTO) {
		this.aditionalinformationService.editAditionalInformation(aditionalinformationModelDTO);
		return new ItemResponse<AditionalInformationModelDTO>(aditionalinformationModelDTO,
				AditionalInformationModelDTO.HEADERS);
	}

}