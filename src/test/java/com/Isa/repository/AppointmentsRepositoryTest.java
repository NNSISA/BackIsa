package com.Isa.repository;

import com.Isa.constants.AppointmentConstants;
import com.model.*;
import com.repository.AppointmentRepository;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class AppointmentsRepositoryTest {

	@Autowired
	private AppointmentRepository ar;

	@Autowired
	private TestEntityManager em;

	@Test
	public void findById() {
		Appointment app = ar.findById(AppointmentConstants.DB_APPOINTMENTS_ID).get();
		assertEquals(AppointmentConstants.DB_APPOINTMENTS_ID, app.getId());
	}

	@Test
	public void save() {
		Clinic c = new Clinic();
		c.setName("Testiranje");
		c.setDescription("Testiranje");
		c.setLat(25);
		c.setLongitude(552);
		c.setProfit(0);
		c.setRating(4);
		c.setAddress("test");
		this.em.persist(c);
		Diagnosis d = new Diagnosis();
		d.setName("Upala grla");
		d.setDescription("Temperatura");
		this.em.persist(d);
		Recipe r = new Recipe().builder().authenticated(true).build();
		this.em.persist(r);
		AppointmentType at = new AppointmentType(1L, "test");
		this.em.persist(at);
		Appointment app = new Appointment(1L, null, null, null, r, d, at, "sava", "doctor", "2020-07-22T16:00", "test",
				"stomatoloski", 2L, true, "test");

		Appointment app1 = this.ar.save(app);
		assertEquals(app1.getId(), this.em.getId(app1));
	}
}
