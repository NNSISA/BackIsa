package com.contoller;

import com.dto.ClinicalCenterAdministratorDTO;
import com.model.*;
import com.repository.ClinicalCenterAdministratorRepository;
import com.repository.ConfirmationTokenRepository;
import com.security.JwtAuthenticationRequest;
import com.security.TokenUtils;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClinicalCenterAdministratorContoller {

	@Autowired
	private ClinicalCenterAdministratorService ccas;
	@Autowired
	private RequestService rs;
	@Autowired
	private TokenUtils tu;
	@Autowired
	private PatientService ps;
	@Autowired
	private ClinicAdministratorService cas;
	@Autowired
	private DoctorService ds;
	@Autowired
	private NurseService ns;
	@Autowired
	private ConfirmationTokenRepository ctr;
	@Autowired
	private ClinicalCenterAdministratorRepository ccar;
	@Autowired
	private EmailService es;
	@Autowired
	private ClinicalCenterAdministratorService service;

	//Prijavljivanje na sistem
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> postCCAByUsernameAndPassword(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws AuthenticationException, IOException {

		AuthenticationManager a = new AuthenticationManager() {
			@Override
			public Authentication authenticate(Authentication authentication)
					throws org.springframework.security.core.AuthenticationException {
				String name = authentication.getName();
				String password = authentication.getCredentials().toString();
				return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
			}
		};

		final Authentication authentication = a.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = "";
		Patient pa = null;
		ClinicAdministrator ca = null;
		Doctor doc = null;
		Nurse nur = null;
		String username = (String) authentication.getPrincipal();
		ClinicalCenterAdministrator cca = (ClinicalCenterAdministrator) ccas.findByUsername(username);
		if (cca == null) {
			pa = ps.findByUsername(username);
			if (pa == null) {
				ca = cas.findByUsername(username);
				if (ca == null) {
					doc = ds.findByUsername(username);
					if (doc == null) {
						nur = ns.findByUsername(username);
						if (nur == null) {
							jwt = "";
						} else {
							jwt = tu.generateToken(username);
						}
					} else {
						jwt = tu.generateToken(username);
					}
				} else {
					jwt = tu.generateToken(username);
				}
			} else {
				jwt = tu.generateToken(username);
			}
		} else {
			jwt = tu.generateToken(username);
		}
		int expiresIn = tu.getExpiredIn();
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	//Dodavanje admina klinickog centra
	@PostMapping(consumes = "application/json")
	public ResponseEntity<ClinicalCenterAdministratorDTO> saveCCA(@RequestBody ClinicalCenterAdministratorDTO ccaDTO) {

		ClinicalCenterAdministrator cca = new ClinicalCenterAdministrator();
		cca.setUsername(ccaDTO.getUsername());
		cca.setPassword(ccaDTO.getPassword());
		ClinicalCenter cc = new ClinicalCenter();
		cc.setName(ccaDTO.getCenterDTO().getName());
		cca.setClinicalCenter(cc);
		if (cca == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ccas.save(cca);
		return new ResponseEntity<>(new ClinicalCenterAdministratorDTO(cca), HttpStatus.OK);
	}

	//Pronalazenje svih zahteva pacijenata
	@RequestMapping(value = "/api/requests", method = RequestMethod.GET)
	public List<RequestUser> getPatientRequests() {
		List<RequestUser> requests = rs.findAll();
		return requests;
	}

	//Brisanje zahteva pacijenata
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/deny-request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RequestUser> deletePatientRequests(@RequestBody RequestUser deleteUser) {

		rs.delete(deleteUser);
		List<RequestUser> requests = rs.findAll();
		Patient patient = new Patient(deleteUser.getUsername(), deleteUser.getPassword(), deleteUser.getFirstName(),
				deleteUser.getLastName(), deleteUser.getEmail(), deleteUser.getAddress(), deleteUser.getCity(),
				deleteUser.getCountry(), deleteUser.getMobileNumber(), deleteUser.getJmbg());

		return new ResponseEntity<>(deleteUser, HttpStatus.OK);
	}

	//Slanje mail-a
	//Ako je zahtev korisnika odbijen
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/deny-request-message/{email}", method = RequestMethod.POST)
	public void deletePatientRequests(@RequestBody String message, @PathVariable String email) {
		System.out.println("Slanje novom email-a");
		try {
			es.sendNotificaitionAsync2(email, message);
		} catch (Exception e) {
			System.out.println("Poruka nije poslata!");
		}
	}

	//Slanje mail-a pacijentu
	//Ako je prihvacen zahtev
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/accept-request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RequestUser> acceptPatientRequests(@RequestBody RequestUser deleteUser) {

		rs.delete(deleteUser);
		List<RequestUser> requests = rs.findAll();
		Patient patient = new Patient(deleteUser.getUsername(), deleteUser.getPassword(), deleteUser.getFirstName(),
				deleteUser.getLastName(), deleteUser.getEmail(), deleteUser.getAddress(), deleteUser.getCity(),
				deleteUser.getCountry(), deleteUser.getMobileNumber(), deleteUser.getJmbg());
		MedicalRecord medicalRecord = new MedicalRecord();
		patient.setRecord(medicalRecord);
		patient = ps.save(patient);
		medicalRecord.setPatient(patient);
		patient.setRecord(medicalRecord);
		patient = ps.save(patient);

		ConfirmationTokenRegistration confirmationToken = new ConfirmationTokenRegistration(patient);
		confirmationToken.setPatientUsername(patient.getUsername());
		ctr.save(confirmationToken);

		try {
			es.sendNotificaitionAsync(patient, confirmationToken);
		} catch (Exception e) {
			System.out.println("Poruka nije poslata!");
		}
		return new ResponseEntity<>(deleteUser, HttpStatus.OK);
	}

	//Potvrda naloga pacijenta
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/confirmAccount/{token}", method = RequestMethod.GET)
	public Patient confirmAccount(ModelAndView modelAndView, @PathVariable("token") String confirmationToken) {

		ConfirmationTokenRegistration token = ctr.findByConfirmationToken(confirmationToken);
		Patient patient = ps.findByUsername(token.getPatient().getUsername());
		patient.setEnabled(true);
		patient = ps.save(patient);
		return token.getPatient();
	}

	//Cuvanje admina centra
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-clinic-center-admin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addAdministrator(@RequestBody ClinicalCenterAdministrator clinicalCenterAdministrator) {
		ccar.save(clinicalCenterAdministrator);
	}

	//Vracanje admina centra
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-admin/{username}", method = RequestMethod.GET)
	private ResponseEntity getAdmin(@PathVariable String username) {
		if (username != null) {
			ClinicalCenterAdministrator cca = service.findByUsername(username);
			if (cca != null) {
				return new ResponseEntity<>(cca, HttpStatus.OK);
			}
			return new ResponseEntity<>("Ne postoji administrator sa takvim korisnickim imenom",
					HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>("Korisnicko ime ne postoji!", HttpStatus.UNPROCESSABLE_ENTITY);
	}

	//Promena lozinke admina centra
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "api/set-password-admin/{password}/{username}", method = RequestMethod.GET)
	private ClinicalCenterAdministrator setPasswordAdmin(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		ClinicalCenterAdministrator cca = service.findByUsername(username);
		cca.setPassword(password);
		cca.setFirstLog(1);
		ClinicalCenterAdministrator newcca = service.save(cca);
		return newcca;
	}
}
