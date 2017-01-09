package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class EventCategoryModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("denumire", "descriere", "simbol", "info",
			"coridoareecologice", "prioritate");

	private int id;
	private String denumire;
	private String descriere;
	private String simbol;
	private boolean info;
	private boolean coridoareecologice;
	private int prioritate;
	private List<LegislationModelDTO> legislations;
	private List<RegulationsModelDTO> regulations;

	public List<RegulationsModelDTO> getRegulations() {
		return this.regulations;
	}

	public void setRegulations(final List<RegulationsModelDTO> regulations) {
		this.regulations = regulations;
	}

	public List<LegislationModelDTO> getLegislations() {
		return this.legislations;
	}

	public void setLegislations(final List<LegislationModelDTO> legislations) {
		this.legislations = legislations;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getDenumire() {
		return this.denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	public String getDescriere() {
		return this.descriere;
	}

	public void setDescriere(final String descriere) {
		this.descriere = descriere;
	}

	public String getSimbol() {
		return this.simbol;
	}

	public void setSimbol(final String simbol) {
		this.simbol = simbol;
	}

	public boolean isInfo() {
		return this.info;
	}

	public void setInfo(final boolean info) {
		this.info = info;
	}

	public boolean isCoridoareecologice() {
		return this.coridoareecologice;
	}

	public void setCoridoareecologice(final boolean coridoareecologice) {
		this.coridoareecologice = coridoareecologice;
	}

	public int getPrioritate() {
		return this.prioritate;
	}

	public void setPrioritate(final int prioritate) {
		this.prioritate = prioritate;
	}

}
