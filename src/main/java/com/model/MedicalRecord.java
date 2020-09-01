package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

//Kreiranje tabele
@Entity
public class MedicalRecord {

	//Generisanje kljuca
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Patient patient;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Surgery> surgeries = new HashSet<>();

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RequestAppointment> requestAppointments = new HashSet<>();

	@Column(nullable = true)
	private String diopter;
	@Column(nullable = true)
	private String height;
	@Column(nullable = true)
	private String weight;
	@Column(nullable = true)
	private String bloodType;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Set<Surgery> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(Set<Surgery> surgeries) {
		this.surgeries = surgeries;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Set<RequestAppointment> getRequestAppointments() {
		return requestAppointments;
	}

	public void setRequestAppointments(Set<RequestAppointment> requestAppointments) {
		this.requestAppointments = requestAppointments;
	}

	public void addRequestAppointment(RequestAppointment ra) {
		this.requestAppointments.add(ra);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiopter() {
		return diopter;
	}

	public void setDiopter(String diopter) {
		this.diopter = diopter;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	@Override
	public String toString() {
		return "MedicalRecord{" + "id=" + id + ", patient=" + patient + ", diopter='" + diopter + '\'' + ", height='"
				+ height + '\'' + ", weight='" + weight + '\'' + ", bloodType='" + bloodType + '\'' + ", surgeries="
				+ surgeries + ", appointments=" + appointments + ", requestAppointments=" + requestAppointments + '}';
	}
}
