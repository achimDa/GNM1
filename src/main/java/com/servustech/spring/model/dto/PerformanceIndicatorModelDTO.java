/**
 *
 */
package com.servustech.spring.model.dto;

import java.io.Serializable;

public class PerformanceIndicatorModelDTO implements Serializable {
	private int id;
	private int categorieevenimentid;
	private int minim;
	private int maxim;
	private String culoare;

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getCategorieevenimentid() {
		return this.categorieevenimentid;
	}

	public void setCategorieevenimentid(final int categorieevenimentid) {
		this.categorieevenimentid = categorieevenimentid;
	}

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

}
