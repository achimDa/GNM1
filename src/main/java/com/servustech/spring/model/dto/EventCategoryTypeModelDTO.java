package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class EventCategoryTypeModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("descriere", "poza");

	private int id;
	private String descriere;
	private Byte[] picture;

	public Byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(final Byte[] bytes) {
		this.picture = bytes;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getDescriere() {
		return this.descriere;
	}

	public void setDescriere(final String descriere) {
		this.descriere = descriere;
	}

}
