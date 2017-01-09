package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.AnimalKingdom;
import com.servustech.spring.model.dto.AnimalKingdomModelDTO;

public class AnimalKingdomModelConverter {
	public static AnimalKingdomModelDTO toDTO(final AnimalKingdom model) {
		final AnimalKingdomModelDTO animalkingdomdto = new AnimalKingdomModelDTO();
		animalkingdomdto.setDenumire(model.getDenumire());
		animalkingdomdto.setCategorie(model.getCategorie());
		animalkingdomdto.setDimensiune(model.getDimensiune());
		animalkingdomdto.setSpecie(model.getSpecie());
		animalkingdomdto.setPoza(model.getPoza());
		animalkingdomdto.setId(model.getId());
		return animalkingdomdto;
	}

	public static List<AnimalKingdomModelDTO> toDTOList(final Collection<AnimalKingdom> animalkingdomList) {
		final List<AnimalKingdomModelDTO> animalkingdoms = new ArrayList<>();
		for (final AnimalKingdom animalkingdomModel : animalkingdomList) {
			animalkingdoms.add(toDTO(animalkingdomModel));
		}
		return animalkingdoms;
	}

	public static void toEntity(final AnimalKingdomModelDTO animalkingdommod, final AnimalKingdom animalkingdomentity) {

		animalkingdomentity.setCategorie(animalkingdommod.getCategorie());
		;
		animalkingdomentity.setDenumire(animalkingdommod.getDenumire());
		animalkingdomentity.setDimensiune(animalkingdommod.getDimensiune());
		animalkingdomentity.setSpecie(animalkingdommod.getSpecie());
		animalkingdomentity.setPoza(animalkingdommod.getPoza());

	}

}
