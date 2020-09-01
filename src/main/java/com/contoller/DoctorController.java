package com.contoller;

import com.dto.PatientRatedClinicDTO;
import com.dto.PatientRatedDoctorDTO;
import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DoctorController {

	@Autowired
	private AppointmentService as;
	@Autowired
	private PatientService ps;
	@Autowired
	private PatientRatedDoctorService prds;
	@Autowired
	private MedicalStaffService mss;
	@Autowired
	private PatientRatedClinicService prcs;
	@Autowired
	private ClinicService cs;

	//Dobavljanje doktora koji se mogu ocenjivati
	//Pregledi moraju biti zavrseni
	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value = "/getDoctorsForRate/{username}", method = RequestMethod.GET)
	public List<Doctor> getDoctorsForRate(@PathVariable String username) {

		List<Appointment> pregledi = as.findAll();
		List<Doctor> doctors = new ArrayList<>();
		for (Appointment app : pregledi) {
			if (app.getPatient() != null) {
				if (app.getPatient().equals(username) && app.isFinished()) {
					doctors.add(app.getDoctor());
				}
			}
		}
		return doctors;
	}

	//Ocenjivanje
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getRate/{username}", method = RequestMethod.GET)
	public List<PatientRatedDoctor> getRate(@PathVariable("username") String username) {

		List<Appointment> pregledi = as.findAll();
		List<Doctor> doctors = new ArrayList<>();
		List<Doctor> doctorsFromPrd = new ArrayList<>();
		List<PatientRatedDoctor> ret = new ArrayList<>();
		for (Appointment app : pregledi) {
			if (app.getPatient() != null) {
				if (app.getPatient().equals(username) && app.isFinished()) {
					doctors.add(app.getDoctor());
				}
			}
		}
		Patient patient = ps.findByUsername(username);
		List<PatientRatedDoctor> prd = prds.findAll();
		for (PatientRatedDoctor p1 : prd) {
			if (p1.getPatient() != null) {
				if (p1.getPatient().getUsername().equals(username)) {
					doctorsFromPrd.add(p1.getDoctor());
				}
			}
		}
		for (Doctor doc : doctors) {
			if (!doctorsFromPrd.contains(doc)) {
				PatientRatedDoctor noviPrd = new PatientRatedDoctor();
				noviPrd.setOcena(0);
				noviPrd.setPatient(patient);
				noviPrd.setDoctor(doc);
				prds.save(noviPrd);
			}
		}
		List<PatientRatedDoctor> prd1 = prds.findAll();
		for (PatientRatedDoctor p : prd1) {
			if (p.getPatient().getUsername().equals(username)) {
				ret.add(p);
			}
		}
		return ret;
	}

	//Dobavljanje klinika koje se mogu ocenjivati
	//Pregledi moraju biti gotovi
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getClinicsForRate/{username}", method = RequestMethod.GET)
	public List<PatientRatedClinic> getClinicsForRate(@PathVariable("username") String username) {

		List<Appointment> pregledi = as.findAll();
		List<Clinic> clinics = new ArrayList<>();
		List<Clinic> clinicsFromPrclinic = new ArrayList<>();
		List<PatientRatedClinic> ret = new ArrayList<>();
		Patient patient = ps.findByUsername(username);
		for (Appointment app : pregledi) {
			if (app.getPatient() != null) {
				if (app.getPatient().equals(username) && app.isFinished()) {
					clinics.add(app.getDoctor().getClinic());
				}
			}
		}

		List<PatientRatedClinic> prclinic = prcs.findAll();
		for (PatientRatedClinic prclin : prclinic) {
			if (prclin.getPatient() != null) {
				if (prclin.getPatient().getUsername().equals(username)) {
					clinicsFromPrclinic.add(prclin.getClinic());
				}
			}
		}
		for (Clinic c : clinics) {
			if (!clinicsFromPrclinic.contains(c)) {
				PatientRatedClinic noviPrclinic = new PatientRatedClinic();
				noviPrclinic.setOcena(0);
				noviPrclinic.setPatient(patient);
				noviPrclinic.setClinic(c);
				prcs.save(noviPrclinic);
			}
		}

		List<PatientRatedClinic> prclinic1 = prcs.findAll();
		for (PatientRatedClinic p : prclinic1) {
			if (p.getPatient().getUsername().equals(username)) {
				ret.add(p);
			}
		}
		return ret;
	}

	//Promena ocena doktora
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/rateChange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PatientRatedDoctorDTO> rateChange(
			@RequestBody PatientRatedDoctorDTO patientRatedDoctorDTO) {
		List<PatientRatedDoctor> prd = prds.findAll();
		Doctor doctor = (Doctor) mss.findByUsername(patientRatedDoctorDTO.getDoctorUsername());
		int suma = 0;
		int kolikoIhIma = 0;
		for (PatientRatedDoctor p : prd) {
			if (p.getPatient().getUsername().equals(patientRatedDoctorDTO.getPatientUsername())) {
				if (p.getDoctor().getUsername().equals(patientRatedDoctorDTO.getDoctorUsername())) {
					p.setOcena(patientRatedDoctorDTO.getOcena());
					prds.save(p);
				}
			}
		}
		for (PatientRatedDoctor p : prd) {
			if (p.getDoctor().getUsername().equals(patientRatedDoctorDTO.getDoctorUsername())) {
				suma += p.getOcena();
				kolikoIhIma++;
			}
		}
		doctor.setReview(suma / kolikoIhIma);
		mss.save(doctor);
		return new ResponseEntity<>(patientRatedDoctorDTO, HttpStatus.OK);
	}

	//Promena ocena klinike
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/rateChangeClinic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PatientRatedClinicDTO> rateChangeClinic(
			@RequestBody PatientRatedClinicDTO patientRatedClinicDTO) {
		List<PatientRatedClinic> prclinic = prcs.findAll();
		Clinic clinic = cs.findByName(patientRatedClinicDTO.getClinicName());
		int suma = 0;
		int kolikoIhIma = 0;
		for (PatientRatedClinic p : prclinic) {
			if (p.getPatient().getUsername().equals(patientRatedClinicDTO.getPatientUsername())) {
				if (p.getClinic().getName().equals(patientRatedClinicDTO.getClinicName())) {
					p.setOcena(patientRatedClinicDTO.getOcena());
					prcs.save(p);
				}
			}
		}

		for (PatientRatedClinic p : prclinic) {
			if (p.getClinic().getName().equals(patientRatedClinicDTO.getClinicName())) {
				suma += p.getOcena();
				kolikoIhIma++;
			}
		}
		clinic.setRating(suma / kolikoIhIma);
		cs.save(clinic);
		return new ResponseEntity<>(patientRatedClinicDTO, HttpStatus.OK);
	}
}
