
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.Legislation;
import com.servustech.spring.model.dto.LegislationModelDTO;

public class LegislationModelConverter {
	public static LegislationModelDTO toDTO(final Legislation model) {
		final LegislationModelDTO legislationdto = new LegislationModelDTO();

		legislationdto.setContinut(model.getContinut());
		legislationdto.setId(model.getId());
		legislationdto.setNumar(model.getNumar());
		legislationdto.setCheckmark(model.isCheckmark());
		legislationdto.setTip(model.getTip());
		legislationdto.setTitlu(model.getTitlu());

		return legislationdto;
	}

	public static List<LegislationModelDTO> toDTOList(final Collection<Legislation> legislationList) {
		final List<LegislationModelDTO> legislations = new ArrayList<>();
		for (final Legislation legislationModel : legislationList) {
			legislations.add(toDTO(legislationModel));
		}
		return legislations;
	}

	public static void toEntity(final LegislationModelDTO legislationmod, final Legislation legislationentity) {
		final EventCategory eventcategory = new EventCategory();

		eventcategory.setId(legislationmod.getEventCategoryId());
		legislationentity.setCheckmark(legislationmod.isCheckmark());
		legislationentity.setContinut(legislationmod.getContinut());
		legislationentity.setId(legislationmod.getId());
		legislationentity.setNumar(legislationmod.getNumar());
		legislationentity.setTip(legislationmod.getTip());
		legislationentity.setTitlu(legislationmod.getTitlu());

	}

}
