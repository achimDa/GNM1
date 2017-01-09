package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.AnimalKingdomDAO;
import com.servustech.spring.model.AnimalKingdom;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.AnimalKingdomModelConverter;
import com.servustech.spring.model.dto.AnimalKingdomModelDTO;

@Service
@Transactional

public class AnimalKingdomServiceImpl implements AnimalKingdomService {
	@Autowired
	private AnimalKingdomDAO animalkingdomDAO;

	@Override
	public void addAnimalKingdom(final BaseEntity animalkingdom) {
		this.animalkingdomDAO.addAnimalKingdom(animalkingdom);
	}

	@Override
	public void updateAnimalKingdom(final BaseEntity animalkingdom) {
		this.animalkingdomDAO.updateAnimalKingdom(animalkingdom);
	}

	@Override
	public void save(final AnimalKingdomModelDTO animalkingdomModelDTO) {
		final AnimalKingdom animalkingdom = new AnimalKingdom();
		AnimalKingdomModelConverter.toEntity(animalkingdomModelDTO, animalkingdom);
		if (animalkingdom.getId() == 0) {
			addAnimalKingdom(animalkingdom);
		} else {
			updateAnimalKingdom(animalkingdom);
		}
	}

	@Override
	public List<AnimalKingdom> listAnimalKingdom(final String animalkingdomName) {
		return this.animalkingdomDAO.listAnimalKingdom(animalkingdomName);
	}

	@Override
	public List<AnimalKingdomModelDTO> listAnimalKingdom() {
		return AnimalKingdomModelConverter.toDTOList(this.animalkingdomDAO.listAnimalKingdom());
	}

	@Override
	public AnimalKingdom getAnimalKingdomById(final int id, final Class<? extends BaseEntity> animalkingdomclass) {
		return this.animalkingdomDAO.getAnimalKingdomById(id, animalkingdomclass);
	}

	@Override
	public void removeAnimalKingdom(final int id, final Class<? extends BaseEntity> animalkingdomclass) {
		this.animalkingdomDAO.removeAnimalKingdom(id, animalkingdomclass);
	}

	@Override
	public void editAnimalKingdom(final AnimalKingdomModelDTO animalkingdomModelDTO) {
		final AnimalKingdom animalkingdom = getAnimalKingdomById(animalkingdomModelDTO.getId(), AnimalKingdom.class);

		animalkingdom.setCategorie(animalkingdomModelDTO.getCategorie());
		animalkingdom.setDenumire(animalkingdomModelDTO.getDenumire());
		animalkingdom.setDimensiune(animalkingdomModelDTO.getDimensiune());
		animalkingdom.setSpecie(animalkingdomModelDTO.getSpecie());
		animalkingdom.setPoza(animalkingdomModelDTO.getPoza());
		animalkingdom.setId(animalkingdomModelDTO.getId());

		this.animalkingdomDAO.updateAnimalKingdom(animalkingdom);

	}

	@Override
	public void addAnimalKingdomPicture(final byte[] bytes) {

	}

}