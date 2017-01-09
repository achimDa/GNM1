package com.servustech.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "arieprotejata", schema = "gnm")
public class ProtectedAreaResponsability extends BaseEntity {

	private String denumire;
	private String descriere;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "protectedarearesp")
	private List<ProtectedAreaResponsability> protectedarearesp;

	public List<ProtectedAreaResponsability> getProtectedarearesp() {
		return this.protectedarearesp;
	}

	public void setProtectedarearesp(final List<ProtectedAreaResponsability> protectedarearesp) {
		this.protectedarearesp = protectedarearesp;
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

	@Override
	public String toString() {
		return "ProtectedAreaResponsability [denumire=" + this.denumire + ", descriere=" + this.descriere + "]";
	}

}
