package com.servustech.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "categorieeveniment", schema = "gnm")
public class EventCategory extends BaseEntity {

	private String denumire;
	private String descriere;
	private String simbol;
	private boolean info;
	private boolean coridoareecologice;
	private int prioritate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventcategory")
	private List<Event> event;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventcategory")
	private List<EventCategory_Legislation> eventCategory_Legislations;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventcategory")
	private List<EventCategory_Regulations> eventCategory_Regulations;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventcategory")
	private List<PerformanceIndicator> performanceindicator;

	public List<PerformanceIndicator> getPerformanceindicator() {
		return this.performanceindicator;
	}

	public void setPerformanceindicator(final List<PerformanceIndicator> performanceindicator) {
		this.performanceindicator = performanceindicator;
	}

	public List<EventCategory_Regulations> getEventCategory_Regulations() {
		return this.eventCategory_Regulations;
	}

	public void setEventCategory_Regulations(final List<EventCategory_Regulations> eventCategory_Regulations) {
		this.eventCategory_Regulations = eventCategory_Regulations;
	}

	public List<EventCategory_Legislation> getEventCategory_Legislations() {
		return this.eventCategory_Legislations;
	}

	public void setEventCategory_Legislations(final List<EventCategory_Legislation> eventCategory_Legislations) {
		this.eventCategory_Legislations = eventCategory_Legislations;
	}

	public List<Event> getEvent() {
		return this.event;
	}

	public void setEvent(final List<Event> event) {
		this.event = event;
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

	public String getSimbol() {
		return this.simbol;
	}

	public void setSimbol(final String simbol) {
		this.simbol = simbol;
	}

	public boolean isInfo() {
		return this.info;
	}

	public void setInfo(final boolean info) {
		this.info = info;
	}

	public boolean isCoridoareecologice() {
		return this.coridoareecologice;
	}

	public void setCoridoareecologice(final boolean coridoareecologice) {
		this.coridoareecologice = coridoareecologice;
	}

	public int getPrioritate() {
		return this.prioritate;
	}

	public void setPrioritate(final int prioritate) {
		this.prioritate = prioritate;
	}

	@Override
	public String toString() {
		return "EventCategory [denumire=" + this.denumire + ", descriere=" + this.descriere + ", simbol=" + this.simbol
				+ ", info=" + this.info + ", coridoareecologice=" + this.coridoareecologice + ", prioritate="
				+ this.prioritate + ", event=" + this.event + "]";
	}

}
