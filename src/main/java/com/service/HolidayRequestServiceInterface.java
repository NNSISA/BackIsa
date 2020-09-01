package com.service;

import com.dto.HolidayRequestDTO;
import com.model.HolidayRequest;
import java.util.List;

public interface HolidayRequestServiceInterface {

	public HolidayRequest findById(Long id);

	public List<HolidayRequest> findAll();

	public HolidayRequest save(HolidayRequest holidayRequest);

	public HolidayRequestDTO changeConfirmation(HolidayRequestDTO holidayreq, String message);
}
