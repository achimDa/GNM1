package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "adresa", schema = "gnm")
public class Adress extends BaseEntity {

	private String strada;
	private String numar;
	private String bloc;
	private String etaj;
	private String apartament;

	@ManyToOne
	@JoinColumn(name = "localitateid")
	private City city;

	public City getCity() {
		return this.city;
	}

	public void setCity(final City city) {
		this.city = city;
	}

	public String getStrada() {
		return this.strada;
	}

	public void setStrada(final String strada) {
		this.strada = strada;
	}

	public String getNumar() {
		return this.numar;
	}

	public void setNumar(final String numar) {
		this.numar = numar;
	}

	public String getBloc() {
		return this.bloc;
	}

	public void setBloc(final String bloc) {
		this.bloc = bloc;
	}

	public String getEtaj() {
		return this.etaj;
	}

	public void setEtaj(final String etaj) {
		this.etaj = etaj;
	}

	public String getApartament() {
		return this.apartament;
	}

	public void setApartament(final String apartament) {
		this.apartament = apartament;
	}

	@Override
	public String toString() {
		return "Adress [strada=" + this.strada + ", numar=" + this.numar + ", bloc=" + this.bloc + ", etaj=" + this.etaj
				+ ", apartament=" + this.apartament + ", city=" + this.city + "]";
	}

}
