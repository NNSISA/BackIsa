package com.repository;

import com.model.PatientRatedClinic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientRatedClinicRepository extends JpaRepository<PatientRatedClinic, Long> {

	List<PatientRatedClinic> findAll();
}
