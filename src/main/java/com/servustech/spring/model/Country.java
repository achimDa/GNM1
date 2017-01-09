package com.servustech.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "tara", schema = "gnm")
public class Country extends BaseEntity {

	private String		denumire;

	private String		indicativ;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	private List<Area>	areas;

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	public String getIndicativ() {
		return indicativ;
	}

	public void setIndicativ(final String indicativ) {
		this.indicativ = indicativ;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(final List<Area> areas) {
		this.areas = areas;
	}

	@Override
	public String toString() {
		return "id=" + id + ", denumire=" + denumire + ", indicativ=" + indicativ;
	}
}