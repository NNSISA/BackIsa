package com.service;

import com.model.RequestAppointment;
import com.repository.RequestAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestAppointmentService {

	@Autowired
	private RequestAppointmentRepository rar;

	public RequestAppointment findById(Long id) {
		return rar.findById(id).get();
	}
	
	public List<RequestAppointment> findAll() {
		return rar.findAll();
	}
	
	public List<RequestAppointment> findByClinicId(Long id) {
		return rar.findByClinicId(id);
	}
	
	public RequestAppointment save(RequestAppointment appointment) {
		return rar.save(appointment);
	}

	public void delete(RequestAppointment appointment) {
		rar.delete(appointment);
	}
}
