package com.servustech.spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servustech.spring.model.base.BaseEntity;

@Entity
@Table(name = "coridorecologic_informatiiaditionale", schema = "gnm")
public class EcologicalCorridor_AditionalInformation extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "idcoridorecologic")
	private EcologicalCorridor ecologicalcorridor;

	@ManyToOne
	@JoinColumn(name = "idinformatiiaditionale")
	private AditionalInformation aditionalinformation;

	public EcologicalCorridor getEcologicalcorridor() {
		return this.ecologicalcorridor;
	}

	public void setEcologicalcorridor(final EcologicalCorridor ecologicalcorridor) {
		this.ecologicalcorridor = ecologicalcorridor;
	}

	public AditionalInformation getAditionalinformation() {
		return this.aditionalinformation;
	}

	public void setAditionalinformation(final AditionalInformation aditionalinformation) {
		this.aditionalinformation = aditionalinformation;
	}

	@Override
	public String toString() {
		return "EcologicalCorridor_AditionalInformation [ecologicalcorridor=" + this.ecologicalcorridor
				+ ", aditionalinformation=" + this.aditionalinformation + "]";
	}

}
