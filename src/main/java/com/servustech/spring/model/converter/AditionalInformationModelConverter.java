
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.AditionalInformation;
import com.servustech.spring.model.EcologicalCorridor;
import com.servustech.spring.model.dto.AditionalInformationModelDTO;

public class AditionalInformationModelConverter {
	public static AditionalInformationModelDTO toDTO(final AditionalInformation model) {
		final AditionalInformationModelDTO aditionalinformationdto = new AditionalInformationModelDTO();

		aditionalinformationdto.setDenumire(model.getDenumire());
		aditionalinformationdto.setId(model.getId());
		aditionalinformationdto.setDescriere(model.getDescriere());
		aditionalinformationdto.setInactiv(model.isInactiv());
	
		return aditionalinformationdto;
	}

	public static List<AditionalInformationModelDTO> toDTOList(final Collection<AditionalInformation> aditionalinformationList) {
		final List<AditionalInformationModelDTO> aditionalinformation = new ArrayList<>();
		for (final AditionalInformation aditionalinformationModel : aditionalinformationList) {
			aditionalinformation.add(toDTO(aditionalinformationModel));
		}
		return aditionalinformation;
	}

	public static void toEntity(final AditionalInformationModelDTO aditionalinformationmod, final AditionalInformation aditionalinformationentity) {
		final EcologicalCorridor ecologicalcorridor = new EcologicalCorridor();

		ecologicalcorridor.setId(aditionalinformationmod.getEcologicalCorridorId());
		aditionalinformationentity.setDenumire(aditionalinformationmod.getDenumire());
		aditionalinformationentity.setDescriere(aditionalinformationmod.getDescriere());
		aditionalinformationentity.setInactiv(aditionalinformationmod.isInactiv());
		aditionalinformationentity.setId(aditionalinformationmod.getId());
	

	}

}
