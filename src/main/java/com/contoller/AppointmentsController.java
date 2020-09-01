package com.contoller;

import com.dto.AppointmentDTO;
import com.dto.AvailableHospitalRoomDTO;
import com.dto.CalendarEventsDTO;
import com.dto.ReservationHospitalRoomDTO2;
import com.dto.*;
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
import java.util.*;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentsController {

	// Najcesce koriscena notacija za povezivanje zavisnosti
	@Autowired
	private DoctorService ds;
	@Autowired
	private RequestAppointmentService ras;
	@Autowired
	private SurgeryService ss;
	@Autowired
	private ClinicAdministratorService cas;
	@Autowired
	private HospitalRoomService hrs;
	@Autowired
	public AppointmentService as;
	@Autowired
	private ClinicService cs;
	@Autowired
	private PatientService ps;
	@Autowired
	private MedicalRecordService mrs;
	@Autowired
	private EmailService es;
	@Autowired
	private MedicalStaffService mss;
	@Autowired
	private DiagnosisService dis;
	@Autowired
	private DrugService drs;
	@Autowired
	private RecipeService rs;

	@CrossOrigin(origins = "http//localhost:4200")
	// Opisuje zahtev koji treba biti obradjen u metodi
	// Sadrzi URL i tip HTTP metode
	@RequestMapping(value = "/api/availableRoomsAppointment", method = RequestMethod.POST)
	// @RequestBody ocekivani java objekat
	// Lista slobodnih soba
	public List<HospitalRoom> availableRooms(@RequestBody AppointmentDTO appointmentDTO) throws ParseException {
		return as.availableRooms(appointmentDTO);
	}

	// Administratora klinike prosledjujem kako bi znali u kojoj je on klinici,
	// i onda uzimamo samo one operacije koje su vezane za tu kliniku
	@CrossOrigin(origins = "http//localhost:4200")
	@RequestMapping(value = "/api/appointments-res-rooms/{cadmin}", method = RequestMethod.GET)
	// @PathVariable identifikacija resursa na koji se operacija odnosi
	public List<RequestAppointment> getResRoom(@PathVariable String cadmin) {
		List<RequestAppointment> appointments = new ArrayList<>();
		List<RequestAppointment> ret = new ArrayList<RequestAppointment>();
		ClinicAdministrator ca;
		try {
			ca = cas.findByUsername(cadmin);
		} catch (Exception e) {
			System.out.println("Nije ulogovan admin klinike!");
			return null;
		}
		if (ca != null) {
			appointments = ras.findByClinicId(ca.getClinic().getId());
			for (RequestAppointment a : appointments) {
				// Uzimamo one operacije kojima nije dodeljena ni jedna sala
				if (a.getHospitalRoom() == null)
					ret.add(a);
			}
		}
		return ret;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-room-app", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody povratni tip metode
	public @ResponseBody ResponseEntity<Appointment> addApp(@RequestBody AppointmentDTO appointment) {
		// Transakcija se vrsi u servisu
		Appointment appointment1 = null;
		try {
			appointment1 = as.acceptAppointment(appointment);
		} catch (Exception e) {
			return new ResponseEntity<>(appointment1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(appointment1, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-requestApp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RequestAppointment> addRequestApp(@RequestBody AppointmentDTO appointment) {

		RequestAppointment appointment1 = new RequestAppointment(appointment.getPatient(), appointment.getDate(),
				appointment.getDescription(), appointment.getDuration());
		try {
			Patient pa = ps.findByUsername(appointment.getPatient());
			Long paID = pa.getId();

			MedicalRecord mr = mrs.findByPatientId(paID);
			appointment1.setMedicalRecord(mr);
		} catch (Exception e) {
		}
		appointment1.setDoctorUsername(appointment.getDoctorUsername());
		Doctor doctor = ds.findByUsername(appointment.getDoctorUsername());
		appointment1.setDoctor(doctor);
		Clinic c = cs.findById(appointment1.getDoctor().getId());
		ras.save(appointment1);
		// Pronalazenje administratora odabrane klinike
		// Slanje mail-a za potvrdu
		for (ClinicAdministrator ca : c.getClinicAdministrator()) {
			try {
				es.sendNotificaitionAsyncc3(ca);
			} catch (Exception e) {
				System.out.println("Poruka nije poslata!");
			}
		}
		return new ResponseEntity<>(appointment1, HttpStatus.OK);
	}

	// Dodavanje predefinisanih pregleda
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-requestAppFAST/{username}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RequestAppointment> addRequestAppo(@RequestBody AppointmentDTO appointment,
			@PathVariable String username) {

		RequestAppointment appointment1 = new RequestAppointment(appointment.getPatient(), appointment.getDate(),
				appointment.getDescription(), appointment.getDuration());
		ClinicAdministrator ca = cas.findByUsername(username);
		Clinic c = cs.findById(ca.getClinic().getId());
		appointment1.setClinic(c);
		appointment1.setPrice(appointment.getPrice());
		ras.save(appointment1);
		return new ResponseEntity<>(appointment1, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-requestApp-from-patient", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RequestAppointment> addRequestAppFromPatient(
			@RequestBody AppointmentDTO appointment) {

		RequestAppointment appointment1 = null;
		try {
			appointment1 = as.addRequestApp(appointment);
			if (appointment1 == null) {
				return new ResponseEntity<>(appointment1, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(appointment1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(appointment1, HttpStatus.OK);
	}

	// Pronalazenje svih zahteva za pregled
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getAppointmentRequests", method = RequestMethod.GET)
	public List<RequestAppointment> getAppointmentReq() {
		return ras.findAll();
	}

	// Vracanje svih zavrsenih pregleda
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getAppointments/{username}", method = RequestMethod.GET)
	public List<Appointment> getAppointments(@PathVariable String username) {

		List<Appointment> ret = new ArrayList<>();
		List<Appointment> sviPregledi = as.findAll();
		for (Appointment a : sviPregledi) {
			if (a.isFinished()) {
				ret.add(a);
			}
		}
		return ret;
	}

	// Pronalazenje svih zahteva za pregled za jednog admina klinike
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/all-requestAppointments/{usernameAdmin}", method = RequestMethod.GET)
	public List<RequestAppointment> getAllRequestAppointmentsOfClinic(@PathVariable String usernameAdmin) {

		List<RequestAppointment> apps = ras.findAll();
		List<RequestAppointment> ret = new ArrayList<>();
		ClinicAdministrator clinicAdministrator = cas.findByUsername(usernameAdmin);
		try {
			for (RequestAppointment app : apps) {
				if (app.getClinic().getId().equals(clinicAdministrator.getClinic().getId())) {
					ret.add(app);
				}
			}
		} catch (Exception e) {
		}

		return ret;
	}

	//Pronalazenje pregleda pacijenta
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/all-patient-appointment/{username}", method = RequestMethod.GET)
	public List<Appointment> getAllAppointments(@PathVariable String username) {

		List<Appointment> apps = as.findAll();
		List<Appointment> ret = new ArrayList<>();
		try {
			for (Appointment app : apps) {
				if (app.getPatient().equals(username))
					ret.add(app);
			}
		} catch (Exception e) {
		}
		return ret;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointments2() {
		return as.findAll();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getAppointmentsMR/{username}", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsMR(@PathVariable String username) {
		return as.findAll();
	}

	// Uzimanje svih pregleda za kalendar
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/getAllAppointments/{doctor}", method = RequestMethod.GET)
	public List<CalendarEventsDTO> getAllAppointmentsDoctor(@PathVariable String doctor) throws ParseException {
		List<Appointment> lista = as.findAll();
		List<CalendarEventsDTO> eventsDTOS = new ArrayList<CalendarEventsDTO>();
		List<Surgery> surgeries = ss.findAll();

		for (Appointment app : lista) {
			if (app.getDoctor() != null) {
				if (app.getDoctor().getUsername().equals(doctor)) {
					String title = "";
					try {
						Patient patient = ps.findByUsername(app.getPatient());
						String finished = "";
						if (app.isFinished())
							finished = "FINISHED";
						else
							finished = "AVAILABLE";
						title = app.getId() + "\n" + "pregled" + "\n" + app.getDescription() + "\n"
								+ patient.getFirstName() + " " + patient.getLastName() + "\n" + finished + "\n"
								+ patient.getUsername();
					} catch (Exception e) {
						title = app.getDescription() + "\nNema pacijenata.";
					}

					String color = "green";
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
					Date date = dateFormat.parse(app.getDate());
					long millis = date.getTime();
					millis += app.getDuration() * 60 * 60 * 1000;
					String endDate = dateFormat.format(millis);

					CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, app.getDate(), endDate, app.getId(),
							color);
					eventsDTOS.add(eventsDTO);
				}
			}
		}
		Doctor doc = ds.findByUsername(doctor);
		for (Surgery s : surgeries) {
			if (s.getDoctor().contains(doc)) {
				String title = "";
				try {
					Patient patient = ps.findByUsername(s.getPatient());

					title = s.getId() + "\n" + "operacija" + "\n" + s.getDescription() + "\n" + patient.getFirstName()
							+ " " + patient.getLastName();
				} catch (Exception e) {
					title = s.getDescription() + "\nnema pacijenata.";
				}

				String color = "purple";
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				Date date = dateFormat.parse(s.getDate());
				long millis = date.getTime();
				millis += s.getDuration() * 60 * 60 * 1000;
				String endDate = dateFormat.format(millis);

				CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, s.getDate(), endDate, s.getId(), color);
				eventsDTOS.add(eventsDTO);
			}
		}
		return eventsDTOS;
	}

	// Dodavljanje svih sala klinike
	@CrossOrigin(origins = "http//localhost:4200")
	@RequestMapping(value = "/api/available-room-other-date-Appointment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private AvailableHospitalRoomDTO availableRoomOtherDate(@RequestBody AppointmentDTO appointmentDTO)
			throws ParseException {
		AvailableHospitalRoomDTO ret = new AvailableHospitalRoomDTO();
		RequestAppointment requestAppointment = ras.findById(appointmentDTO.getId());
		
		List<HospitalRoom> rooms = hrs.findByClinicId(requestAppointment.getClinic().getId());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date date = dateFormat.parse(requestAppointment.getDate());
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

	//Popunjavanje kalendara za sestru
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/getAllAppointments-nurse/{nurse}", method = RequestMethod.GET)
	public List<CalendarEventsDTO> getAllAppointmentsNurse(@PathVariable String nurse) throws ParseException {
		List<Appointment> lista = as.findAll();
		List<CalendarEventsDTO> eventsDTOS = new ArrayList<CalendarEventsDTO>();
		List<Surgery> surgeries = ss.findAll();
		Nurse nur = (Nurse) mss.findByUsername(nurse);
		for (Appointment app : lista) {
			if (app.getDoctor().getClinic().getId() == nur.getClinic().getId()) {
				String title = "";
				try {
					Patient patient = ps.findByUsername(app.getPatient());
					title = app.getDescription() + "\n" + patient.getFirstName() + " " + patient.getLastName();
				} catch (Exception e) {
					title = app.getDescription() + "\nNema pacijenata.";
				}

				String color = "green";
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				Date date = dateFormat.parse(app.getDate());
				long millis = date.getTime();
				millis += app.getDuration() * 60 * 60 * 1000;
				String endDate = dateFormat.format(millis);

				CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, app.getDate(), endDate, app.getId(), color);
				eventsDTOS.add(eventsDTO);
			}
		}
		return eventsDTOS;
	}

	//Izvestaji
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/set-app-report", method = RequestMethod.POST)
	public void reportAppointment(@RequestBody ReportAppointmentDTO reportAppointmentDTO) {
		Appointment app2 = new Appointment();
		Appointment app = as.findById(reportAppointmentDTO.getAppointment().getId());
		app2.setId(reportAppointmentDTO.getAppointment().getId());
		app.setInfo(reportAppointmentDTO.getAppointment().getInfo());

		Diagnosis diagnosis = dis.findById(reportAppointmentDTO.getDiagnosis().getId());
		app.setDiagnosis(diagnosis);
		Recipe recipe = new Recipe().builder().authenticated(false)
				.description(reportAppointmentDTO.getRecipe().getDescription()).appointment(app).build();

		Set<Drug> drugs = new HashSet<>();
		for (Long id : reportAppointmentDTO.getRecipe().getDrugs()) {
			Drug drug = drs.findById(id);
			drugs.add(drug);
		}
		recipe.setDrug(drugs);
		app.setRecipe(recipe);
		Recipe r = rs.save(recipe);
		Appointment app1 = as.save(app);
	}

	//Dobavljanje starih medicinskih izvestaja
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-old-app-report/{doctor}", method = RequestMethod.GET)
	private List<Appointment> getOldAppointments(@PathVariable String doctor) {
		List<Appointment> appointments = as.findAll();
		List<Appointment> ret = new ArrayList<>();
		for (Appointment app : appointments) {
			if (app.getDoctor().getUsername().equals(doctor) && app.isFinished())
				ret.add(app);
		}
		return ret;
	}

	@RequestMapping(value = "/getAlreadyCreatedAppointments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Appointment> getAlreadyCreatedAppointments() throws ParseException {
		List<Appointment> lista = as.findAll();
		List<Appointment> ret = new ArrayList<>();
		for (Appointment a : lista) {
			if (a.getPatient() == null) {
				ret.add(a);
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
					System.out.println("Pregled nije moguc " + appointment.getDate());
					available = false;
				}
			}
		}
		return available;
	}

	@Scheduled(cron = "${greeting.cron}")
	private void systemReservation2() throws ParseException, InterruptedException {
		List<RequestAppointment> allRequestAppointments = ras.findAll();
		List<RequestAppointment> requestsWithoutRoom = new ArrayList<>();
		for (RequestAppointment requestAppointment : allRequestAppointments) {
			if (requestAppointment.getHospitalRoom() == null)
				requestsWithoutRoom.add(requestAppointment);
		}

		for (RequestAppointment s : requestsWithoutRoom) {
			RequestAppointment req = ras.findById(s.getId());
			List<HospitalRoom> rooms = hrs.findByClinicId(req.getClinic().getId());

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date date = dateFormat.parse(req.getDate());
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
							req.setHospitalRoom(room);
							req.setDate(newDate);
							Appointment appointment1 = new Appointment();
							appointment1.setDate(req.getDate());
							appointment1.setDescription(req.getDescription());
							appointment1.setDoctorUsername(req.getDoctorUsername());
							appointment1.setDuration(req.getDuration());
							appointment1.setType(req.getType());
							appointment1.setPatient(req.getPatient());
							appointment1.setHospitalRoom(req.getHospitalRoom());

							room.getAppointments().add(appointment1);
							Appointment appointment = as.save(appointment1);
							HospitalRoom saveHR = hrs.save(room);
							Patient patient = ps.findByUsername(req.getPatient());
							try {
								es.sendPatientNotificaition2(appointment1, patient);
							} catch (Exception e) {
								System.out.println("Neuspesno!");
							}
						} else {
							startSurgery = startSurgery + 2 * 60 * 60 * 1000;
						}
					}
				}
			}
		}
	}

	// Brzi pregledi
	@CrossOrigin(origins = "http//localhost:4200")
	@RequestMapping(value = "/api/add-room-to-appointmentF", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addRoomToFastApp(@RequestBody ReservationHospitalRoomDTO2 reservationHospitalRoomDTO)
			throws InterruptedException {
		HospitalRoom hospitalRoom = this.hrs.findById(reservationHospitalRoomDTO.getAppointmentDTO().getRoomID());
		RequestAppointment req = this.ras.findById(reservationHospitalRoomDTO.getAppointmentDTO().getId());
		req.setHospitalRoom(hospitalRoom);
		req.setDate(reservationHospitalRoomDTO.getAppointmentDTO().getDate());
		req.setDoctor(this.ds.findById(reservationHospitalRoomDTO.getDoctor()));

		Appointment appointment1 = new Appointment();
		appointment1.setDate(req.getDate());
		appointment1.setDescription(req.getDescription());
		appointment1.setDoctorUsername(req.getDoctorUsername());
		appointment1.setDuration(req.getDuration());
		appointment1.setType("neki tip");
		appointment1.setPatient(null);
		appointment1.setHospitalRoom(req.getHospitalRoom());
		appointment1.setDoctor(req.getDoctor());
		appointment1.setPrice(req.getPrice());
		Appointment a = this.as.save(appointment1);
		this.ras.delete(req);

		Doctor doctor = this.ds.findById(reservationHospitalRoomDTO.getDoctor());
		doctor.getAppointments().add(a);
		Doctor d = this.ds.save(doctor);
		hospitalRoom.getAppointments().add(a);
		HospitalRoom hospitalRoom1 = this.hrs.save(hospitalRoom);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-report-info/{id}", method = RequestMethod.GET)
	private ReportAppointmentDTO getOldAppointmentsInfo(@PathVariable Long id) {
		Appointment appointment = as.findById(id);
		ReportAppointmentDTO reportAppointmentDTO = new ReportAppointmentDTO();
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setPatient(appointment.getPatient());
		appointmentDTO.setId(appointment.getId());
		appointmentDTO.setInfo(appointment.getInfo());
		reportAppointmentDTO.setAppointment(appointmentDTO);
		DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
		Diagnosis d = dis.findById(appointment.getDiagnosis().getId());
		diagnosisDTO.setDescription(d.getDescription());
		diagnosisDTO.setName(d.getName());
		diagnosisDTO.setId(d.getId());
		reportAppointmentDTO.setDiagnosis(diagnosisDTO);
		RecipeDTO recipeDTO = new RecipeDTO();
		Recipe r = rs.findById(appointment.getRecipe().getId());
		recipeDTO.setDescription(r.getDescription());
		recipeDTO.setId(r.getId());
		Set<Drug> drugs = (Set<Drug>) r.getDrug();
		List<Long> recipeDrug = new ArrayList<>();
		for (Drug drug : drugs) {
			recipeDrug.add(drug.getId());
		}
		recipeDTO.setDrugs(recipeDrug);
		reportAppointmentDTO.setRecipe(recipeDTO);
		return reportAppointmentDTO;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/scheduleApp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Appointment> scheduleApp(@RequestBody AppointmentDTO appointment) {

		Appointment appointment1 = null;
		try {
			appointment1 = as.schedule(appointment);
		} catch (Exception e) {
			return new ResponseEntity<>(appointment1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(appointment1, HttpStatus.OK);
	}
}
