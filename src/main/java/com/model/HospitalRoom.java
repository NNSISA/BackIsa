package com.model;

import com.dto.HospitalRoomDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

//Kreiranje tabele
@Entity
public class HospitalRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;
	/*
	 * Zbog resavanja konfliktnih situacija ubaceno je polje @Version. Koristi se
	 * optimisticko zakljucavanje. Nakon svake izmene se povecava.
	 */

	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Set<Surgery> surgeries = new HashSet<Surgery>();

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Appointment> appointments = new HashSet<Appointment>();

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private Clinic clinic;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int room_number;

	public HospitalRoom(HospitalRoomDTO hospitalRoom) {
		this.name = hospitalRoom.getName();
		this.room_number = hospitalRoom.getRoom_number();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoom_number() {
		return room_number;
	}

	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public HospitalRoom() {
	}

	public HospitalRoom(Long id, Set<Surgery> surgeries, Set<Appointment> appointments, Clinic clinic, String name,
			int room_number) {
		this.id = id;
		this.surgeries = surgeries;
		this.appointments = appointments;
		this.clinic = clinic;
		this.name = name;
		this.room_number = room_number;
	}

	public HospitalRoom(Long id, String name, int room_number) {
		this.id = id;
		this.name = name;
		this.room_number = room_number;
	}
}
