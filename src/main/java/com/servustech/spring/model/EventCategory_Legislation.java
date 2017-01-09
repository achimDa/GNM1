package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "categorieeveniment_legislatie", schema = "gnm")
public class EventCategory_Legislation extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "idcategorieeveniment")
	private EventCategory eventcategory;

	@ManyToOne
	@JoinColumn(name = "idlegislatie")
	private Legislation legislation;

	public EventCategory getEventcategory() {
		return this.eventcategory;
	}

	public void setEventcategory(final EventCategory eventcategory) {
		this.eventcategory = eventcategory;
	}

	public Legislation getLegislation() {
		return this.legislation;
	}

	public void setLegislation(final Legislation legislation) {
		this.legislation = legislation;
	}

	@Override
	public String toString() {
		return "categorieeveniment_legislatie [eventcategory=" + this.eventcategory + ", legislation="
				+ this.legislation + "]";
	}

}
