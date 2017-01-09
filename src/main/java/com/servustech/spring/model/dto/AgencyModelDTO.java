package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class AgencyModelDTO implements Serializable {
	
	public static final List<String> HEADERS = Arrays.asList("logo","cui","unitateEmitantaId","secoetateId");
			
	private Byte[]	logo;
	private String	cui;
	private int 	id;
	private int 	unitateEmitantaId;
	private int 	secoetateId;
	
	public Byte[] getLogo() {
		return logo;
	}
	public void setLogo(Byte[] logo) {
		this.logo = logo;
	}
	public String getCui() {
		return cui;
	}
	public void setCui(String cui) {
		this.cui = cui;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUnitateEmitantaId() {
		return unitateEmitantaId;
	}
	public void setUnitateEmitantaId(int unitateEmitantaId) {
		this.unitateEmitantaId = unitateEmitantaId;
	}
	public int getSecoetateId() {
		return secoetateId;
	}
	public void setSecoetateId(int secoetateId) {
		this.secoetateId = secoetateId;
	}
	
	
	
	
}
