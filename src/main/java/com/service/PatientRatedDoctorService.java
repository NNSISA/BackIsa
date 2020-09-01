package com.service;

import com.model.PatientRatedDoctor;
import com.repository.PatientRatedDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientRatedDoctorService {

	@Autowired
	private PatientRatedDoctorRepository prdr;

	public List<PatientRatedDoctor> findAll() {
		return prdr.findAll();
	}
	
	public PatientRatedDoctor save(PatientRatedDoctor prd) {
		return prdr.save(prd);
	}
}
