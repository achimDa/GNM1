package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "contacte", schema = "gnm")
public class Contacts extends BaseEntity {

	private String	nume;
	private String	prenume;
	private String	telefon1;
	private String	fax;
	private String	email;
	private String	functie;

	public String getNume() {
		return nume;
	}

	public void setNume(final String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(final String prenume) {
		this.prenume = prenume;
	}

	public String getTelefon1() {
		return telefon1;
	}

	public void setTelefon1(final String telefon1) {
		this.telefon1 = telefon1;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(final String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getFunctie() {
		return functie;
	}

	public void setFunctie(final String functie) {
		this.functie = functie;
	}

	@Override
	public String toString() {
		return "Contacte [ nume=" + nume + ", prenume=" + prenume + ", telefon1=" + telefon1 + ", fax=" + fax
				+ ", email=" + email + ", functie=" + functie + "]";
	}

}
