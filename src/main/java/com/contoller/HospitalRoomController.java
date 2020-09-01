package com.contoller;

import com.dto.HospitalRoomDTO;
import com.model.*;
import com.service.ClinicAdministratorService;
import com.service.ClinicService;
import com.service.HospitalRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HospitalRoomController {

	@Autowired
	private HospitalRoomService hrs;
	@Autowired
	private ClinicService cs;
	@Autowired
	private ClinicAdministratorService cas;

	@CrossOrigin
	@GetMapping("sale")
	public List<HospitalRoom> getHospitalRooms() {
		return hrs.findAll();
	}

	//Vraca listu soba
	@CrossOrigin
	@GetMapping("sale/{usernameAdmin}")
	public List<HospitalRoom> getHospitalRooms2(@PathVariable String usernameAdmin) {
		ClinicAdministrator clinicAdministrator = cas.findByUsername(usernameAdmin);
		List<HospitalRoom> hrooms = hrs.findAll();
		List<HospitalRoom> ret = new ArrayList<>();
		for (int i = 0; i < hrooms.size(); i++) {
			if (hrooms.get(i).getClinic().getId().equals(clinicAdministrator.getClinic().getId())) {
				ret.add(hrooms.get(i));
			}
		}
		return ret;
	}

	//Brisanje sobe
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/delete-room", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<HospitalRoomDTO> deleteRoom(@RequestBody HospitalRoomDTO room) {

		HospitalRoom hr = hrs.findByName(room.getName());
		Clinic clinic = cs.findById(hr.getClinic().getId());
		clinic.getHospitalRooms().remove(hr);
		cs.save(clinic);
		hrs.delete(hr);
		HospitalRoomDTO hRoom = new HospitalRoomDTO(room.getName(), room.getRoom_number());
		return new ResponseEntity<>(hRoom, HttpStatus.OK);
	}

	//Menjanje informacija o sobi
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/changeRoomInfo/{name}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<HospitalRoomDTO> changeRoomInfo(@RequestBody HospitalRoomDTO room,
			@PathVariable String name) {

		HospitalRoom room1 = hrs.findByName(name);
		if (room1 != null) {
			room1.setName(room.getName());
			room1.setRoom_number(room.getRoom_number());
			hrs.save(room1);
		} else {
			HospitalRoom room2 = new HospitalRoom();
			room2.setName(room.getName());
			room2.setRoom_number(room.getRoom_number());
			hrs.save(room2);
		}
		HospitalRoomDTO room3 = new HospitalRoomDTO();
		return new ResponseEntity<>(room3, HttpStatus.OK);
	}

	//Cuvaje soba
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/add-room/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addRoom(@RequestBody HospitalRoomDTO hospitalRoom, @PathVariable String username) {
		// Korisnicko ime je korisnicko ime administratora koji je dodao kliniku.
		// Korisitimo da bismo dodelili sobu klinici
		System.out.println(username);
		HospitalRoom hr = new HospitalRoom(hospitalRoom);
		hrs.save(hr);
		ClinicAdministrator clinicAdministrator = cas.findByUsername(username);

		if (clinicAdministrator != null) {
			Clinic clinic = cs.findById(clinicAdministrator.getClinic().getId());
			if (clinic != null) {
				clinic.getHospitalRooms().add(hr);
				hr.setClinic(clinic);
				hrs.save(hr);
				cs.save(clinic);
				hrs.save(hr);
			}
		}
	}
}
