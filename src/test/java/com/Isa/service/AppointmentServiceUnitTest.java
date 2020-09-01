package com.Isa.service;

import com.dto.AppointmentDTO;
import com.model.*;
import com.repository.AppointmentRepository;
import com.repository.PatientRepository;
import com.service.AppointmentService;
import com.service.MedicalRecordService;
import com.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.*;
import static com.Isa.constants.AppointmentConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class AppointmentServiceUnitTest {

	@Mock
	public AppointmentRepository arm;
	@InjectMocks
	public AppointmentService as;
	@Mock
	public Appointment am;
	@Mock
	private PatientRepository prm;
	@Mock
	private MedicalRecordService mrs;
	@Mock
	public PatientService ps;

	@Test
	public void testFindAll() {

		Appointment app1 = new Appointment(1L, null, null, null, null, null, null, "test", "test", "test", "test",
				"test", 2L, false, "test");
		app1.setId(1L);
		app1.setPrice(2000);
		when(arm.findAll()).thenReturn(Arrays.asList(app1));
		List<Appointment> apps = as.findAll();
		assertEquals(apps.size(), 1);

	}

	@Test
	public void testFindOne() {
		Appointment app1 = new Appointment(1L, null, null, null, null, null, null, "test", "test", "test", "test",
				"test", 2L, false, "test");

		given(this.arm.findById(DB_APPOINTMENTS_ID)).willReturn(Optional.of(app1));
		Appointment dbAppointment = as.findById(DB_APPOINTMENTS_ID);
		assertEquals(Optional.of(1L).get(), dbAppointment.getId());
		verify(arm, times(1)).findById(DB_APPOINTMENTS_ID);
		verifyNoMoreInteractions(arm);
	}

	@Test
	public void sheduleTest() {
		AppointmentDTO appDTO = new AppointmentDTO(1L, null, "2020-07-05T16:00", "cold", "test", 2L, 1L,
				DB_APPOINTMENTS_PATIENT, "doctor", "test", null);

		HospitalRoom hr = new HospitalRoom(1l, "Operaciona soba", 1);

		Appointment app1 = new Appointment(1L, null, hr, null, null, null, null, DB_APPOINTMENTS_PATIENT, "doctor",
				"2020-07-05T16:00", "cold", "test", 2L, false, "test");

		Patient patient = new Patient();
		patient.setId(1L);
		patient.setUsername("niki");
		given(this.ps.findByUsername(DB_APPOINTMENTS_PATIENT)).willReturn(patient);
		MedicalRecord mr = new MedicalRecord();
		mr.setId(1L);
		mr.setPatient(patient);
		given(this.mrs.findByPatientId(patient.getId())).willReturn(mr);
		given(this.arm.findById(1L)).willReturn(Optional.of(app1));
		Appointment appointment = as.schedule(appDTO);

		assertEquals(appointment.getMedicalRecord().getId(), mr.getId());

	}

}
