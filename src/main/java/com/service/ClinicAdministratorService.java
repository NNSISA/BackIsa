package com.service;

import com.model.ClinicAdministrator;
import com.repository.ClinicAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClinicAdministratorService {

	@Autowired
	private ClinicAdministratorRepository car;

	public ClinicAdministrator findByUsername(String username) {
		return car.findByUsername(username);
	}

	public List<ClinicAdministrator> findByClinicId(Long id) {
		return car.findByClinicId(id);
	}
	
	public ClinicAdministrator save(ClinicAdministrator clinicAdministrator) {
		return car.save(clinicAdministrator);
	}
}
