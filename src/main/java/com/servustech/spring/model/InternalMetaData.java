package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "metadatainterna", schema = "gnm")
public class InternalMetaData extends BaseEntity {

	private String	externalId;
	private String	denumire;
	
	@ManyToOne
	@JoinColumn(name = "metadataHartaId")
	private MetaDataMap metadatamap;
	
	

	public MetaDataMap getMetadatamap() {
		return metadatamap;
	}

	public void setMetadatamap(MetaDataMap metadatamap) {
		this.metadatamap = metadatamap;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	@Override
	public String toString() {
		return "InternalMetaData [externalId=" + externalId + ", denumire=" + denumire + ", metadatamap=" + metadatamap
				+ "]";
	}

	

}
