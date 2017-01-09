package com.servustech.spring.model;




import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "eveniment", schema = "gnm")

public class Event extends BaseEntity {
	
	private String categorie;
	private String dimensiune;
	private String specie;
	private String animal;
	private String descriere;
	
	@ManyToOne
	@JoinColumn(name ="idcategorieeveniment")
	private EventCategory eventcategory;

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getDimensiune() {
		return dimensiune;
	}

	public void setDimensiune(String dimensiune) {
		this.dimensiune = dimensiune;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getDescriere() {
		return descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	
	public EventCategory getEventcategory() {
		return eventcategory;
	}

	public void setEventcategory(EventCategory eventcategory) {
		this.eventcategory = eventcategory;
	}

	@Override
	public String toString() {
		return "Event [categorie=" + categorie + ", dimensiune=" + dimensiune + ", specie=" + specie + ", animal="
				+ animal + ", descriere=" + descriere + ", eventcategory=" + eventcategory + "]";
	}

	
		
}
