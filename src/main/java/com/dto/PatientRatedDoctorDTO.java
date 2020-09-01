package com.dto;

public class PatientRatedDoctorDTO {

	private DoctorDTO doctorDTO;
	private PatientDTO patientDTO;
	private String patientUsername;
	private String doctorUsername;
	private int ocena;

	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}

	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}

	public String getPatientUsername() {
		return patientUsername;
	}

	public String getDoctorUsername() {
		return doctorUsername;
	}

	public int getOcena() {
		return ocena;
	}

	public void setDoctorDTO(DoctorDTO doctorDTO) {
		this.doctorDTO = doctorDTO;
	}

	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public DoctorDTO getDoctorDTO() {
		return doctorDTO;
	}

	public PatientDTO getPatientDTO() {
		return patientDTO;
	}

	public PatientRatedDoctorDTO() {
	}

	public PatientRatedDoctorDTO(DoctorDTO doctorDTO, PatientDTO patientDTO, int ocena) {
		this.doctorDTO = doctorDTO;
		this.patientDTO = patientDTO;
		this.ocena = ocena;
	}
}
