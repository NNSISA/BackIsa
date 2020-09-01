package com.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//Kreiranje tabele ClinicalCenter
@Entity
public class ClinicalCenter {

	//Inkrementalno dodeljivanje vrednosti kljuca
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//CascadeType.ALL - menjanjem pregleda menjace se i recepti
	//LAZY - ucitavanje svih veza sa objektom pri ekspicitnom trazenju
	@OneToMany(mappedBy = "clinicalCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Patient> patients = new HashSet<Patient>();

	//CascadeType.ALL - menjanjem pregleda menjace se i recepti
	//LAZY - ucitavanje svih veza sa objektom pri ekspicitnom trazenju
	@OneToMany(mappedBy = "clinicalCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Clinic> clinics = new HashSet<Clinic>();

	@OneToMany(mappedBy = "clinicalCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ClinicalCenterAdministrator> administrators = new HashSet<ClinicalCenterAdministrator>();

	@Column(name = "username", nullable = false)
	private String name;

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

}
