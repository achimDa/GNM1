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

import com.servustech.spring.model.CertifiedIssuingUnit;
import com.servustech.spring.model.dto.CertifiedIssuingUnitModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.CertifiedIssuingUnitService;

@Controller
public class CertifiedIssuingUnitController {
	@Autowired
	private CertifiedIssuingUnitService certifiedissuingunitService;

	/**
	 * List certified issuing unit method
	 *
	 * @param model
	 * @return lists the certified issuing units created
	 */

	@RequestMapping(value = "/certifiedissuingunit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<CertifiedIssuingUnitModelDTO> listCertifiedIssuingUnit() {
		return new CollectionResponse<>(certifiedissuingunitService.listCertifiedIssuingUnit(), CertifiedIssuingUnitModelDTO.HEADERS);
	}

	/**
	 * add certified issuing unit method
	 *
	 * @param certifiedissuingunit
	 * @return adds a new certified issuing unit in the list and redirrects to
	 *         the certified issuing unit list
	 */

	@RequestMapping(value = "/certifiedissuingunit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<CertifiedIssuingUnitModelDTO> addCertifiedIssuingUnit(@RequestBody final CertifiedIssuingUnitModelDTO crtissunitModelDTO) {
		certifiedissuingunitService.save(crtissunitModelDTO);
		return  new ItemResponse<CertifiedIssuingUnitModelDTO>(crtissunitModelDTO, CertifiedIssuingUnitModelDTO.HEADERS);
	}

	/**
	 * remove certified issuing unit method
	 *
	 * @param id
	 * @return removes a certified issuing unit from the list
	 */

	@RequestMapping(value = "/certifiedissuingunit/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeCertifiedIssuingUnit(@PathVariable("id") final int id) {
		certifiedissuingunitService.removeCertifiedIssuingUnit(id, CertifiedIssuingUnit.class);

	}

	/**
	 * edit certified issuing unit method
	 *
	 * @param id
	 * @param model
	 * @return edits an certified issuing unit based on id and loads it at the
	 *         end of the certified issuing unit list
	 */

	@RequestMapping(value = "/certifiedissuingunit/{id}", method = RequestMethod.PUT, 
	consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<CertifiedIssuingUnitModelDTO> editCertifiedIssuingUnit(@RequestBody final CertifiedIssuingUnitModelDTO crtissunitModelDTO) {
		certifiedissuingunitService.editCertifiedIssuingUnit(crtissunitModelDTO);
		return new ItemResponse<CertifiedIssuingUnitModelDTO>(crtissunitModelDTO, CertifiedIssuingUnitModelDTO.HEADERS);
	}

}