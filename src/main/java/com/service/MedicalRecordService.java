package com.service;

import com.model.MedicalRecord;
import com.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordRepository mrr;

	public MedicalRecord findByPatientId(Long id) {
		return mrr.findByPatientId(id);
	}
	
	public MedicalRecord save(MedicalRecord medicalRecord) {
		return mrr.save(medicalRecord);
	}
}
