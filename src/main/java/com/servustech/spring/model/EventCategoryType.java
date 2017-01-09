package com.servustech.spring.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "tipcategorieeveniment", schema = "gnm")
public class EventCategoryType extends BaseEntity {

	private String descriere;
	@Lob
	private Byte[] picture;

	public String getDescriere() {
		return this.descriere;
	}

	public void setDescriere(final String descriere) {
		this.descriere = descriere;
	}

	public Byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(final Byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "EventCategoryType [descriere=" + this.descriere + ", picture=" + Arrays.toString(this.picture) + "]";
	}

}
