package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Patient;
import com.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository pr;
	
	public List<Patient> findAll() {
		return pr.findAll();
	}
	
	public Patient findByUsername(String username) {
		return pr.findByUsername(username);
	}
	
	public Patient save(Patient patient) {
		return pr.save(patient);
	}
}
