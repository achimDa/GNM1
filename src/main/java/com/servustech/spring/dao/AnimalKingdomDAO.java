
package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.AnimalKingdom;
import com.servustech.spring.model.base.BaseEntity;

public interface AnimalKingdomDAO {

	void addAnimalKingdom(BaseEntity animalkingdom);

	void updateAnimalKingdom(BaseEntity animalkingdom);

	List<AnimalKingdom> listAnimalKingdom(String animalkingdomName);

	List<AnimalKingdom> listAnimalKingdom();

	AnimalKingdom getAnimalKingdomById(int id, Class<? extends BaseEntity> animalkingdomclass);

	void removeAnimalKingdom(int id, final Class<? extends BaseEntity> animalkingdomclass);
}