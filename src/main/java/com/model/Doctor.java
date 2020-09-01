package com.model;

import com.fasterxml.jackson.annotation.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

//Kreiranje tabele doktora
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@DiscriminatorValue("1")
public class Doctor extends MedicalStaff {

	//CascadeType.REFRESH - omogucava da aplikacija radi sa poslednjom verzijom entiteta iz baze
	//LAZY - ucitavanje svih veza sa objektom pri ekspicitnom trazenju
	@JsonBackReference
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Set<Surgery> surgeries = new HashSet<Surgery>();

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RequestAppointment> requestAppointments = new HashSet<RequestAppointment>();

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Appointment> appointments = new HashSet<Appointment>();

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private Set<PatientRatedDoctor> patientRatedDoctors = new HashSet<>();

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Clinic clinic;

	@Column(name = "review", nullable = false)
	private int review;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH)
	private AppointmentType appointmentType;

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public int getReview() {
		return review;
	}

	public void setReview(int review) {
		this.review = review;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
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

	public Set<PatientRatedDoctor> getPatientRatedDoctors() {
		return patientRatedDoctors;
	}

	public void setPatientRatedDoctors(Set<PatientRatedDoctor> patientRatedDoctors) {
		this.patientRatedDoctors = patientRatedDoctors;
	}

	public Set<RequestAppointment> getRequestAppointments() {
		return requestAppointments;
	}

	public void setRequestAppointments(Set<RequestAppointment> requestAppointments) {
		this.requestAppointments = requestAppointments;
	}

	public Doctor() {
	}

	public Doctor(Set<Surgery> surgeries, Set<Appointment> appointments, int review) {
		super();
		this.surgeries = surgeries;
		this.appointments = appointments;
		this.review = review;
	}

}
