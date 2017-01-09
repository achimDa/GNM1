package com.servustech.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "coridorecologic", schema = "gnm")

public class EcologicalCorridor extends BaseEntity {

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ecologicalcorridor")
	private List<EcologicalCorridor_AditionalInformation> ecologicalCorridor_AditionalInformation;

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

	public List<EcologicalCorridor_AditionalInformation> getEcologicalCorridor_AditionalInformation() {
		return this.ecologicalCorridor_AditionalInformation;
	}

	public void setEcologicalCorridor_AditionalInformation(
			final List<EcologicalCorridor_AditionalInformation> ecologicalCorridor_AditionalInformation) {
		this.ecologicalCorridor_AditionalInformation = ecologicalCorridor_AditionalInformation;
	}

	@Override
	public String toString() {
		return "EcologicalCorridor [denumirecoridor=" + this.denumirecoridor + ", numarcoridor=" + this.numarcoridor
				+ ", tipcoridor=" + this.tipcoridor + ", descrierecoridor=" + this.descrierecoridor + ", dimensiune="
				+ this.dimensiune + ", specie=" + this.specie + ", denumire=" + this.denumire + ", urmadeanimal="
				+ this.urmadeanimal + ", animalmort=" + this.animalmort + ", dela=" + this.dela + ", panala="
				+ this.panala + ", categorie=" + this.categorie + ", animalmigrator=" + this.animalmigrator
				+ ", ecologicalCorridor_AditionalInformation=" + this.ecologicalCorridor_AditionalInformation + "]";
	}

}
