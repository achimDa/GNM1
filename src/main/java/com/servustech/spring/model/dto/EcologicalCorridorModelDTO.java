package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class EcologicalCorridorModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("denumirecoridor", "numarcoridor", "tipcoridor",
			"descrierecoridor", "dimensiune", "specie", "denumire", "urma de animal", "animal mort", "de la", "pana la",
			"categorie", "animal migrator");

	private int id;
	private String denumirecoridor;
	private int numarcoridor;
	private String tipcoridor;
	private String descrierecoridor;
	private String dimensiune;
	private String specie;
	private String denumire;
	private boolean urmadeanimal;
	private boolean animalmort;
	private int dela;
	private int panala;
	private String categorie;
	private boolean animalmigrator;

	private List<AditionalInformationModelDTO> aditionalinformations;

	public List<AditionalInformationModelDTO> getAditionalinformations() {
		return this.aditionalinformations;
	}

	public void setAditionalinformation(final List<AditionalInformationModelDTO> aditionalinformations) {
		this.aditionalinformations = aditionalinformations;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getDenumirecoridor() {
		return this.denumirecoridor;

	}

	public void setDenumirecoridor(final String denumirecoridor) {
		this.denumirecoridor = denumirecoridor;
	}

	public int getNumarcoridor() {
		return this.numarcoridor;
	}

	public void setNumarcoridor(final int numarcoridor) {
		this.numarcoridor = numarcoridor;
	}

	public String getTipcoridor() {
		return this.tipcoridor;
	}

	public void setTipcoridor(final String tipcoridor) {
		this.tipcoridor = tipcoridor;
	}

	public String getDescrierecoridor() {
		return this.descrierecoridor;
	}

	public void setDescrierecoridor(final String descrierecoridor) {
		this.descrierecoridor = descrierecoridor;
	}

	public String getDimensiune() {
		return this.dimensiune;
	}

	public void setDimensiune(final String dimensiune) {
		this.dimensiune = dimensiune;
	}

	public String getSpecie() {
		return this.specie;
	}

	public void setSpecie(final String specie) {
		this.specie = specie;
	}

	public String getDenumire() {
		return this.denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	public boolean isUrmadeanimal() {
		return this.urmadeanimal;
	}

	public void setUrmadeanimal(final boolean urmadeanimal) {
		this.urmadeanimal = urmadeanimal;
	}

	public boolean isAnimalmort() {
		return this.animalmort;
	}

	public void setAnimalmort(final boolean animalmort) {
		this.animalmort = animalmort;
	}

	public int getDela() {
		return this.dela;
	}

	public void setDela(final int dela) {
		this.dela = dela;
	}

	public int getPanala() {
		return this.panala;
	}

	public void setPanala(final int panala) {
		this.panala = panala;
	}

	public String getCategorie() {
		return this.categorie;
	}

	public void setCategorie(final String categorie) {
		this.categorie = categorie;
	}

	public boolean isAnimalmigrator() {
		return this.animalmigrator;
	}

	public void setAnimalmigrator(final boolean animalmigrator) {
		this.animalmigrator = animalmigrator;
	}

}
