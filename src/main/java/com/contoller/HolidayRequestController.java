package com.contoller;

import com.dto.HolidayRequestDTO;
import com.model.HolidayRequest;
import com.model.MedicalStaff;
import com.service.HolidayRequestService;
import com.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HolidayRequestController {

	@Autowired
	private HolidayRequestService hrs;
	@Autowired
	private MedicalStaffService mss;

	//Pronalazenje svih zahteva za odmor
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getHolidayRequests", method = RequestMethod.GET)
	public List<HolidayRequest> getHolidayReq() {
		return hrs.findAll();
	}

	//Lista nezavrsenih godisnjih odmora
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getHolidayRequests2/{username}", method = RequestMethod.GET)
	public List<HolidayRequestDTO> getHolidayReq2(@PathVariable String username) {
		List<HolidayRequest> list = hrs.findAll();
		List<HolidayRequestDTO> list2 = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			HolidayRequest h = list.get(i);
			HolidayRequestDTO dto = new HolidayRequestDTO();
			dto.setDateEnd(h.getDateEnd());
			dto.setDateStart(h.getDateStart());
			dto.setUsername(h.getMedicalStaff().getUsername());
			dto.setId(h.getId());
			dto.setConfirmed(h.isConfirmed());
			if (!h.isFinished()) {
				list2.add(dto);
			}
		}
		return list2;
	}

	//Izmena potvrde godisneg odmora
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/changeConfirmation/{message}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<HolidayRequestDTO> change(@RequestBody HolidayRequestDTO holidayreq,
			@PathVariable String message) {

		HolidayRequestDTO holidayRequest = new HolidayRequestDTO();
		try {
			holidayRequest = hrs.changeConfirmation(holidayreq, message);
			return new ResponseEntity<>(holidayRequest, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(holidayRequest, HttpStatus.NOT_FOUND);
		}
	}

	//Zakazivanje godisnjeg odmora
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/holiday-request", method = RequestMethod.POST)
	@PostMapping(consumes = "application/json")
	public void holidayRequest(@RequestBody HolidayRequestDTO holidayRequestDTO) {
		HolidayRequest holidayRequest = new HolidayRequest();
		MedicalStaff ms = mss.findByUsername(holidayRequestDTO.getUsername());
		holidayRequest.setDateStart(holidayRequestDTO.getDateStart());
		holidayRequest.setDateEnd(holidayRequestDTO.getDateEnd());
		holidayRequest.setMedicalStaff(ms);
		holidayRequest = hrs.save(holidayRequest);
		ms.getHolidayRequests().add(holidayRequest);
		ms = mss.save(ms);
	}
}
