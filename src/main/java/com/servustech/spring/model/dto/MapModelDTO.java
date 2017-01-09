package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MapModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("denumire","fisier","descriere");
	
	private String	denumire;
	private String	fisier;
	private String	descriere;
	private int 	id;
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public String getFisier() {
		return fisier;
	}
	public void setFisier(String fisier) {
		this.fisier = fisier;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	
	
}
