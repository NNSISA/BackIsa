package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import javax.persistence.*;
import javax.persistence.Version;

//U bazi kreira tabalu sa nazivom klase
@Entity
//Sistem za objektno-relaciono mapiranje
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//Naziv tabele
@Table(name = "appointments")
public class Appointment {

	//Generisanje kljuca u bazu, inkrementalno povecavanje
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//CascadeType.REFRESH - omogucava da aplikacija radi sa poslednjom verzijom entiteta iz baze
	//EAGER - ucitavanje svih veza sa objektom odmah
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Doctor doctor;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private HospitalRoom hospitalRoom;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private MedicalRecord medicalRecord;

	//CascadeType.ALL - menjanjem pregleda menjace se i recepti
	//LAZY - ucitavanje svih veza sa objektom pri ekspicitnom trazenju
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Recipe recipe;

	@ManyToOne
	@JsonIgnore
	private Diagnosis diagnosis;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private AppointmentType type2;

	//Naziv kolone
	@Column(name = "patient", nullable = true)
	private String patient;

	@Column(name = "doctorUsername", nullable = true)
	private String doctorUsername;

	@Column(name = "date", nullable = false)
	private String date;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "type", nullable = true)
	private String type;

	@Column(name = "duration", nullable = false)
	private long duration;

	@Column(name = "price", nullable = true)
	private double price;

	@Column(name = "finished", nullable = false)
	private boolean finished;

	@Column(name = "info", nullable = true)
	private String info;

	@Version
	private Long version;
	/*
	 * Zbog resavanja konfliktnih situacija ubaceno je polje @Version. Koristi se
	 * optimisticko zakljucavanje. Nakon svake izmene se povecava.
	 */

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDoctorUsername() {
		return doctorUsername;
	}

	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Appointment() {
	}

	public Appointment(Long id, String patient, String date, String description, long duration) {
		this.id = id;
		this.patient = patient;
		this.date = date;
		this.description = description;
		this.duration = duration;
	}

	public Appointment(Long id, Doctor doctor, HospitalRoom hospitalRoom, MedicalRecord medicalRecord, Recipe recipe,
			Diagnosis diagnosis, AppointmentType type2, String patient, String doctorUsername, String date,
			String description, String type, long duration, boolean finished, String info) {
		this.id = id;
		this.doctor = doctor;
		this.hospitalRoom = hospitalRoom;
		this.medicalRecord = medicalRecord;
		this.recipe = recipe;
		this.diagnosis = diagnosis;
		this.type2 = type2;
		this.patient = patient;
		this.doctorUsername = doctorUsername;
		this.date = date;
		this.description = description;
		this.type = type;
		this.duration = duration;
		this.finished = finished;
		this.info = info;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Appointment c = (Appointment) o;
		if (c.id == null || id == null) {
			return false;
		}
		return Objects.equals(id, c.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	public AppointmentType getType2() {
		return type2;
	}

	public void setType2(AppointmentType type2) {
		this.type2 = type2;
	}

	@Override
	public String toString() {
		return "Appointment{" + "id=" + id + '}';
	}

}
