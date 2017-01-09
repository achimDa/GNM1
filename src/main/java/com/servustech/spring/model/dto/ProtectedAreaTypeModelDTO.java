package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ProtectedAreaTypeModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("denumire");
	
	private String denumire;
	private int    id;
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
	
	

}
