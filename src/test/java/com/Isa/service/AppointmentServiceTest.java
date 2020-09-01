package com.Isa.service;

import com.dto.AppointmentDTO;
import com.model.*;
import com.service.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.ValidationException;
import java.util.List;
import static com.Isa.constants.AppointmentConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentServiceTest {

	@Autowired
	public AppointmentService as;

	// Pozitivno testiranje
	@Test
	@Transactional
	public void sheduleTest() {

		AppointmentDTO appDTO = new AppointmentDTO(1L, null, "2020-07-05T16:00", "cold", "test", 2L, 1L, "niki", "test",
				"test", null);

		Appointment appointment = as.schedule(appDTO);

		assertEquals(appointment.getPatient(), DB_APPOINTMENTS_PATIENT);
		assertEquals(appointment.getMedicalRecord().getId(), DB_APPOINTMENTS_MR);
	}

	@Test
	@Transactional
	public void acceptAppointment() {
		AppointmentDTO appDTO = new AppointmentDTO(1L, null, "2020-07-05T16:00", "cold", "test", 2L, 1L, "niki",
				"doctor", "test", null);

		List<Appointment> appsBEFORE = as.findAll();
		Appointment appointment = as.acceptAppointment(appDTO);
		List<Appointment> appsAFTER = as.findAll();
		assertEquals(appointment.getPatient(), DB_APPOINTMENTS_PATIENT);
		assertEquals(appsAFTER.size(), appsBEFORE.size() + 1);
		assertNotEquals(appsAFTER.size(), appsBEFORE.size());

	}

	// Pozitivno testiranje
	@Test
	public void findAllTest() throws Exception {
		List<Appointment> apps = as.findAll();
	}

	// Negativno testiranje
	@Test(expected = ValidationException.class)
	public void findByIdFail() throws Exception {
		Appointment app = as.findOne(15L);
	}
}
