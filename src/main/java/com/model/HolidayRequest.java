package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

//Kreiranje tabele za odmor
@Entity
public class HolidayRequest {

	//Inkrementalno dodeljivanje vrednosti kljuca
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Kolone
	@Column(nullable = false)
	private String dateStart;
	@Column(nullable = false)
	private String dateEnd;
	@Column(nullable = true)
	private boolean confirmed;
	@Column(nullable = true)
	private boolean finished;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private MedicalStaff medicalStaff;

	@Version
	private Long version;
	/*
	 * Zbog resavanja konfliktnih situacija ubaceno je polje @Version. Koristi se
	 * optimisticko zakljucavanje. Nakon svake izmene se povecava.
	 */

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HolidayRequest() {
	}

	public String getDateStart() {
		return dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public MedicalStaff getMedicalStaff() {
		return medicalStaff;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setMedicalStaff(MedicalStaff medicalStaff) {
		this.medicalStaff = medicalStaff;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public String toString() {
		return "HolidayRequest{" + "id=" + id + ", dateStart='" + dateStart + '\'' + ", dateEnd='" + dateEnd + '\''
				+ ", medicalStaff=" + medicalStaff + '}';
	}
}
