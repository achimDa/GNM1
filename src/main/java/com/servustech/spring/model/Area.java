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
@Table(name = "judet", schema = "gnm")
public class Area extends BaseEntity {

	private String denumire;
	private String indicativ;

	@ManyToOne
	@JoinColumn(name = "idtara")
	private Country country;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	private List<City> city;

	public String getDenumire() {
		return this.denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	public String getIndicativ() {
		return this.indicativ;
	}

	public void setIndicativ(final String indicativ) {
		this.indicativ = indicativ;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(final Country country) {
		this.country = country;
	}

	public List<City> getCity() {
		return this.city;
	}

	public void setCity(final List<City> city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Area [denumire=" + this.denumire + ", indicativ=" + this.indicativ + ", country=" + this.country + "]";
	}

}
