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
public class AreaModelDTO implements Serializable {
	public static final  List<String> HEADERS = Arrays.asList("denumire","indicativ","idtara");

	private String	denumire;
	private String	indicativ;
	private int		idtara;
	private int		id;

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

	public int getIdtara() {
		return idtara;
	}

	public void setIdtara(final int idtara) {
		this.idtara = idtara;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

}
