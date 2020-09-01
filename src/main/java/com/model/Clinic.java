package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Kreiramo tabelu u bazi sa nazivom klase
@Entity
public class Clinic {

	//Inkrementalno dodeljivanje vrednosti kljuca
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//EAGER - ucitavanje svih veza sa objektom odmah
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ClinicalCenter clinicalCenter;

	//CascadeType.REFRESH - omogucava da aplikacija radi sa poslednjom verzijom entiteta iz baze
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)

	private Set<PriceList> priceList = new HashSet<PriceList>();

	//CascadeType.REFRESH - omogucava da aplikacija radi sa poslednjom verzijom entiteta iz baze
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)

	private Set<RequestAppointment> requestAppointments = new HashSet<RequestAppointment>();

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)

	private Set<ClinicAdministrator> clinicAdministrator = new HashSet<ClinicAdministrator>();

	//EAGER - ucitavanje svih veza sa objektom odmah
	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

	private Set<Doctor> doctors = new HashSet<Doctor>();

	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

	private Set<Nurse> nurses = new HashSet<Nurse>();

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY)
	private Set<HospitalRoom> hospitalRooms = new HashSet<HospitalRoom>();

	@JsonIgnore
	@OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
	private Set<PatientRatedClinic> patientRatedClinic = new HashSet<>();

	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "pricelist", nullable = false)
	private int pricelist;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "profit", nullable = false)
	private int profit;
	@Column(name = "rating", nullable = false)
	private int rating;
	@Column(name = "longitude", nullable = true)
	private double longitude;
	@Column(name = "lat", nullable = true)
	private double lat;
	@Version
	private Long version;
	/*
	 * Zbog resavanja konfliktnih situacija ubaceno je polje @Version. Koristi se
	 * optimisticko zakljucavanje. Nakon svake izmene se povecava.
	 */

	public int getRating() {
		return rating;
	}

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Surgery> surgeries = new HashSet<Surgery>();

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPricelist() {
		return pricelist;
	}

	public void setPricelist(int pricelist) {
		this.pricelist = pricelist;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public Set<ClinicAdministrator> getClinicAdministrator() {
		return clinicAdministrator;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public void setClinicAdministrator(Set<ClinicAdministrator> clinicAdministrator) {
		this.clinicAdministrator = clinicAdministrator;
	}

	public void setPatientRatedClinic(Set<PatientRatedClinic> patientRatedClinic) {
		this.patientRatedClinic = patientRatedClinic;
	}

	public Set<PatientRatedClinic> getPatientRatedClinic() {
		return patientRatedClinic;
	}

	public ClinicalCenter getClinicalCenter() {
		return clinicalCenter;
	}

	public void setClinicalCenter(ClinicalCenter clinicalCenter) {
		this.clinicalCenter = clinicalCenter;
	}

	public Set<HospitalRoom> getHospitalRooms() {
		return hospitalRooms;
	}

	public void setHospitalRooms(Set<HospitalRoom> hospitalRooms) {
		this.hospitalRooms = hospitalRooms;
	}

	public Set<PriceList> getPriceList() {
		return priceList;
	}

	public void setPriceList(Set<PriceList> priceList) {
		this.priceList = priceList;
	}

	public Set<RequestAppointment> getRequestAppointments() {
		return requestAppointments;
	}

	public void setRequestAppointments(Set<RequestAppointment> requestAppointments) {
		this.requestAppointments = requestAppointments;
	}

	public Set<Surgery> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(Set<Surgery> surgeries) {
		this.surgeries = surgeries;
	}

	public Set<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(Set<Nurse> nurses) {
		this.nurses = nurses;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public Clinic() {
	}
}
