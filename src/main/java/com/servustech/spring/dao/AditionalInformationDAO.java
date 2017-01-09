package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.AditionalInformation;
import com.servustech.spring.model.base.BaseEntity;

public interface AditionalInformationDAO {

	void addAditionalInformation(BaseEntity aditionalinformation);

	void updateAditionalInformation(BaseEntity aditionalinformation);

	List<AditionalInformation> listAditionalInformation(String aditionalinformationName);

	List<AditionalInformation> listAditionalInformation(int id);

	AditionalInformation getAditionalInformationById(int id, Class<? extends BaseEntity> aditionalinformationclass);

	void removeAditionalInformation(int id, final Class<? extends BaseEntity> aditionalinformationclass);
}