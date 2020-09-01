package com.model;

import java.io.Serializable;

public class PatientRatedDoctorId implements Serializable {

	private Long doctor;
	private Long patient;

	public Long getDoctor() {
		return doctor;
	}

	public Long getPatient() {
		return patient;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}

	public void setPatient(Long patient) {
		this.patient = patient;
	}

	public PatientRatedDoctorId() {
	}

	public PatientRatedDoctorId(Long doctor, Long patient) {
		this.doctor = doctor;
		this.patient = patient;
	}
}
