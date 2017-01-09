package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("userId", "parola", "nume", "prenume", "email", "inactiv");

	private int id;
	private long codLicienta;
	private String organizatie;
	private String userId;
	private String parola;
	private String nume;
	private String prenume;
	private String email;
	private Boolean inactiv;
	private Boolean accessWeb;
	private Boolean accessMobil;
	private Date dataNasterii;
	private String Roluri;

	public String getRoluri() {
		return this.Roluri;
	}

	public void setRoluri(final String set) {
		this.Roluri = set;
	}

	public long getCodLicienta() {
		return this.codLicienta;
	}

	public void setCodLicienta(final long codLicienta) {
		this.codLicienta = codLicienta;
	}

	public String getOrganizatie() {
		return this.organizatie;
	}

	public void setOrganizatie(final String organizatie) {
		this.organizatie = organizatie;
	}

	public Boolean getAccessWeb() {
		return this.accessWeb;
	}

	public void setAccessWeb(final Boolean accessWeb) {
		this.accessWeb = accessWeb;
	}

	public Boolean getAccessMobil() {
		return this.accessMobil;
	}

	public void setAccessMobil(final Boolean accessMobil) {
		this.accessMobil = accessMobil;
	}

	public Date getDataNasterii() {
		return this.dataNasterii;
	}

	public void setDataNasterii(final Date dataNasterii) {
		this.dataNasterii = dataNasterii;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getParola() {
		return this.parola;
	}

	public void setParola(final String parola) {
		this.parola = parola;
	}

	public String getNume() {
		return this.nume;
	}

	public void setNume(final String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return this.prenume;
	}

	public void setPrenume(final String prenume) {
		this.prenume = prenume;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Boolean getInactiv() {
		return this.inactiv;
	}

	public void setInactiv(final Boolean inactiv) {
		this.inactiv = inactiv;
	}

}
