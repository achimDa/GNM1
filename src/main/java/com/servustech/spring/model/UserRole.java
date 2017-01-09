package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "rol", schema = "gnm")
public class UserRole extends BaseEntity {
	
	private String nume;
	private String descriere;
	
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	
	/*
	ROLE_ADMIN,
	ROLE_AREA_ADMIN,
	ROLE_PROTECTED_AREA_ADMIN,
	ROLE_RANGER,
	ROLE_AUTORISED_USER,
	ROLE_PUBLIC,
	ROLE_LOCAL_ADMIN,
	ROLE_GENERAL_ADMIN
	*/
}
