
package com.servustech.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "regnulanimal", schema = "gnm")
public class AnimalKingdom extends BaseEntity {

	@Column(name = "dimensiune")
	@Enumerated(EnumType.ORDINAL)
	private Dimension dimensiune;

	@Column(name = "specie")
	@Enumerated(EnumType.ORDINAL)
	private Species specie;

	private String denumire;

	@Column(name = "categorie")
	@Enumerated(EnumType.ORDINAL)
	private Category categorie;
	@Lob
	private Byte[] poza;

	public Dimension getDimensiune() {
		return this.dimensiune;
	}

	public void setDimensiune(final Dimension dimensiune) {
		this.dimensiune = dimensiune;
	}

	public Species getSpecie() {
		return this.specie;
	}

	public void setSpecie(final Species specie) {
		this.specie = specie;
	}

	public String getDenumire() {
		return this.denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	public Category getCategorie() {
		return this.categorie;
	}

	public void setCategorie(final Category categorie) {
		this.categorie = categorie;
	}

	public Byte[] getPoza() {
		return this.poza;
	}

	public void setPoza(final Byte[] poza) {
		this.poza = poza;
	}

	@Override
	public String toString() {
		return "AnimalKingdom [dimensiune=" + this.dimensiune + ", specie=" + this.specie + ", denumire="
				+ this.denumire + ", categorie=" + this.categorie + "]";
	}

}
