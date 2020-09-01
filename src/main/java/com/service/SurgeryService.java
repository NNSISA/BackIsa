package com.service;

import com.dto.ReservationHospitalRoomDTO;
import com.model.Doctor;
import com.model.HospitalRoom;
import com.model.Patient;
import com.model.Surgery;
import com.repository.SurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SurgeryService {

	@Autowired
	private SurgeryRepository sr;
	@Autowired
	private DoctorService ds;
	@Autowired
	private HospitalRoomService hrs;
	@Autowired
	private PatientService ps;
	@Autowired
	private SurgeryService ss;
	@Autowired
	private EmailService es;

	public Surgery findById(Long id) {
		return sr.findById(id).get();
	}

	public List<Surgery> findAll() {
		return sr.findAll();
	}

	public Surgery save(Surgery surgery) {
		return sr.save(surgery);
	}

	public List<Surgery> findByMedicalRecordId(Long id) {
		return sr.findByMedicalRecordId(id);
	}

	public List<Surgery> findByClinicId(Long id) {
		return sr.findByClinicId(id);
	}

	public List<Surgery> findByHospitalId(Long id) {
		return sr.findByHospitalRoomId(id);
	}

	//rollbackFor oznacava za koje izuzetke ce se desiti roolback
	//REQUIRED prikljucuje metodu transakciji ili otvara novu ako transakcija ne postoji
	//READ_UNCOMMITTED eliminise problem poslednje izmene
	//Slanje mail-a pacijentu u vezi operacije
	@Transactional(rollbackFor = {
			RuntimeException.class }, readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public String AddRoomToSurgery(ReservationHospitalRoomDTO reservationHospitalRoomDTO) {
		HospitalRoom hospitalRoom = this.hrs.findById(reservationHospitalRoomDTO.getSurgery().getRoomID());
		Surgery surgery = this.ss.findById(reservationHospitalRoomDTO.getSurgery().getId());
		surgery.setHospitalRoom(hospitalRoom);
		surgery.setDate(reservationHospitalRoomDTO.getSurgery().getDate());
		for (Long id : reservationHospitalRoomDTO.getDoctors()) {
			surgery.getDoctor().add(this.ds.findById(id));
		}
		Surgery s = this.ss.save(surgery);
		for (Long id : reservationHospitalRoomDTO.getDoctors()) {
			try {
				s = addDoctorsToSurgery(id, s);
			} catch (Exception e) {
				return "greska";
			}
		}
		Patient patient = ps.findByUsername(s.getPatient());
		try {
			es.sendPatientNotificaition(s, patient);
		} catch (Exception e) {
		}
		hospitalRoom.getSurgeries().add(s);
		HospitalRoom hospitalRoom1 = this.hrs.save(hospitalRoom);
		return "ok";
	}

	//rollbackFor oznacava za koje izuzetke ce se desiti roolback
	//REQUIRED prikljucuje metodu transakciji ili otvara novu ako transakcija ne postoji
	//READ_UNCOMMITTED eliminise problem poslednje izmene
	//Slanje mail-a doktoru u vezi operacije
	@Transactional(rollbackFor = {
			RuntimeException.class }, readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public Surgery addDoctorsToSurgery(Long id, Surgery s) {
		Doctor doctor = this.ds.findById(id);
		doctor.getSurgeries().add(s);
		Doctor d = this.ds.save(doctor);
		try {
			es.sendDoctorNotificaition(s, doctor);
		} catch (Exception e) {
		}
		return s;
	}
}
