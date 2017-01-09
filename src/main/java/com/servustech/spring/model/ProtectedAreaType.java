package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "tiparieprotejata", schema = "gnm")
public class ProtectedAreaType extends BaseEntity {

	private String denumire;

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	@Override
	public String toString() {
		return "TipArieProtejata [denumire=" + denumire + "]";
	}

}