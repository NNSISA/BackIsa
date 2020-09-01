package com.service;

import com.dto.AppointmentDTO;
import com.model.Appointment;
import com.model.RequestAppointment;

import java.util.List;

public interface AppointmentServiceInterface {

	public Appointment findById(Long id);
	
	public List<Appointment> findAll();
	
	public Appointment save(Appointment appointment);
	
	public Appointment findByDate(String date);

	public Appointment setFinished(Appointment app);

	public Appointment schedule(AppointmentDTO appointment);
	
	public List<Appointment> findByFinished(Boolean finished);

	public List<Appointment> findByHospitalRoomId(Long id);

	public Appointment acceptAppointment(AppointmentDTO appointment);

	public RequestAppointment addRequestApp(AppointmentDTO appointment);
}
