package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "comisariat", schema = "gnm")
public class Agency extends BaseEntity {

	private Byte[] logo;
	private String cui;

	@ManyToOne
	@JoinColumn(name = "societateid")
	private Institute institute;

	@ManyToOne
	@JoinColumn(name = "unitateemitentaid")
	private IssuingUnit issuingunit;

	public IssuingUnit getIssuingunit() {
		return this.issuingunit;
	}

	public void setIssuingunit(final IssuingUnit issuingunit) {
		this.issuingunit = issuingunit;
	}

	public Byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(final Byte[] logo) {
		this.logo = logo;
	}

	public String getCui() {
		return this.cui;
	}

	public void setCui(final String cui) {
		this.cui = cui;
	}

	public Institute getInstitute() {
		return this.institute;
	}

	public void setInstitute(final Institute institute) {
		this.institute = institute;
	}

	@Override
	public String toString() {
		return "Agency [ cui=" + this.cui + ", institute=" + this.institute + ", issuingunit=" + this.issuingunit + "]";
	}

}
