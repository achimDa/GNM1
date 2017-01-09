package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MetaDataMapModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("idHarta","tipMetadataId","numar","etc");
	
	private int 	id;
	private int		idHarta;
	private int		tipMetadataId;
	private int 	numar;
	private String 	etc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdHarta() {
		return idHarta;
	}
	public void setIdHarta(int idHarta) {
		this.idHarta = idHarta;
	}
	public int getTipMetadataId() {
		return tipMetadataId;
	}
	public void setTipMetadataId(int tipMetadataId) {
		this.tipMetadataId = tipMetadataId;
	}
	public int getNumar() {
		return numar;
	}
	public void setNumar(int numar) {
		this.numar = numar;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
	
	

}
