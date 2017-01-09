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
@Table(name = "localitate", schema = "gnm")
public class City extends BaseEntity {

	private String	denumire;
	
	@ManyToOne
	@JoinColumn(name = "judetId")
	private Area area;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
	private List<Adress> adresses;
	
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public List<Adress> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adress> adresses) {
		this.adresses = adresses;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	@Override
	public String toString() {
		return "City [denumire=" + denumire + ", area=" + area +  "]";
	}

}
