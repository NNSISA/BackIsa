package com.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class ConfirmationTokenRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "token_id")
	private long tokenid;
	@Column(name = "confirmation_token")
	private String confirmationToken;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@OneToOne(targetEntity = Patient.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "patient_id")
	private Patient patient;
	@Column(name = "patient_username_id", nullable = true)
	private String patientUsername;

	public String getPatientUsername() {
		return patientUsername;
	}

	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}

	public long getTokenid() {
		return tokenid;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ConfirmationTokenRegistration() {
	}

	public ConfirmationTokenRegistration(Patient patient) {
		this.confirmationToken = UUID.randomUUID().toString();
		this.createdDate = new Date();
		this.patient = patient;
	}
}
