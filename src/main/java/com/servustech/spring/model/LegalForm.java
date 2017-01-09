package com.servustech.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "formajuridica", schema = "gnm")
public class LegalForm extends BaseEntity {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formajuridica")
	private List<Institute> institute;

	private String denumire;

	public List<Institute> getInstitute() {
		return this.institute;
	}

	public void setInstitute(final List<Institute> institute) {
		this.institute = institute;
	}

	public String getDenumire() {
		return this.denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	@Override
	public String toString() {
		return "LegalForm [ denumire=" + this.denumire + "]";
	}

}
