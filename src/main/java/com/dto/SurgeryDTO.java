package com.dto;

public class SurgeryDTO {

	private Long id;
	private String date = "";
	private String description = "";
	private String patient = "";
	private String doctorSurgery = "";
	private int duration = 2;
	private Long roomID;

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

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getDoctorSurgery() {
		return doctorSurgery;
	}

	public void setDoctorSurgery(String doctorSurgery) {
		this.doctorSurgery = doctorSurgery;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Long getRoomID() {
		return roomID;
	}

	public void setRoomID(Long roomID) {
		this.roomID = roomID;
	}

	@Override
	public String toString() {
		return "SurgeryDTO{" + "id=" + id + ", date='" + date + '\'' + ", description='" + description + '\''
				+ ", patient='" + patient + '\'' + ", doctorSurgery='" + doctorSurgery + '\'' + ", duration=" + duration
				+ ", roomID=" + roomID + '}';
	}
}
