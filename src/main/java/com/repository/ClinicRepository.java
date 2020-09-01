package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

	Clinic findByName(String name);
	
	List<Clinic> findAllByName(String name);

}
