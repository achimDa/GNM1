package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class InternalMetaDataModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("externalId","denumire","metadatamap");
	
	private String	externalId;
	private String	denumire;
	private int 	id;
	private int		metadatamap;
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
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
	public int getMetadatamap() {
		return metadatamap;
	}
	public void setMetadatamap(int metadatamap) {
		this.metadatamap = metadatamap;
	}
	

}
