/**
 *
 */
package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author user
 *
 */
public class RegulationsModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("checkmark", "tip", "titlu", "numar", "Continut");

	private boolean checkmark;
	private String tip;
	private String titlu;
	private int numar;
	private String continut;
	private int eventCategoryId;
	private int id;

	public boolean isCheckmark() {
		return this.checkmark;
	}

	public void setCheckmark(final boolean checkmark) {
		this.checkmark = checkmark;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(final String tip) {
		this.tip = tip;
	}

	public String getTitlu() {
		return this.titlu;
	}

	public void setTitlu(final String titlu) {
		this.titlu = titlu;
	}

	public int getNumar() {
		return this.numar;
	}

	public void setNumar(final int numar) {
		this.numar = numar;
	}

	public String getContinut() {
		return this.continut;
	}

	public void setContinut(final String continut) {
		this.continut = continut;
	}

	public int getEventCategoryId() {
		return this.eventCategoryId;
	}

	public void setEventCategoryId(final int eventCategoryId) {
		this.eventCategoryId = eventCategoryId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

}
