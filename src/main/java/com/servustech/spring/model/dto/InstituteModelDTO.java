package com.servustech.spring.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class InstituteModelDTO implements Serializable {
	public static final List<String> HEADERS = Arrays.asList("denumiresc","atributFiscal","dataInfiintare","capitalSocial","monedaCapitalSocial","codCaen"
			,"descriereCaen","domeniu","email","fax","comisarSef","formajuridica","adresaId");
	

	private String	denumiresc;
	private String	atributFiscal;
	private Date	dataInfiintare;
	private int		capitalSocial;
	private String	monedaCapitalSocial;
	private String	codCaen;
	private String	descriereCaen;
	private String	domeniu;
	private String	email;
	private String	fax;
	private String	comisarSef;
	private int 	formajuridica;
	private int 	id;
	private int 	adresaId;
	public String getDenumiresc() {
		return denumiresc;
	}
	public void setDenumiresc(String denumiresc) {
		this.denumiresc = denumiresc;
	}
	public String getAtributFiscal() {
		return atributFiscal;
	}
	public void setAtributFiscal(String atributFiscal) {
		this.atributFiscal = atributFiscal;
	}
	public Date getDataInfiintare() {
		return dataInfiintare;
	}
	public void setDataInfiintare(Date dataInfiintare) {
		this.dataInfiintare = dataInfiintare;
	}
	public int getCapitalSocial() {
		return capitalSocial;
	}
	public void setCapitalSocial(int capitalSocial) {
		this.capitalSocial = capitalSocial;
	}
	public String getMonedaCapitalSocial() {
		return monedaCapitalSocial;
	}
	public void setMonedaCapitalSocial(String monedaCapitalSocial) {
		this.monedaCapitalSocial = monedaCapitalSocial;
	}
	public String getCodCaen() {
		return codCaen;
	}
	public void setCodCaen(String codCaen) {
		this.codCaen = codCaen;
	}
	public String getDescriereCaen() {
		return descriereCaen;
	}
	public void setDescriereCaen(String descriereCaen) {
		this.descriereCaen = descriereCaen;
	}
	public String getDomeniu() {
		return domeniu;
	}
	public void setDomeniu(String domeniu) {
		this.domeniu = domeniu;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getComisarSef() {
		return comisarSef;
	}
	public void setComisarSef(String comisarSef) {
		this.comisarSef = comisarSef;
	}
	public int getFormajuridica() {
		return formajuridica;
	}
	public void setFormajuridica(int formajuridica) {
		this.formajuridica = formajuridica;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdresaId() {
		return adresaId;
	}
	public void setAdresaId(int adresaId) {
		this.adresaId = adresaId;
	}

	
	

}
