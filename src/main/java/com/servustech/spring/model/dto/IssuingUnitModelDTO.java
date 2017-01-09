package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class IssuingUnitModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("denumire","nrInregistrare");

	
	private int 	id;
	private String	denumire;
	private String	nrInregistrare;
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
	public String getNrInregistrare() {
		return nrInregistrare;
	}
	public void setNrInregistrare(String nrInregistrare) {
		this.nrInregistrare = nrInregistrare;
	}
	
	
	
}
