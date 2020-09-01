package com.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.ClinicAdministrator;

public interface ClinicAdministratorRepository extends JpaRepository<ClinicAdministrator, Long> {

	List<ClinicAdministrator> findByClinicId(Long id);
	
	ClinicAdministrator findByUsername(String username);

	Page<ClinicAdministrator> findAll(Pageable pageable);

}
