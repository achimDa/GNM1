
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.EventCategory;
import com.servustech.spring.model.PerformanceIndicator;
import com.servustech.spring.model.dto.PerformanceIndicatorModelDTO;

public class PerformanceIndicatorModelConverter {
	public static PerformanceIndicatorModelDTO toDTO(final PerformanceIndicator model) {
		final PerformanceIndicatorModelDTO performanceindicatordto = new PerformanceIndicatorModelDTO();

		performanceindicatordto.setCuloare(model.getCuloare());
		performanceindicatordto.setId(model.getId());
		performanceindicatordto.setMaxim(model.getMaxim());
		performanceindicatordto.setMinim(model.getMinim());

		return performanceindicatordto;
	}

	public static List<PerformanceIndicatorModelDTO> toDTOList(
			final Collection<PerformanceIndicator> performanceindicatorList) {
		final List<PerformanceIndicatorModelDTO> performanceindicators = new ArrayList<>();
		for (final PerformanceIndicator performanceindicatorModel : performanceindicatorList) {
			performanceindicators.add(toDTO(performanceindicatorModel));
		}
		return performanceindicators;
	}

	public static void toEntity(final PerformanceIndicatorModelDTO legislationmod,
			final PerformanceIndicator legislationentity) {
		final EventCategory eventcategory = new EventCategory();
		//
		// eventcategory.setId(legislationmod.getCategorieevenimentid());
		// legislationentity.setCheckmark(legislationmod.isCheckmark());
		// legislationentity.setContinut(legislationmod.getContinut());
		// legislationentity.setId(legislationmod.getId());
		// legislationentity.setNumar(legislationmod.getNumar());
		// legislationentity.setTip(legislationmod.getTip());
		// legislationentity.setTitlu(legislationmod.getTitlu());
		//
	}

}
