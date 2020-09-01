package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Patient;
import java.lang.String;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByUsername(String username);

	Patient findByUsernameIgnoreCase(String username);
	
	List<Patient> findByFirstName(String firstname);
}
