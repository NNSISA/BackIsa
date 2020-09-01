package com.contoller;

import com.dto.MedicalRecordDTO;
import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MedicalRecordController {

	@Autowired
	private AppointmentService as;
	@Autowired
	private PatientService ps;
	@Autowired
	private MedicalRecordService mrs;

	//Zakazivanje pregleda
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/get-patient-appointments/{username}/{doctor}", method = RequestMethod.GET)
	private List<Appointment> getAppointments(@PathVariable("username") String username,
			@PathVariable("doctor") String doctor) throws ParseException {
		List<Appointment> apps = as.findAll();
		List<Appointment> ret = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(new Date());

		for (Appointment app : apps) {
			Date date = dateFormat.parse(app.getDate());
			long milis = date.getTime();
			String newDate = dateFormat.format(milis);
			try {
				if (app.getPatient().equals(username) && app.getDoctorUsername().equals(doctor) && newDate.equals(today)
						&& app.getHospitalRoom() != null && app.isFinished() == false) {
					ret.add(app);
				}
			} catch (Exception e) {
				System.out.println("Moraju se popuniti sva polja!");
			}
		}
		return ret;
	}

	//Informacije o medicinskom izvestaju
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-medical-record-info/{username}", method = RequestMethod.GET)
	private MedicalRecordDTO getMedicalRecordInfo(@PathVariable String username) {
		MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
		Patient patient = ps.findByUsername(username);
		MedicalRecord medicalRecord = mrs.findByPatientId(patient.getId());
		medicalRecordDTO.setBloodType(medicalRecord.getBloodType());
		medicalRecordDTO.setDiopter(medicalRecord.getDiopter());
		medicalRecordDTO.setHeight(medicalRecord.getHeight());
		medicalRecordDTO.setWeight(medicalRecord.getWeight());
		medicalRecordDTO.setPatientUsername(username);
		return medicalRecordDTO;
	}

	//Dodavanje informacija o medicinskom izvestaju
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/set-medical-record-info", method = RequestMethod.POST)
	private MedicalRecordDTO setMedicalRecordInfo(@RequestBody MedicalRecordDTO medicalRecordDTO) {
		Patient patient = ps.findByUsername(medicalRecordDTO.getPatientUsername());
		MedicalRecord medicalRecord = mrs.findByPatientId(patient.getId());
		medicalRecord.setBloodType(medicalRecordDTO.getBloodType());
		medicalRecord.setDiopter(medicalRecordDTO.getDiopter());
		medicalRecord.setHeight(medicalRecordDTO.getHeight());
		medicalRecord.setWeight(medicalRecordDTO.getWeight());
		medicalRecordDTO.setId(medicalRecord.getId());
		MedicalRecord md = mrs.save(medicalRecord);
		return medicalRecordDTO;
	}
}
