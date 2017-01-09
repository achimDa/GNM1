/**
 *
 */
package com.servustech.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "reglementari", schema = "gnm")

public class Regulations extends BaseEntity {

	private boolean checkmark;
	private String tip;
	private String titlu;
	private int numar;
	private String continut;

	// @ManyToMany(cascade = CascadeType.ALL)
	// @JoinTable(name = "CategorieEveniment_Has_Legislatie", joinColumns =
	// @JoinColumn(name = "idLegislatie") , inverseJoinColumns =
	// @JoinColumn(name = "idCategorieEveniment") )
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "regulations", cascade = CascadeType.ALL)
	private List<EventCategory_Regulations> eventCategory_Regulations = new ArrayList<>();

	public List<EventCategory_Regulations> getEventCategory_Regulations() {
		return this.eventCategory_Regulations;
	}

	public void setEventCategory_Regulations(final List<EventCategory_Regulations> eventCategory_Regulations) {
		this.eventCategory_Regulations = eventCategory_Regulations;
	}

	public boolean isCheckmark() {
		return this.checkmark;
	}

	public void setCheckmark(final boolean checkmark) {
		this.checkmark = checkmark;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(final String tip) {
		this.tip = tip;
	}

	public String getTitlu() {
		return this.titlu;
	}

	public void setTitlu(final String titlu) {
		this.titlu = titlu;
	}

	public int getNumar() {
		return this.numar;
	}

	public void setNumar(final int numar) {
		this.numar = numar;
	}

	public String getContinut() {
		return this.continut;
	}

	public void setContinut(final String continut) {
		this.continut = continut;
	}

	@Override
	public String toString() {
		return "Legislation [checkmark=" + this.checkmark + ", tip=" + this.tip + ", titlu=" + this.titlu + ", numar="
				+ this.numar + ", continut=" + this.continut + "]";
	}

}
