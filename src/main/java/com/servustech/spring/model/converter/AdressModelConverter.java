package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.Adress;
import com.servustech.spring.model.City;
import com.servustech.spring.model.dto.AdressModelDTO;

public class AdressModelConverter {
	public static AdressModelDTO toDTO(final Adress model) {
		final AdressModelDTO adressdto = new AdressModelDTO();
		adressdto.setId(model.getId());
		adressdto.setLocalitateid(model.getCity().getId());
		adressdto.setApartament(model.getApartament());
		adressdto.setBloc(model.getBloc());
		adressdto.setEtaj(model.getEtaj());
		adressdto.setNumar(model.getNumar());
		adressdto.setStrada(model.getStrada());
		return adressdto;
	}

	public static List<AdressModelDTO> toDTOList(final Collection<Adress> adressList) {
		final List<AdressModelDTO> adress = new ArrayList<>();
		for (final Adress adressModel : adressList) {
			adress.add(toDTO(adressModel));
		}
		return adress;
	}

	public static void toEntity(final AdressModelDTO adressmod, final Adress adressentity) {
		final City city = new City();

		city.setId(adressmod.getLocalitateid());
		adressentity.setCity(city);
		adressentity.setApartament(adressmod.getApartament());
		adressentity.setBloc(adressmod.getBloc());
		adressentity.setEtaj(adressmod.getEtaj());
		adressentity.setId(adressmod.getId());
		adressentity.setNumar(adressmod.getNumar());
		adressentity.setStrada(adressmod.getStrada());

	}

}