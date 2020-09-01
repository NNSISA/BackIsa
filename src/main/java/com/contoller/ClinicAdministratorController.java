package com.contoller;

import com.dto.ClinicAdministratorDTO;
import com.dto.ClinicDTO;
import com.model.Clinic;
import com.model.ClinicAdministrator;
import com.repository.ClinicAdministratorRepository;
import com.repository.ClinicRepository;
import com.service.ClinicAdministratorService;
import com.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClinicAdministratorController {

	@Autowired
	private ClinicAdministratorService cas;
	@Autowired
	private ClinicService cs;
	@Autowired
	private ClinicRepository cr;
	@Autowired
	private ClinicAdministratorRepository car;

	//Dodavanje admina klinike
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-admin/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addAdministrator(@RequestBody ClinicAdministratorDTO clinicAdministratorDTO, @PathVariable long id) {
		Clinic clinic = cs.findById(id);
		ClinicAdministrator ca = new ClinicAdministrator();
		ca.setUsername(clinicAdministratorDTO.getUsername());
		ca.setPassword(clinicAdministratorDTO.getPassword());
		ca.setEmail(clinicAdministratorDTO.getEmail());
		ca.setAddress(clinicAdministratorDTO.getAddress());
		ca.setCity(clinicAdministratorDTO.getCity());
		ca.setCountry(clinicAdministratorDTO.getCountry());
		ca.setJmbg(clinicAdministratorDTO.getJmbg());
		ca.setFirstName(clinicAdministratorDTO.getFirstName());
		ca.setLastName(clinicAdministratorDTO.getLastName());
		ca.setMobileNumber(clinicAdministratorDTO.getMobileNumber());
		ca = car.save(ca);
		clinic.getClinicAdministrator().add(ca);
		ca.setClinic(clinic);
		car.save(ca);
		cr.save(clinic);
	}

	//Pronalazenje admina klinika po korisnickom imenu
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/cadmin/{username}", method = RequestMethod.GET)
	public ClinicAdministrator getAdmin(@PathVariable String username) {
		return cas.findByUsername(username);
	}

	//Pronalazenje klinika
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getMyClinic/{username}", method = RequestMethod.GET)
	public ClinicDTO getClinic(@PathVariable String username) {
		ClinicAdministrator admin = cas.findByUsername(username);
		Clinic clinic = cs.findById(admin.getClinic().getId());
		ClinicDTO clinicDTO = new ClinicDTO(clinic);
		clinicDTO.setLongitude(clinic.getLongitude());
		clinicDTO.setLat(clinic.getLat());
		clinicDTO.setId(clinic.getId());
		return clinicDTO;
	}

	//Izmena informacija o adminu klinike
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/adminChangeInfo", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ClinicAdministratorDTO> changeInfo(@RequestBody ClinicAdministratorDTO mdNovi) {
		ClinicAdministrator md = (ClinicAdministrator) cas.findByUsername(mdNovi.getUsername());
		if (md != null) {
			md.setPassword(mdNovi.getPassword());
			md.setFirstName(mdNovi.getFirstName());
			md.setLastName(mdNovi.getLastName());
			md.setCity(mdNovi.getCity());
			md.setCountry(mdNovi.getCountry());
			md.setEmail(mdNovi.getEmail());
			md.setMobileNumber(mdNovi.getMobileNumber());
			cas.save(md);
		} else {

		}
		ClinicAdministratorDTO d = new ClinicAdministratorDTO(md);
		return new ResponseEntity<>(d, HttpStatus.OK);
	}
}
