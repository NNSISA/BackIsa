package com.Isa.controller;

import com.dto.AppointmentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.nio.charset.Charset;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentsControllerTest {

	private static final String URL_PREFIX = "/";
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private TestRestTemplate trt;

	HttpHeaders headers = new HttpHeaders();
	HttpEntity<Object> httpEntity;

	// Pozitivno testiranje
	@Test
	public void addApp() throws Exception {
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(1L);
		appointment.setDate("2020-04-02T10:00");
		appointment.setDescription("opis1");
		appointment.setDuration(2);
		appointment.setPatient("Nikola");
		appointment.setType("tip1");
		appointment.setDoctorUsername("Nikola");
		appointment.setRoomID(1L);
		appointment.setPrice(2000);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json");
		httpEntity = new HttpEntity<>(ow.writeValueAsString(appointment), header);

		ResponseEntity<AppointmentDTO> responseEntity = trt.exchange(URL_PREFIX + "/api/add-room-app", HttpMethod.POST,
				httpEntity, AppointmentDTO.class);

		AppointmentDTO appointmentDTO = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(appointmentDTO.getDate(), appointment.getDate());
		assertEquals(appointmentDTO.getHospitalRoom().getId(), appointment.getRoomID());

	}

	// Negativno testiranje
	@Test
	public void addApp2() throws Exception {
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(1L);
		appointment.setDate("2020-04-02T10:00");
		appointment.setDescription("opis1");
		appointment.setDuration(2);
		appointment.setPatient("nikola");
		appointment.setType("tip1");
		appointment.setDoctorUsername("Nikola");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json");
		httpEntity = new HttpEntity<>(ow.writeValueAsString(appointment), header);

		ResponseEntity<AppointmentDTO> responseEntity = trt.exchange(URL_PREFIX + "/api/add-room-app", HttpMethod.POST,
				httpEntity, AppointmentDTO.class);

		AppointmentDTO appointmentDTO = responseEntity.getBody();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

	@Test
	public void scheduleApp() throws Exception {
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setId(1L);
		appointment.setDate("2020-07-02T10:00");
		appointment.setDescription("opis1");
		appointment.setDuration(2);
		appointment.setPatient("nikola");
		appointment.setType("tip1");
		appointment.setDoctorUsername("Nikola");
		appointment.setPrice(2000);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json");
		httpEntity = new HttpEntity<>(ow.writeValueAsString(appointment), header);

		ResponseEntity<AppointmentDTO> responseEntity = trt.exchange(URL_PREFIX + "/scheduleApp", HttpMethod.POST,
				httpEntity, AppointmentDTO.class);

		AppointmentDTO appointmentDTO = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}
}
