package com.servustech.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "arieprotejata", schema = "gnm")
public class ProtectedArea extends BaseEntity {

	private String denumire;
	private String descriere;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "protectedarea")
	private List<ProtectedArea_Has_Map> areahasmap;

	@ManyToOne
	@JoinColumn(name = "tipArieProtejataId")
	private ProtectedAreaType protectedtype;

	@ManyToOne
	@JoinColumn(name = "responsabilitateArieProtejataId")
	private ProtectedAreaResponsability protectedarearesponsability;

	// @ManyToOne
	// @JoinColumn(name = "custodeId")
	private int custodeId;

	public List<ProtectedArea_Has_Map> getAreahasmap() {
		return this.areahasmap;
	}

	public void setAreahasmap(final List<ProtectedArea_Has_Map> areahasmap) {
		this.areahasmap = areahasmap;
	}

	public ProtectedAreaType getProtectedtype() {
		return this.protectedtype;
	}

	public void setProtectedtype(final ProtectedAreaType protectedtype) {
		this.protectedtype = protectedtype;
	}

	public ProtectedAreaResponsability getProtectedarearesponsability() {
		return this.protectedarearesponsability;
	}

	public void setProtectedarearesponsability(final ProtectedAreaResponsability protectedarearesponsability) {
		this.protectedarearesponsability = protectedarearesponsability;
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

	public int getCustodeId() {
		return this.custodeId;
	}

	public void setCustodeId(final int custodeId) {
		this.custodeId = custodeId;
	}

	@Override
	public String toString() {
		return "ProtectedArea [denumire=" + this.denumire + ", descriere=" + this.descriere + ", protectedtype="
				+ this.protectedtype + ", protectedarearesponsability=" + this.protectedarearesponsability
				+ ", custodeId=" + this.custodeId + "]";
	}

}
