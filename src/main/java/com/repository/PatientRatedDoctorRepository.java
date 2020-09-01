package com.repository;

import com.model.PatientRatedDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientRatedDoctorRepository extends JpaRepository<PatientRatedDoctor, Long> {

	List<PatientRatedDoctor> findAll();

}
