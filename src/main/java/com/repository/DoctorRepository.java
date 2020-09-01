package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Doctor;
import java.lang.String;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	Doctor findByUsername(String username);

	List<Doctor> findAll();

	List<Doctor> findByClinicId(Long id);

	Optional<Doctor> findById(Long id);
	
	List<Doctor> findByFirstName(String firstname);
}
