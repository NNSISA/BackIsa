package com.service;

import com.model.Diagnosis;
import com.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

	@Autowired
	private DiagnosisRepository dr;

	public Diagnosis save(Diagnosis diagnosis) {
		return dr.save(diagnosis);
	}

	public List<Diagnosis> findAll() {
		return dr.findAll();
	}

	public Diagnosis findById(Long id) {
		return dr.findById(id).get();
	}

}
