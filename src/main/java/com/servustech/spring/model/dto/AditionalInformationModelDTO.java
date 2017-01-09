package com.servustech.spring.model.dto;

import java.util.Arrays;
import java.util.List;

public class AditionalInformationModelDTO {
	public static final List<String> HEADERS = Arrays.asList("inactiv", "denumire", "descriere");

	private int id;
	private boolean inactiv;
	private String denumire;
	private String descriere;
	private int ecologicalCorridorId;

	public int getEcologicalCorridorId() {
		return this.ecologicalCorridorId;
	}

	public void setEcologicalCorridorId(final int ecologicalCorridorId) {
		this.ecologicalCorridorId = ecologicalCorridorId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public boolean isInactiv() {
		return this.inactiv;
	}

	public void setInactiv(final boolean inactiv) {
		this.inactiv = inactiv;
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

}
