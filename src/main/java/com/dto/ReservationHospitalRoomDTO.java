package com.dto;

import java.util.List;

public class ReservationHospitalRoomDTO {

	private List<Long> doctors;
	private SurgeryDTO surgery;

	public List<Long> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Long> doctors) {
		this.doctors = doctors;
	}

	public SurgeryDTO getSurgery() {
		return surgery;
	}

	public void setSurgery(SurgeryDTO surgery) {
		this.surgery = surgery;
	}

	@Override
	public String toString() {
		return "ReservationHospitalRoomDTO{" + "doctors=" + doctors + ", surgery=" + surgery + '}';
	}
}
