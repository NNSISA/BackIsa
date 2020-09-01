package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;

//Tabela
@Entity
@IdClass(PatientRatedDoctorId.class)
public class PatientRatedDoctor implements Serializable {

	//Spajanje tabela na osnovu Id
	//Spajamo Doktora i Pacijenta
	@Id
	@ManyToOne
	@JoinColumn
	private Doctor doctor;

	@JsonIgnore
	@Id
	@ManyToOne
	@JoinColumn
	private Patient patient;

	//Dodatna kolona
	@Column(nullable = true)
	private int ocena;

	public Doctor getDoctor() {
		return doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public int getOcena() {
		return ocena;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public PatientRatedDoctor() {
	}

	public PatientRatedDoctor(Doctor doctor, Patient patient, int ocena) {
		this.doctor = doctor;
		this.patient = patient;
		this.ocena = ocena;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
