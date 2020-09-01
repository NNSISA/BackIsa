package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.ClinicalCenter;

public interface ClinicalCenterRepository extends JpaRepository<ClinicalCenter, Long> {

	List<ClinicalCenter> findByName(String name);

}
