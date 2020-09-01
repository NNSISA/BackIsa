package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Surgery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Set<Doctor> doctors = new HashSet<Doctor>();

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private HospitalRoom hospitalRoom;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private MedicalRecord medicalRecord;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Clinic clinic;

	@Column(nullable = false)
	private String date;
	@Column(nullable = false)
	private String description;
	@Column(name = "patient", nullable = true)
	private String patient;
	@Column(name = "duration", nullable = false)
	private long duration;

	@Version
	private Long version;
	/*
	 * Zbog resavanja konfliktnih situacija ubaceno je polje @Version. Koristi se
	 * optimisticko zakljucavanje. Nakon svake izmene se povecava.
	 */

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public void setDoctor(Set<Doctor> doctor) {
		this.doctors = doctor;
	}

	public Set<Doctor> getDoctor() {
		return doctors;
	}

	public HospitalRoom getHospitalRoom() {
		return hospitalRoom;
	}

	public void setHospitalRoom(HospitalRoom hospitalRoom) {
		this.hospitalRoom = hospitalRoom;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Surgery() {
	}

	public Surgery(Long id, Set<Doctor> doctor, HospitalRoom hospitalRoom, MedicalRecord medicalRecord, String date,
			String description, String patient, long duration) {
		this.id = id;
		this.doctors = doctor;
		this.hospitalRoom = hospitalRoom;
		this.medicalRecord = medicalRecord;
		this.date = date;
		this.description = description;
		this.patient = patient;
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Surgery{" + "id=" + id + ", doctor=" + doctors + ", hospitalRoom=" + hospitalRoom + ", medicalRecord="
				+ medicalRecord + ", date='" + date + '\'' + ", description='" + description + '\'' + ", patient='"
				+ patient + '\'' + ", duration=" + duration + '}';
	}
}
