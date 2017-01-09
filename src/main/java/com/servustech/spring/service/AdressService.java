package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.Adress;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.AdressModelDTO;

public interface AdressService {

	void addAdress(BaseEntity adress);

	void save(AdressModelDTO adressModelDTO);

	void updateAdress(BaseEntity adress);

	List<Adress> listAdress(String adressName);

	List<AdressModelDTO> listAdress(int objectID);

	Object getAdressById(int id, Class<? extends BaseEntity> clazz);

	void removeAdress(int id, Class<? extends BaseEntity> clazz);

	/**
	 * @param areaModelDTO
	 */
	void editAdress(AdressModelDTO adressModelDTO);
}
