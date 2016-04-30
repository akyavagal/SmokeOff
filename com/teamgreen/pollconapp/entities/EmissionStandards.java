package com.teamgreen.pollconapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EmissionStandards")
public class EmissionStandards {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public EmissionStandards() {
		super();
	}

	private int id;
	private double idealValueFor_CO;
	private double idealValueFor_HC;
	private double idealValueFor_CO2;
	private double idealValueFor_O2;

	@Column(name="IDEAL_VALUE_FOR_CO" , nullable = false, columnDefinition = "double default 0.45")
	public double getIdealValueFor_CO() {
		return idealValueFor_CO;
	}

	public void setIdealValueFor_CO(double idealValueFor_CO) {
		this.idealValueFor_CO = idealValueFor_CO;
	}

	@Column(name="IDEAL_VALUE_FOR_HC" , nullable = false, columnDefinition = "double default 12.45")
	public double getIdealValueFor_HC() {
		return idealValueFor_HC;
	}

	public void setIdealValueFor_HC(double idealValueFor_HC) {
		this.idealValueFor_HC = idealValueFor_HC;
	}

	@Column(name="IDEAL_VALUE_FOR_CO2" , nullable = false, columnDefinition = "double default 1.5")
	public double getIdealValueFor_CO2() {
		return idealValueFor_CO2;
	}

	public void setIdealValueFor_CO2(double idealValueFor_CO2) {
		this.idealValueFor_CO2 = idealValueFor_CO2;
	}

	@Column(name="IDEAL_VALUE_FOR_O2" , nullable = false, columnDefinition = "double default 4.772")
	public double getIdealValueFor_O2() {
		return idealValueFor_O2;
	}

	public void setIdealValueFor_O2(double idealValueFor_O2) {
		this.idealValueFor_O2 = idealValueFor_O2;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
