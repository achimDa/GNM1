package com.servustech.spring.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "utilizator", schema = "gnm")
public class User extends BaseEntity {

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

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "utilizator_rol", joinColumns = @JoinColumn(name = "utilizator") , inverseJoinColumns = @JoinColumn(name = "rol") , schema = "gnm")
	private Set<UserRole> roluri = new HashSet<UserRole>(0);

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

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	@Column(columnDefinition="character varying(60)")
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

	public Set<UserRole> getRoluri() {
		return this.roluri;
	}

	public void setRoluri(final Set<UserRole> roluri) {
		this.roluri = roluri;
	}

	@Override
	public String toString() {
		return "User [codLicienta=" + this.codLicienta + ", organizatie=" + this.organizatie + ", userId=" + this.userId
				+ ", parola=" + this.parola + ", nume=" + this.nume + ", prenume=" + this.prenume + ", email="
				+ this.email + ", inactiv=" + this.inactiv + ", accessWeb=" + this.accessWeb + ", accessMobil="
				+ this.accessMobil + "]";
	}

}