package com.service;

import com.dto.ClinicDTO;
import com.model.Clinic;

import java.util.List;

public interface ClinicServiceInterface {

	public List<Clinic> findAll();

	public Clinic findById(long id);

	public Clinic findByName(String name);
	
	public Clinic save(Clinic clinic);

	public void delete(Clinic clinic);

	public Clinic changeInfo(ClinicDTO newClinic, String name);
}
