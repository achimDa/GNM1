package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.AnimalKingdom;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.AnimalKingdomModelDTO;

public interface AnimalKingdomService {

	void addAnimalKingdom(BaseEntity animalkingdom);

	void save(AnimalKingdomModelDTO animalkingdomModelDTO);

	void updateAnimalKingdom(BaseEntity animalkingdom);

	List<AnimalKingdom> listAnimalKingdom(String animalkingdomName);

	List<AnimalKingdomModelDTO> listAnimalKingdom();

	Object getAnimalKingdomById(int id, Class<? extends BaseEntity> clazz);

	void removeAnimalKingdom(int id, Class<? extends BaseEntity> clazz);

	void editAnimalKingdom(AnimalKingdomModelDTO animalkingdomModelDTO);

	void addAnimalKingdomPicture(byte[] bytes);
}
