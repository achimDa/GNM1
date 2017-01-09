package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class CountryModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("denumire","indicativ");
	
	private String		denumire;
	private String		indicativ;
	private int 		id;
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public String getIndicativ() {
		return indicativ;
	}
	public void setIndicativ(String indicativ) {
		this.indicativ = indicativ;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
