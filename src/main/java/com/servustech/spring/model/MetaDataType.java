package com.servustech.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "tipmetadata", schema = "gnm")
public class MetaDataType extends BaseEntity {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "metadatatype")
	private List<MetaDataType> metadatatype;

	public List<MetaDataType> getMetadatatype() {
		return this.metadatatype;
	}

	public void setMetadatatype(final List<MetaDataType> metadatatype) {
		this.metadatatype = metadatatype;
	}

	private String denumire;

	public String getDenumire() {
		return this.denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	@Override
	public String toString() {
		return "MetaDataType [ denumire=" + this.denumire + "]";
	}

}
