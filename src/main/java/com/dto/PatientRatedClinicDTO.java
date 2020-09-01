package com.dto;

public class PatientRatedClinicDTO {

	private ClinicDTO clinicDTO;
	private PatientDTO patientDTO;
	private String patientUsername;
	private String clinicName;
	private int ocena;

	public ClinicDTO getClinicDTO() {
		return clinicDTO;
	}

	public PatientDTO getPatientDTO() {
		return patientDTO;
	}

	public String getPatientUsername() {
		return patientUsername;
	}

	public String getClinicName() {
		return clinicName;
	}

	public int getOcena() {
		return ocena;
	}

	public void setClinicDTO(ClinicDTO clinicDTO) {
		this.clinicDTO = clinicDTO;
	}

	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}

	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public PatientRatedClinicDTO() {
	}

	public PatientRatedClinicDTO(ClinicDTO clinicDTO, PatientDTO patientDTO, String patientUsername, String clinicName,
			int ocena) {
		this.clinicDTO = clinicDTO;
		this.patientDTO = patientDTO;
		this.patientUsername = patientUsername;
		this.clinicName = clinicName;
		this.ocena = ocena;
	}

}
