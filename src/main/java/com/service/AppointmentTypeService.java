package com.service;

import com.model.AppointmentType;
import com.repository.AppointmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentTypeService {

	@Autowired
	private AppointmentTypeRepository atr;

	public List<AppointmentType> findAll() {
		return atr.findAll();
	}

	public AppointmentType findByName(String name) {
		return atr.findByName(name);
	}

	public AppointmentType save(AppointmentType appointmentType) {
		return atr.save(appointmentType);
	}
	
	public void delete(AppointmentType type) {
		atr.delete(type);
	}
}
