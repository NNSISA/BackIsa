package com.repository;

import com.model.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Long> {
	
	AppointmentType findByName(String name);
}
