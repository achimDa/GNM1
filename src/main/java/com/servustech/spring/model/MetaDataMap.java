package com.servustech.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "metadataharta", schema = "gnm")
public class MetaDataMap extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "idHarta")
	private Map map;

	@ManyToOne
	@JoinColumn(name = "tipMetaData")
	private MetaDataType metadatatype;

	private int numar;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "metadatamap")
	private List<InternalMetaData> internalmetadata;

	public Map getMap() {
		return this.map;
	}

	public void setMap(final Map map) {
		this.map = map;
	}

	public MetaDataType getMetadatatype() {
		return this.metadatatype;
	}

	public void setMetadatatype(final MetaDataType metadatatype) {
		this.metadatatype = metadatatype;
	}

	public List<InternalMetaData> getInternalmetadata() {
		return this.internalmetadata;
	}

	public void setInternalmetadata(final List<InternalMetaData> internalmetadata) {
		this.internalmetadata = internalmetadata;
	}

	private String etc;

	public int getNumar() {
		return this.numar;
	}

	public void setNumar(final int numar) {
		this.numar = numar;
	}

	public String getEtc() {
		return this.etc;
	}

	public void setEtc(final String etc) {
		this.etc = etc;
	}

	@Override
	public String toString() {
		return "MetaDataMap [numar=" + this.numar + ", etc=" + this.etc + "]";
	}

}
