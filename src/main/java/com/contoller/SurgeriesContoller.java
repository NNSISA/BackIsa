package com.contoller;

import com.dto.AvailableHospitalRoomDTO;
import com.dto.ReservationHospitalRoomDTO;
import com.dto.SurgeryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SurgeriesContoller {

	@Autowired
	private MedicalRecordService mrs;
	@Autowired
	private SurgeryService ss;
	@Autowired
	private ClinicAdministratorService cas;
	@Autowired
	private HospitalRoomService hrs;
	@Autowired
	private DoctorService ds;
	@Autowired
	private AppointmentService as;
	@Autowired
	private EmailService es;
	@Autowired
	private PatientService ps;

	//Vraca listu operacija
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getSurgeries/{username}", method = RequestMethod.GET)
	public List<Surgery> getSurgeries(@PathVariable String username) {
		return ss.findAll();
	}

	//Dodavanje operacije u karton pacijenta
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/new-surgery", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<SurgeryDTO> addSurgery(@RequestBody SurgeryDTO surgeryDTO) {
		Surgery surgery = new Surgery();
		surgery.setDate(surgeryDTO.getDate());
		surgery.setPatient(surgeryDTO.getPatient());
		surgery.setDescription(surgeryDTO.getDescription());
		Doctor doctor = null;
		Clinic clinic = null;
		try {
			doctor = ds.findByUsername(surgeryDTO.getDoctorSurgery());
			clinic = doctor.getClinic();
		} catch (Exception e) {

		}
		if (doctor != null) {
			Set<Surgery> surgeries = doctor.getSurgeries();
			Set<Appointment> appointments = doctor.getAppointments();
			boolean available = true;
			for (Surgery s : surgeries) {
				if (available) {
					if (s.getDate().equals(surgery.getDate())) {
						available = false;
					}
				}
			}
			for (Appointment a : appointments) {
				if (available) {
					if (a.getDate().equals(surgery.getDate())) {
						available = false;
					}
				}
			}
			surgery.setClinic(clinic);
			surgery.setDuration(2);
			if (available)
				surgery.getDoctor().add(doctor);
			Surgery surgeriesave = ss.save(surgery);

			try {
				Patient patient = ps.findByUsername(surgeryDTO.getPatient());
				MedicalRecord mr = mrs.findByPatientId(patient.getId());
				mr.getSurgeries().add(surgeriesave);
				MedicalRecord mr2 = mrs.save(mr);
			} catch (Exception e) {
				System.out.println("Operaciju nije moguce dodati u kartom pacijenta!");
			}
		}
		return new ResponseEntity<>(surgeryDTO, HttpStatus.OK);
	}

	// Prosledjujemo administratora klinike,
	// Uzimamo samo one operacije koje su vezane za tu kliniku
	@CrossOrigin(origins = "http//localhost:4200")
	@RequestMapping(value = "/api/surgeries-res-rooms/{cadmin}", method = RequestMethod.GET)
	public List<Surgery> getSurgeriesInClinic(@PathVariable String cadmin) {
		List<Surgery> surgeries = new ArrayList<>();
		List<Surgery> ret = new ArrayList<Surgery>();
		ClinicAdministrator ca;
		try {
			ca = cas.findByUsername(cadmin);
		} catch (Exception e) {
			return null;
		}
		if (ca != null) {
			surgeries = ss.findByClinicId(ca.getClinic().getId());
			for (Surgery s : surgeries) {
				// Uzimamo one operacije koji nije dodeljena sala
				if (s.getHospitalRoom() == null)
					ret.add(s);
			}
		}
		return ret;
	}
	
	//Cekiranje sobe za operaciju
	@CrossOrigin(origins = "http//localhost:4200")
	@RequestMapping(value = "/api/availableRooms", method = RequestMethod.POST)
	public List<HospitalRoom> availableRooms(@RequestBody SurgeryDTO surgeryDTO) throws ParseException {
		List<HospitalRoom> ret = new ArrayList<>();
		Surgery surgery = null;
		try {
			surgery = ss.findById(surgeryDTO.getId());
		} catch (Exception e) {
			return null;
		}

		Clinic clinic = surgery.getClinic();
		// Uzimamo milisekunde kad je zakazano
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date date = dateFormat.parse(surgeryDTO.getDate());
		// Prolazimo kroz sve sale i gledamo koja je slobodna u tom trenutku
		List<HospitalRoom> allRooms = hrs.findByClinicId(surgery.getClinic().getId());
		for (HospitalRoom hospitalRoom : allRooms) {
			boolean nadjenaOperacijaKojaJeUTomTerminu = false;
			// Proveravamo da li je zakazana neka operacija tada
			List<Surgery> roomSurgeries = ss.findByHospitalId(hospitalRoom.getId());
			for (Surgery s : roomSurgeries) {
				if (nadjenaOperacijaKojaJeUTomTerminu == false) {
					// poredim datum moje operacije i datum operacije u ovom for-u
					if (surgery.getDate().equals(s.getDate())) {
						nadjenaOperacijaKojaJeUTomTerminu = true;
					}
				}
			}

			List<Appointment> appsRoom = this.as.findByHospitalRoomId(hospitalRoom.getId());
			for (Appointment appointment : appsRoom) {
				if (nadjenaOperacijaKojaJeUTomTerminu == false) {
					// Provaravamo datum moje operacije i datum operacije, ako je 0 onda su jednaki
					if (surgery.getDate().equals(appointment.getDate())) {
						// Da li se poklapaju satnice
						nadjenaOperacijaKojaJeUTomTerminu = true;
					}
				}
			}

			if (!nadjenaOperacijaKojaJeUTomTerminu)
				ret.add(hospitalRoom);
			Set<Appointment> roomAppointments = hospitalRoom.getAppointments();
		}
		return ret;
	}

	//Pronalazenje doktora za operaciju
	@CrossOrigin(origins = "http//localhost:4200")
	@RequestMapping(value = "/api/availableDoctors", method = RequestMethod.POST)
	public List<Doctor> getAvailableDoctors(@RequestBody SurgeryDTO surgeryDTO) throws ParseException {
		Surgery surgery = ss.findById(surgeryDTO.getId());
		List<Doctor> doctors = this.ds.findByClinicId(surgery.getClinic().getId());
		List<Doctor> availableDoctors = new ArrayList<>();
		for (Doctor doctor : doctors) {
			boolean available = true;
			for (Surgery s : doctor.getSurgeries()) {
				if (surgeryDTO.getDate().equals(s.getDate())) {
					available = false;
				}
			}
			for (Appointment appointment : doctor.getAppointments()) {
				if (available == true) {
					if (surgeryDTO.getDate().equals(appointment.getDate())) {
						available = false;
					}
				}
			}
			if (available)
				availableDoctors.add(doctor);
		}
		return availableDoctors;
	}

	//Dodavanje sobe
	@CrossOrigin(origins = "http//localhost:4200")
	@RequestMapping(value = "/api/add-room-to-surgery", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addRoomToSurgery(@RequestBody ReservationHospitalRoomDTO reservationHospitalRoomDTO)
			throws InterruptedException {
		try {
			String text = ss.AddRoomToSurgery(reservationHospitalRoomDTO);
			if (text.equals("greska"))
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//Odredjivanje datuma za sobu
	@CrossOrigin(origins = "http//localhost:4200")
	@RequestMapping(value = "/api/available-room-other-date", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private AvailableHospitalRoomDTO availableRoomOtherDate(@RequestBody SurgeryDTO surgeryDTO) throws ParseException {
		AvailableHospitalRoomDTO ret = new AvailableHospitalRoomDTO();
		Surgery surgery = ss.findById(surgeryDTO.getId());
		List<HospitalRoom> rooms = hrs.findByClinicId(surgery.getClinic().getId());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date date = dateFormat.parse(surgery.getDate());
		long startSurgery = date.getTime();
		boolean nadjenaSoba = false;
		while (nadjenaSoba == false) {
			startSurgery = startSurgery + 2 * 60 * 60 * 1000;
			String newDate = dateFormat.format(startSurgery);
			for (HospitalRoom room : rooms) {
				if (nadjenaSoba == false) {
					List<Surgery> surgeries = ss.findByHospitalId(room.getId());
					List<Appointment> appointments = as.findByHospitalRoomId(room.getId());
					nadjenaSoba = checkTime(newDate, appointments, surgeries);
					if (nadjenaSoba) {
						ret.setDate(newDate);
						ret.setId(room.getId());
						ret.setRoom_num(room.getRoom_number());
						ret.setName(room.getName());
					}
				}
			}
		}
		return ret;
	}

	private boolean checkTime(String date, List<Appointment> appointments, List<Surgery> surgeries) {
		boolean available = true;
		for (Surgery surgery : surgeries) {
			if (available) {
				if (surgery.getDate().equals(date)) {
					available = false;
				}
			}
		}
		for (Appointment appointment : appointments) {
			if (available) {
				if (appointment.getDate().equals(date)) {
					available = false;
				}
			}
		}
		return available;
	}

	@Scheduled(cron = "${greeting.cron}")
	private void systemReservation() throws ParseException, InterruptedException {
		List<Surgery> allSurgeries = ss.findAll();
		List<Surgery> surgeriesWithoutRoom = new ArrayList<>();
		for (Surgery surgery : allSurgeries) {
			if (surgery.getHospitalRoom() == null)
				surgeriesWithoutRoom.add(surgery);
		}
		for (Surgery s : surgeriesWithoutRoom) {
			Surgery surgery = ss.findById(s.getId());
			List<HospitalRoom> rooms = hrs.findByClinicId(surgery.getClinic().getId());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date date = dateFormat.parse(surgery.getDate());
			long startSurgery = date.getTime();
			boolean nadjenaSoba = false;
			while (nadjenaSoba == false) {
				String newDate = dateFormat.format(startSurgery);
				for (HospitalRoom room : rooms) {
					if (nadjenaSoba == false) {
						List<Surgery> surgeries = ss.findByHospitalId(room.getId());
						List<Appointment> appointments = as.findByHospitalRoomId(room.getId());
						nadjenaSoba = checkTime(newDate, appointments, surgeries);
						if (nadjenaSoba) {
							s.setHospitalRoom(room);
							s.setDate(newDate);
							room.getSurgeries().add(s);
							Surgery saveS = ss.save(s);
							HospitalRoom saveHR = hrs.save(room);
							Patient patient = ps.findByUsername(s.getPatient());
							try {
								es.sendPatientNotificaition(s, patient);
							} catch (Exception e) {
							}

						} else {
							startSurgery = startSurgery + 2 * 60 * 60 * 1000;
						}
					}
				}
			}
		}
	}
}
