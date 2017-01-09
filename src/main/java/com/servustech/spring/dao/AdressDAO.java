package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.Adress;
import com.servustech.spring.model.base.BaseEntity;

public interface AdressDAO {

	void addAdress(BaseEntity adress);

	void updateAdress(BaseEntity adress);

	List<Adress> listAdress(String adressName);

	List<Adress> listAdress(int id);

	Adress getAdressById(int id, Class<? extends BaseEntity> adressclass);

	void removeAdress(int id, final Class<? extends BaseEntity> adressclass);
}