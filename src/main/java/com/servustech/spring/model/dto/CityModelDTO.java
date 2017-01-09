package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class CityModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("denumire","judetId");
	

	private String	denumire;
	private int id;
	private int judetId;
	
	
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJudetId() {
		return judetId;
	}
	public void setJudetId(int judetId) {
		this.judetId = judetId;
	}
	
	
	

}
