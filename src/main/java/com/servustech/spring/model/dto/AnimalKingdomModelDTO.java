/**
 *
 */
package com.servustech.spring.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.servustech.spring.model.Category;
import com.servustech.spring.model.Dimension;
import com.servustech.spring.model.Species;

public class AnimalKingdomModelDTO implements Serializable {

	@Column(name = "dimensiune")
	@Enumerated(EnumType.ORDINAL)
	private Dimension dimensiune;

	@Column(name = "specie")
	@Enumerated(EnumType.ORDINAL)
	private Species specie;

	private int id;
	private String denumire;
	private boolean animalMigrator;

	@Column(name = "categorie")
	@Enumerated(EnumType.ORDINAL)
	private Category categorie;
	@Lob
	private Byte[] poza;

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public boolean isAnimalMigrator() {
		return this.animalMigrator;
	}

	public void setAnimalMigrator(final boolean animalMigrator) {
		this.animalMigrator = animalMigrator;
	}

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

}
