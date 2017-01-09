package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ProtectedAreaModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("denumire","descriere","tipArieProtejataId","custodeId","responsabilitateArieProtejataId");
			
			
	private int 	id;
	private String  denumire;
	private String	descriere;
	private int 	tipArieProtejataId;
	private int 	custodeId;
	private int		responsabilitateArieProtejataId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getTipArieProtejataId() {
		return tipArieProtejataId;
	}
	public void setTipArieProtejataId(int tipArieProtejataId) {
		this.tipArieProtejataId = tipArieProtejataId;
	}
	public int getCustodeId() {
		return custodeId;
	}
	public void setCustodeId(int custodeId) {
		this.custodeId = custodeId;
	}
	public int getResponsabilitateArieProtejataId() {
		return responsabilitateArieProtejataId;
	}
	public void setResponsabilitateArieProtejataId(int responsabilitateArieProtejataId) {
		this.responsabilitateArieProtejataId = responsabilitateArieProtejataId;
	}
	
	
}
