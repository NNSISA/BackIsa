package com.Isa.controller;

import static com.Isa.constants.ClinicCenterAdministratorConstants.*;
import static org.junit.Assert.assertEquals;
import com.model.ClinicalCenterAdministrator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClinicalCenterAdministratorContollerTest {
	private static final String URL_PREFIX = "/";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private TestRestTemplate rt;

	HttpHeaders headers = new HttpHeaders();
	HttpEntity<Object> httpEntity;

	@Test
	public void getAdmin() throws Exception {

		ResponseEntity<ClinicalCenterAdministrator> responseEntity = rt.exchange(
				URL_PREFIX + "api/get-admin/" + DB_CLINIC_CENTER_ADMIN_USERNAME, HttpMethod.GET, httpEntity,
				ClinicalCenterAdministrator.class);
		ClinicalCenterAdministrator ccadmin = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(DB_CLINIC_CENTER_ADMIN_ID, ccadmin.getId());
		assertEquals(DB_CLINIC_CENTER_ADMIN_FIRST_NAME, ccadmin.getFirstName());
		assertEquals(DB_CLINIC_CENTER_ADMIN_LAST_NAME, ccadmin.getLastName());
		assertEquals(DB_CLINIC_CENTER_ADMIN_EMAIL, ccadmin.getEmail());
		assertEquals(DB_CLINIC_CENTER_ADMIN_JMBG, ccadmin.getJmbg());
		assertEquals(DB_CLINIC_CENTER_ADMIN_ADDRESS, ccadmin.getAddress());
		assertEquals(DB_CLINIC_CENTER_ADMIN_USERNAME, ccadmin.getUsername());
		assertEquals(DB_CLINIC_CENTER_ADMIN_CITY, ccadmin.getCity());
		assertEquals(DB_CLINIC_CENTER_ADMIN_COUNTRY, ccadmin.getCountry());
		assertEquals(DB_CLINIC_CENTER_ADMIN_MOBILE_NUMBER, ccadmin.getMobileNumber());
		assertEquals(DB_CLINIC_CENTER_ADMIN_FIRST_LOG, ccadmin.getFirstLog());

	}

	@Test
	public void getAdminFailUsername() throws Exception {

		ResponseEntity<String> responseEntity = rt.exchange(URL_PREFIX + "api/get-admin/" + DB_CLINIC_CENTER_ADMIN_FAIL,
				HttpMethod.GET, httpEntity, String.class);

		assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());

	}

	@Test
	public void getAdminEmptyUsername() throws Exception {

		ResponseEntity<ClinicalCenterAdministrator> responseEntity = rt.exchange(URL_PREFIX + "api/get-admin",
				HttpMethod.GET, httpEntity, ClinicalCenterAdministrator.class);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

	}

}
