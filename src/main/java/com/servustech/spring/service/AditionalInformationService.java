package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.AditionalInformation;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.AditionalInformationModelDTO;

public interface AditionalInformationService {

	void addAditionalInformation(BaseEntity aditionalinformation);

	void save(AditionalInformationModelDTO aditionalinformationModelDTO);

	void updateAditionalInformation(BaseEntity aditionalinformation);

	List<AditionalInformation> listAditionalInformation(String aditionalinformationName);

	List<AditionalInformationModelDTO> listAditionalInformation(int objectID);

	Object getAditionalInformationById(int id, Class<? extends BaseEntity> clazz);

	void removeAditionalInformation(int id, Class<? extends BaseEntity> clazz);

	void editAditionalInformation(AditionalInformationModelDTO aditionalinformationModelDTO);
}
