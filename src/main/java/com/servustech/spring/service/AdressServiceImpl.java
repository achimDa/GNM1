package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.AdressDAO;
import com.servustech.spring.model.Adress;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.AdressModelConverter;
import com.servustech.spring.model.dto.AdressModelDTO;

@Service
@Transactional

public class AdressServiceImpl implements AdressService {
	@Autowired
	private AdressDAO adressDAO;

	@Override
	public void addAdress(final BaseEntity adress) {
		this.adressDAO.addAdress(adress);
	}

	@Override
	public void updateAdress(final BaseEntity adress) {
		this.adressDAO.updateAdress(adress);
	}

	@Override
	public void save(final AdressModelDTO adressModelDTO) {
		final Adress adress = new Adress();
		AdressModelConverter.toEntity(adressModelDTO, adress);
		if (adress.getId() == 0) {
			addAdress(adress);
		} else {
			updateAdress(adress);
		}
	}

	@Override
	public List<Adress> listAdress(final String adressName) {
		return this.adressDAO.listAdress(adressName);
	}

	@Override
	public List<AdressModelDTO> listAdress(final int objectID) {
		return AdressModelConverter.toDTOList(this.adressDAO.listAdress(objectID));
	}

	@Override
	public Adress getAdressById(final int id, final Class<? extends BaseEntity> adressclass) {
		return this.adressDAO.getAdressById(id, adressclass);
	}

	@Override
	public void removeAdress(final int id, final Class<? extends BaseEntity> adressclass) {
		this.adressDAO.removeAdress(id, adressclass);
	}

	@Override
	public void editAdress(final AdressModelDTO adressModelDTO) {
		final Adress adress = getAdressById(adressModelDTO.getId(), Adress.class);

		adress.setApartament(adressModelDTO.getApartament());
		adress.setBloc(adressModelDTO.getBloc());
		adress.setEtaj(adressModelDTO.getEtaj());
		adress.setId(adressModelDTO.getId());
		adress.setNumar(adressModelDTO.getNumar());
		adress.setStrada(adressModelDTO.getStrada());

		this.adressDAO.updateAdress(adress);

	}

}