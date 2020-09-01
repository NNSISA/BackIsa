package com.service;

import com.dto.HolidayRequestDTO;
import com.model.HolidayRequest;
import com.repository.HolidayRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class HolidayRequestService implements HolidayRequestServiceInterface {

	@Autowired
	private HolidayRequestRepository hrr;
	@Autowired
	private EmailService es;

	public HolidayRequest findById(Long id) {
		return hrr.findById(id).get();
	}

	public List<HolidayRequest> findAll() {
		return hrr.findAll();
	}

	public HolidayRequest save(HolidayRequest holidayRequest) {
		return hrr.save(holidayRequest);
	}

	//rollbackFor oznacava za koje izuzetke ce se desiti roolback
	//REQUIRED prikljucuje metodu transakciji ili otvara novu ako transakcija ne postoji
	//READ_UNCOMMITTED eliminise problem poslednje izmene
	//Transakcija za potvrdjivanje godisnjeg odmora
	//Slanje mail-a, odobreno ili nije odobreno
	@Transactional(rollbackFor = {
			RuntimeException.class }, readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public HolidayRequestDTO changeConfirmation(HolidayRequestDTO holidayreq, String message) {

		HolidayRequest holidayRequest = hrr.findById(holidayreq.getId()).get();
		HolidayRequestDTO holidayRequestDTO = new HolidayRequestDTO();
		holidayRequest.setConfirmed(holidayreq.getConfirmed());
		holidayRequest.setFinished(true);
		hrr.save(holidayRequest);

		try {
			if (holidayRequest.isConfirmed()) {
				es.sendNotificaitionAsync5();
			} else {
				es.sendNotificaitionAsync6(message);
			}
		} catch (Exception e) {
			System.out.println("Poruka nije poslata!");
		}
		return holidayRequestDTO;
	}
}
