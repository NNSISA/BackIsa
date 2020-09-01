package com.service;

import com.model.PatientRatedClinic;
import com.repository.PatientRatedClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientRatedClinicService {

	@Autowired
	private PatientRatedClinicRepository prcr;

	public PatientRatedClinic save(PatientRatedClinic prclinic) {
		return prcr.save(prclinic);
	}

	public List<PatientRatedClinic> findAll() {
		return prcr.findAll();
	}
}
