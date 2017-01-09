package com.servustech.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "harta", schema = "gnm")
public class Map extends BaseEntity {

	private String denumire;
	private String fisier;
	private String descriere;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "map")
	private List<ProtectedArea_Has_Map> area_has_map;

	public List<ProtectedArea_Has_Map> getArea_has_map() {
		return this.area_has_map;
	}

	public void setArea_has_map(final List<ProtectedArea_Has_Map> area_has_map) {
		this.area_has_map = area_has_map;
	}

	public String getDenumire() {
		return this.denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	public String getFisier() {
		return this.fisier;
	}

	public void setFisier(final String fisier) {
		this.fisier = fisier;
	}

	public String getDescriere() {
		return this.descriere;
	}

	public void setDescriere(final String descriere) {
		this.descriere = descriere;
	}

	@Override
	public String toString() {
		return "Map [denumire=" + this.denumire + ", fisier=" + this.fisier + ", descriere=" + this.descriere + "]";
	}

}
