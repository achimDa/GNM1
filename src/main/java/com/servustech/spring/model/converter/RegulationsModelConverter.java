
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.Regulations;
import com.servustech.spring.model.dto.RegulationsModelDTO;

public class RegulationsModelConverter {
	public static RegulationsModelDTO toDTO(final Regulations model) {
		final RegulationsModelDTO regulationsdto = new RegulationsModelDTO();

		regulationsdto.setContinut(model.getContinut());
		regulationsdto.setId(model.getId());
		regulationsdto.setNumar(model.getNumar());
		regulationsdto.setCheckmark(model.isCheckmark());
		regulationsdto.setTip(model.getTip());
		regulationsdto.setTitlu(model.getTitlu());

		return regulationsdto;
	}

	public static List<RegulationsModelDTO> toDTOList(final Collection<Regulations> regulationsList) {
		final List<RegulationsModelDTO> regulations = new ArrayList<>();
		for (final Regulations regulationsModel : regulationsList) {
			regulations.add(toDTO(regulationsModel));
		}
		return regulations;
	}

	public static void toEntity(final RegulationsModelDTO regulationsmod, final Regulations regulationsentity) {
		final EventCategory eventcategory = new EventCategory();

		eventcategory.setId(regulationsmod.getEventCategoryId());
		regulationsentity.setCheckmark(regulationsmod.isCheckmark());
		regulationsentity.setContinut(regulationsmod.getContinut());
		regulationsentity.setId(regulationsmod.getId());
		regulationsentity.setNumar(regulationsmod.getNumar());
		regulationsentity.setTip(regulationsmod.getTip());
		regulationsentity.setTitlu(regulationsmod.getTitlu());

	}

}
