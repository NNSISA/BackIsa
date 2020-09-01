package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.ClinicalCenterAdministrator;
import java.lang.String;

public interface ClinicalCenterAdministratorRepository extends JpaRepository<ClinicalCenterAdministrator, Long> {

	Page<ClinicalCenterAdministrator> findAll(Pageable page);

	ClinicalCenterAdministrator findByUsername(String username);

}
