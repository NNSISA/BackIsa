package com.Isa.service;

import com.model.Clinic;
import com.model.Doctor;
import com.service.ClinicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static com.Isa.constants.ClinicConstants.*;
import static org.junit.Assert.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClinicServiceTest {

	@Autowired
	public ClinicService cs;

	// Pozitivno testiranje
	@Test
	public void getSearchDoctor() {
		List<Doctor> doctors = this.cs.getSearchDoctor("2020-09-22T10:00", DB_CLINIC_NAME_SEARCH_DOCTOR, "Kardioloski");

		for (Doctor d : doctors) {
			assertEquals(d.getClinic().getName(), DB_CLINIC_NAME_SEARCH_DOCTOR);
		}

		assertNotEquals(doctors, null);
		assertNotEquals(doctors.size(), 0);

	}

	// Pozitivno testiranje
	@Test
	public void getSearchClinics() {
		List<Clinic> clinics = this.cs.getSearchClinics("2020-09-22T10:00", "Kardioloski");

		assertNotEquals(clinics, null);
		assertNotEquals(clinics.size(), 0);
	}

	// Negativno testiranje
	@Test
	public void getSearchDoctorFail() {
		List<Doctor> doctors = this.cs.getSearchDoctor("2020-09-22T10:00", DB_CLINIC_NAME_SEARCH_DOCTOR, "test");

		assertEquals(doctors.size(), 0);
	}

	// Negativno testiranje
	@Test
	public void getSearchClinicsFail() {
		List<Clinic> clinics = this.cs.getSearchClinics("2020-09-22T10:00", "test");

		assertEquals(clinics.size(), 0);
	}
}
