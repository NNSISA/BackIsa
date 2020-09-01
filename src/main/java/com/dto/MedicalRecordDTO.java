package com.dto;

import com.model.MedicalRecord;

public class MedicalRecordDTO {
	
	private Long id;
	private PatientDTO patient;
	private String diopter;
	private String height;
	private String weight;
	private String bloodType;
	private String patientUsername;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public String getDiopter() {
		return diopter;
	}

	public void setDiopter(String diopter) {
		this.diopter = diopter;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getPatientUsername() {
		return patientUsername;
	}

	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}

	public MedicalRecordDTO() {
		super();
	}

	public MedicalRecordDTO(Long id, PatientDTO patient) {
		super();
		this.id = id;
		this.patient = patient;
	}

	public MedicalRecordDTO(MedicalRecord md) {
		super();
		this.id = md.getId();
		this.patient = new PatientDTO(md.getPatient());
	}

	@Override
	public String toString() {
		return "MedicalRecordDTO{" + "id=" + id + ", patient=" + patient + ", diopter='" + diopter + '\'' + ", height='"
				+ height + '\'' + ", weight='" + weight + '\'' + ", bloodType='" + bloodType + '\''
				+ ", patientUsername='" + patientUsername + '\'' + '}';
	}
}
