package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "categorieeveniment_reglementari", schema = "gnm")
public class EventCategory_Regulations extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "idcategorieeveniment")
	private EventCategory eventcategory;

	@ManyToOne
	@JoinColumn(name = "idreglementari")
	private Regulations regulations;

	public EventCategory getEventcategory() {
		return this.eventcategory;
	}

	public void setEventcategory(final EventCategory eventcategory) {
		this.eventcategory = eventcategory;
	}

	public Regulations getRegulations() {
		return this.regulations;
	}

	public void setRegulations(final Regulations regulations) {
		this.regulations = regulations;
	}

	@Override
	public String toString() {
		return "EventCategory_Regulations [eventcategory=" + this.eventcategory + ", regulations=" + this.regulations
				+ "]";
	}

}
