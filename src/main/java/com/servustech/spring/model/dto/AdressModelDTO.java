package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdressModelDTO implements Serializable {
	public static final List<String> HEADERS=Arrays.asList("strada","numar","bloc","etaj","apartament","localitateid" );

	private String	strada;
	private String	numar;
	private String	bloc;
	private String	etaj;
	private String	apartament;
	private int		localitateid;
	private int 	id;
	public String getStrada() {
		return strada;
	}
	public void setStrada(String strada) {
		this.strada = strada;
	}
	public String getNumar() {
		return numar;
	}
	public void setNumar(String numar) {
		this.numar = numar;
	}
	public String getBloc() {
		return bloc;
	}
	public void setBloc(String bloc) {
		this.bloc = bloc;
	}
	public String getEtaj() {
		return etaj;
	}
	public void setEtaj(String etaj) {
		this.etaj = etaj;
	}
	public String getApartament() {
		return apartament;
	}
	public void setApartament(String apartament) {
		this.apartament = apartament;
	}
	public int getLocalitateid() {
		return localitateid;
	}
	public void setLocalitateid(int localitateid) {
		this.localitateid = localitateid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
