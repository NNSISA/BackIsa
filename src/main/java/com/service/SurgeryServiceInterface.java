package com.service;

import com.dto.ReservationHospitalRoomDTO;
import com.model.Surgery;
import java.util.List;

public interface SurgeryServiceInterface {

	public Surgery findById(Long id);

	public List<Surgery> findAll();

	public List<Surgery> findByClinicId(Long id);

	public List<Surgery> findByMedicalRecordId(Long id);

	public List<Surgery> findByHospitalId(Long id);

	public Surgery save(Surgery surgery);

	public void AddRoomToSurgery(ReservationHospitalRoomDTO reservationHospitalRoomDTO);
}
