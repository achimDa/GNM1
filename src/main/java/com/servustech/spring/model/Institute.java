package com.servustech.spring.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "societate", schema = "gnm")
public class Institute extends BaseEntity {

	private String denumiresc;
	private String atributFiscal;
	private Date dataInfiintare;
	private int capitalSocial;
	private String monedaCapitalSocial;
	private String codCaen;
	private String descriereCaen;
	private String domeniu;
	private String email;
	private String fax;
	private String comisarSef;

	@ManyToOne
	@JoinColumn(name = "formaJuridicaId")
	private LegalForm formajuridica;

	@ManyToOne
	@JoinColumn(name = "adresaId")
	private Adress adress;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institute")
	private List<Agency> agency;

	public LegalForm getFormajuridica() {
		return this.formajuridica;
	}

	public void setFormajuridica(final LegalForm formajuridica) {
		this.formajuridica = formajuridica;
	}

	public Adress getAdress() {
		return this.adress;
	}

	public void setAdress(final Adress adress) {
		this.adress = adress;
	}

	public List<Agency> getAgency() {
		return this.agency;
	}

	public void setAgency(final List<Agency> agency) {
		this.agency = agency;
	}

	public String getDenumiresc() {
		return this.denumiresc;
	}

	public void setDenumiresc(final String denumiresc) {
		this.denumiresc = denumiresc;
	}

	public String getAtributFiscal() {
		return this.atributFiscal;
	}

	public void setAtributFiscal(final String atributFiscal) {
		this.atributFiscal = atributFiscal;
	}

	public Date getDataInfiintare() {
		return this.dataInfiintare;
	}

	public void setDataInfiintare(final Date dataInfiintare) {
		this.dataInfiintare = dataInfiintare;
	}

	public int getCapitalSocial() {
		return this.capitalSocial;
	}

	public void setCapitalSocial(final int capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public String getMonedaCapitalSocial() {
		return this.monedaCapitalSocial;
	}

	public void setMonedaCapitalSocial(final String monedaCapitalSocial) {
		this.monedaCapitalSocial = monedaCapitalSocial;
	}

	public String getCodCaen() {
		return this.codCaen;
	}

	public void setCodCaen(final String codCaen) {
		this.codCaen = codCaen;
	}

	public String getDescriereCaen() {
		return this.descriereCaen;
	}

	public void setDescriereCaen(final String descriereCaen) {
		this.descriereCaen = descriereCaen;
	}

	public String getDomeniu() {
		return this.domeniu;
	}

	public void setDomeniu(final String domeniu) {
		this.domeniu = domeniu;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(final String fax) {
		this.fax = fax;
	}

	public String getComisarSef() {
		return this.comisarSef;
	}

	public void setComisarSef(final String comisarSef) {
		this.comisarSef = comisarSef;
	}

	@Override
	public String toString() {
		return "Institute [denumiresc=" + this.denumiresc + ", atributFiscal=" + this.atributFiscal
				+ ", dataInfiintare=" + this.dataInfiintare + ", capitalSocial=" + this.capitalSocial
				+ ", monedaCapitalSocial=" + this.monedaCapitalSocial + ", codCaen=" + this.codCaen + ", descriereCaen="
				+ this.descriereCaen + ", domeniu=" + this.domeniu + ", email=" + this.email + ", fax=" + this.fax
				+ ", comisarSef=" + this.comisarSef + ", formajuridica=" + this.formajuridica + ", adress="
				+ this.adress + "]";
	}

}
