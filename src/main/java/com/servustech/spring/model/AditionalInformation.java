package com.servustech.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "informatiiaditionale", schema = "gnm")

public class AditionalInformation extends BaseEntity {
	
	private boolean inactiv;
	private String denumire;
	private String descriere;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aditionalinformation", cascade = CascadeType.ALL)
	private List<EcologicalCorridor_AditionalInformation> ecologicalcorridor_Aditionalinformation = new ArrayList<>();
	
	
	
	public List<EcologicalCorridor_AditionalInformation> getEcologicalcorridor_Aditionalinformation() {
		return ecologicalcorridor_Aditionalinformation;
	}
	public void setEcologicalcorridor_Aditionalinformation(
			List<EcologicalCorridor_AditionalInformation> ecologicalcorridor_Aditionalinformation) {
		this.ecologicalcorridor_Aditionalinformation = ecologicalcorridor_Aditionalinformation;
	}
	public boolean isInactiv() {
		return inactiv;
	}
	public void setInactiv(boolean inactiv) {
		this.inactiv = inactiv;
	}
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	@Override
	public String toString() {
		return "AditionalInformation [inactiv=" + inactiv + ", denumire=" + denumire + ", descriere=" + descriere + "]";
	}
	
	

}
