package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "arieprotejata_has_harta", schema = "gnm")
public class ProtectedArea_Has_Map extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "arieProtejataId")
	private ProtectedArea protectedarea;

	@ManyToOne
	@JoinColumn(name = "hartaId")
	private Map map;

	public ProtectedArea getProtectedarea() {
		return protectedarea;
	}

	public void setProtectedarea(ProtectedArea protectedarea) {
		this.protectedarea = protectedarea;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "ProtectedArea_Has_Map [protectedarea=" + protectedarea + ", map=" + map + "]";
	}

	

}
