package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.MedicalRecord;
import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

	MedicalRecord findByPatientId(Long patientId);
	
	@Override
	Optional<MedicalRecord> findById(Long id);

}
