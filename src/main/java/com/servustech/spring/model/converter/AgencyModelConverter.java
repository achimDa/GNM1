
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.Agency;
import com.servustech.spring.model.Institute;
import com.servustech.spring.model.IssuingUnit;
import com.servustech.spring.model.dto.AgencyModelDTO;


public class AgencyModelConverter {
	public static AgencyModelDTO toDTO(final Agency model) {
		final AgencyModelDTO agencydto = new AgencyModelDTO();
		
		agencydto.setLogo(model.getLogo());
		agencydto.setCui(model.getCui());
		agencydto.setId(model.getId());
		agencydto.setSecoetateId(model.getInstitute().getId());
		agencydto.setUnitateEmitantaId(model.getIssuingunit().getId());
		
		return agencydto;
	}

	public static List<AgencyModelDTO> toDTOList(final Collection<Agency> agencyList) {
		final List<AgencyModelDTO> agency = new ArrayList<>();
		for (final Agency agencyModel : agencyList) {
			agency.add(toDTO(agencyModel));
		}
		return agency;
	}

	public static void toEntity(final AgencyModelDTO agencymod, final Agency agencyentity) {
		final Institute institute = new Institute();
		final IssuingUnit issunit = new IssuingUnit();

		institute.setId(agencymod.getSecoetateId());
		issunit.setId(agencymod.getUnitateEmitantaId());
		agencyentity.setInstitute(institute);
		agencyentity.setIssuingunit(issunit);
		agencyentity.setCui(agencymod.getCui());
		agencyentity.setLogo(agencymod.getLogo());
		agencyentity.setId(agencymod.getId());
		

	}

}
