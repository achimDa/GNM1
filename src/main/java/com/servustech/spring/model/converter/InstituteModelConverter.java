
package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.Institute;
import com.servustech.spring.model.Adress;
import com.servustech.spring.model.LegalForm;
import com.servustech.spring.model.dto.InstituteModelDTO;

public class InstituteModelConverter {
	public static InstituteModelDTO toDTO(final Institute model) {
		final InstituteModelDTO institutedto = new InstituteModelDTO();
		
		
		institutedto.setAdresaId(model.getAdress().getId());
		institutedto.setFormajuridica(model.getFormajuridica().getId());
		institutedto.setAtributFiscal(model.getAtributFiscal());
		institutedto.setCapitalSocial(model.getCapitalSocial());
		institutedto.setCodCaen(model.getCodCaen());
		institutedto.setComisarSef(model.getComisarSef());
		institutedto.setDataInfiintare(model.getDataInfiintare());
		institutedto.setDenumiresc(model.getDenumiresc());
		institutedto.setDescriereCaen(model.getDescriereCaen());
		institutedto.setDomeniu(model.getDomeniu());
		institutedto.setEmail(model.getEmail());
		institutedto.setFax(model.getFax());
		institutedto.setId(model.getId());
		institutedto.setMonedaCapitalSocial(model.getMonedaCapitalSocial());
				
		return institutedto;
	}

	public static List<InstituteModelDTO> toDTOList(final Collection<Institute> instituteList) {
		final List<InstituteModelDTO> institute = new ArrayList<>();
		for (final Institute instituteModel : instituteList) {
			institute.add(toDTO(instituteModel));
		}
		return institute;
	}

	public static void toEntity(final InstituteModelDTO institutemod, final Institute instituteentity) {
		final Adress adress = new Adress();
		final LegalForm legalform = new LegalForm();
		
		adress.setId(institutemod.getAdresaId());
		instituteentity.setAdress(adress);
		
		legalform.setId(institutemod.getFormajuridica());
		instituteentity.setFormajuridica(legalform);
		
		instituteentity.setAtributFiscal(institutemod.getAtributFiscal());
		instituteentity.setCapitalSocial(institutemod.getCapitalSocial());
		instituteentity.setCodCaen(institutemod.getCodCaen());
		instituteentity.setComisarSef(institutemod.getComisarSef());
		instituteentity.setDataInfiintare(institutemod.getDataInfiintare());
		instituteentity.setDenumiresc(institutemod.getDenumiresc());
		instituteentity.setDescriereCaen(institutemod.getDescriereCaen());
		instituteentity.setDomeniu(institutemod.getDomeniu());
		instituteentity.setEmail(institutemod.getEmail());
		instituteentity.setFax(institutemod.getFax());
		instituteentity.setId(institutemod.getId());
		instituteentity.setMonedaCapitalSocial(institutemod.getMonedaCapitalSocial());
		
		
	}

}
