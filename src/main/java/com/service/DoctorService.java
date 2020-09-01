package com.service;

import com.model.Doctor;
import com.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository dr;

	public Doctor findByUsername(String username) {
		return dr.findByUsername(username);
	}

	public List<Doctor> findByClinicId(Long id) {
		return dr.findByClinicId(id);
	}

	public Doctor findById(Long id) {
		return dr.findById(id).get();
	}

	public List<Doctor> findAll() {
		return dr.findAll();
	}

	public Doctor save(Doctor patient) {
		return dr.save(patient);

	}

	public void delete(Doctor doc) {
		dr.delete(doc);
	}

}
