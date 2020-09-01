package com.repository;

import com.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

	Diagnosis save(Diagnosis diagnosis);
}
