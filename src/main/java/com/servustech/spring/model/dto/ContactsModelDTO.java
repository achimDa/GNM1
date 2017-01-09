package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ContactsModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("nume","prenume","telefon1","fax","email","functie");
	
	private int 	id;
	private String	nume;
	private String	prenume;
	private String	telefon1;
	private String	fax;
	private String	email;
	private String	functie;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	public String getTelefon1() {
		return telefon1;
	}
	public void setTelefon1(String telefon1) {
		this.telefon1 = telefon1;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFunctie() {
		return functie;
	}
	public void setFunctie(String functie) {
		this.functie = functie;
	}


	
}
