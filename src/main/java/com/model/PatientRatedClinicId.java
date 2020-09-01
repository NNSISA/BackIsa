package com.model;

import java.io.Serializable;

public class PatientRatedClinicId implements Serializable {

	private Long clinic;

	private Long patient;

	public Long getClinic() {
		return clinic;
	}

	public Long getPatient() {
		return patient;
	}

	public void setClinic(Long clinic) {
		this.clinic = clinic;
	}

	public void setPatient(Long patient) {
		this.patient = patient;
	}

	public PatientRatedClinicId() {
	}

	public PatientRatedClinicId(Long clinic, Long patient) {
		this.clinic = clinic;
		this.patient = patient;
	}
}
