/**
 *
 */
package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "indicatoriperformanta", schema = "gnm")
public class PerformanceIndicator extends BaseEntity {

	private int minim;
	private int maxim;
	private String culoare;

	@ManyToOne
	@JoinColumn(name = "categorieevenimentid")
	private EventCategory eventcategory;

	public int getMinim() {
		return this.minim;
	}

	public void setMinim(final int minim) {
		this.minim = minim;
	}

	public int getMaxim() {
		return this.maxim;
	}

	public void setMaxim(final int maxim) {
		this.maxim = maxim;
	}

	public String getCuloare() {
		return this.culoare;
	}

	public void setCuloare(final String culoare) {
		this.culoare = culoare;
	}

	public EventCategory getEventcategory() {
		return this.eventcategory;
	}

	public void setEventcategory(final EventCategory eventcategory) {
		this.eventcategory = eventcategory;
	}

	@Override
	public String toString() {
		return "PerformanceIndicator [minim=" + this.minim + ", maxim=" + this.maxim + ", culoare=" + this.culoare
				+ ", eventcategory=" + this.eventcategory + "]";
	}

}
