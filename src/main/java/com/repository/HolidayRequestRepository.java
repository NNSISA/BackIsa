package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.HolidayRequest;

public interface HolidayRequestRepository extends JpaRepository<HolidayRequest, Long> {

}
