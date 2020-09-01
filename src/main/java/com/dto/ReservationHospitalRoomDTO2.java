package com.dto;

public class ReservationHospitalRoomDTO2 {
	private Long doctor;
	private AppointmentDTO appointment;

	public Long getDoctor() {
		return doctor;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}

	public AppointmentDTO getAppointmentDTO() {
		return appointment;
	}

	public void setAppointment(AppointmentDTO appointmentDTO) {
		this.appointment = appointmentDTO;
	}

	public ReservationHospitalRoomDTO2() {
	}

	@Override
	public String toString() {
		return "ReservationHospitalRoomDTO{" + "doctors=" + doctor + ", appointment=" + appointment + '}';
	}
}
