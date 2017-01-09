
package com.servustech.spring.model.dto;

import java.io.Serializable;

public class UserRoleModelDTO implements Serializable {

	private String nume;
	private String descriere;

	public String getNume() {
		return this.nume;
	}

	public void setNume(final String nume) {
		this.nume = nume;
	}

	public String getDescriere() {
		return this.descriere;
	}

	public void setDescriere(final String descriere) {
		this.descriere = descriere;
	}

}
