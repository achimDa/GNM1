package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class EventModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("categorie","dimensiune","specie","animal","descriere","idcategorieeveniment");
	
	private String categorie;
	private String dimensiune;
	private String specie;
	private String animal;
	private String descriere;
	private int    id;
	private int	   idcategorieeveniment;
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getDimensiune() {
		return dimensiune;
	}
	public void setDimensiune(String dimensiune) {
		this.dimensiune = dimensiune;
	}
	public String getSpecie() {
		return specie;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdcategorieeveniment() {
		return idcategorieeveniment;
	}
	public void setIdcategorieeveniment(int idcategorieeveniment) {
		this.idcategorieeveniment = idcategorieeveniment;
	}
	
	
	
	

}
