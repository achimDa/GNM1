package com.servustech.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "unitateemitenta", schema = "gnm")
public class IssuingUnit extends BaseEntity {

	private String denumire;
	private String nrInregistrare;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "issuingunit")
	private List<Agency> agencys;

	public List<Agency> getAgencys() {
		return this.agencys;
	}

	public void setAgencys(final List<Agency> agencys) {
		this.agencys = agencys;
	}

	public String getDenumire() {
		return this.denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	public String getNrInregistrare() {
		return this.nrInregistrare;
	}

	public void setNrInregistrare(final String nrInregistrare) {
		this.nrInregistrare = nrInregistrare;
	}

	@Override
	public String toString() {
		return "IssuingUnit [denumire=" + this.denumire + ", nrInregistrare=" + this.nrInregistrare + "]";
	}

}
