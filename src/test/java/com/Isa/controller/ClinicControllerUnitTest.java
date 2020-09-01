package com.Isa.controller;

import com.Isa.TestUtil;
import com.model.Clinic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import static com.Isa.constants.ClinicConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClinicControllerUnitTest {

	private MockMvc mm;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	@Before
	public void setup() {
		this.mm = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetAllClinics() throws Exception {
		mm.perform(get("/api/get-clinics")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_CLINIC_COUNT)))
				.andExpect(jsonPath("$.[*].id").value(hasItem(DB_CLINIC_ID.intValue())))
				.andExpect(jsonPath("$.[*].name").value(hasItem(DB_CLINIC_NAME)))
				.andExpect(jsonPath("$.[*].address").value(hasItem(DB_CLINIC_ADDRESS)))
				.andExpect(jsonPath("$.[*].description").value(hasItem(DB_CLINIC_DESCRIPTION)));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSaveClinic() throws Exception {
		Clinic clinic = new Clinic();
		clinic.setName(DB_CLINIC_NAME);
		clinic.setDescription(DB_CLINIC_DESCRIPTION);
		clinic.setAddress(DB_CLINIC_ADDRESS);
		String json = TestUtil.json(clinic);
		this.mm.perform(post("/api/add-clinic").contentType(contentType).content(json)).andExpect(status().isOk());
	}

}
