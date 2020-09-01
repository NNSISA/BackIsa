package com.Isa.controller;

import static org.junit.Assert.assertEquals;
import com.Isa.constants.ClinicConstants;
import com.dto.ClinicDTO;
import com.dto.DoctorDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClinicControllerTest {
	private static final String URL_PREFIX = "/";
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private TestRestTemplate rt;

	HttpHeaders headers = new HttpHeaders();
	HttpEntity<Object> httpEntity;

	@Test
	public void getClinic() throws Exception {
		ResponseEntity<ClinicDTO[]> responseEntity = rt.exchange(URL_PREFIX + "api/get-clinics", HttpMethod.GET,
				httpEntity, ClinicDTO[].class);

		ClinicDTO[] clinics = responseEntity.getBody();
		for (ClinicDTO C : clinics) {

			System.out.println(C);
		}

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assert.assertEquals(ClinicConstants.DB_CLINIC_COUNT, clinics.length);
		Assert.assertEquals(ClinicConstants.DB_CLINIC_ID, clinics[3].getId());
		Assert.assertEquals(ClinicConstants.DB_CLINIC_NAME, clinics[3].getName());
		Assert.assertEquals(ClinicConstants.DB_CLINIC_ADDRESS, clinics[3].getAddress());
		Assert.assertEquals(ClinicConstants.DB_CLINIC_DESCRIPTION, clinics[3].getDescription());
		Assert.assertEquals(ClinicConstants.DB_CLINIC_PROFIT, clinics[3].getProfit());
		Assert.assertEquals(ClinicConstants.DB_CLINIC_RATING, clinics[3].getRating());
	}

	// Pozitivno testiranje
	@Test
	public void getSearchClinic() throws Exception {
		ResponseEntity<ClinicDTO[]> responseEntity = rt.exchange(
				URL_PREFIX + "api/get-search-clinics/" + ClinicConstants.DB_DATE + "/" + ClinicConstants.DB_TYPE,
				HttpMethod.GET, httpEntity, ClinicDTO[].class);

		ClinicDTO[] appts = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assert appts != null;
	}

	// Pozitivno testiranje
	@Test
	public void getSearchDoctors() throws Exception {
		ResponseEntity<DoctorDTO[]> responseEntity = rt
				.exchange(
						URL_PREFIX + "api/get-search-doctors/" + ClinicConstants.DB_DATE + "/"
								+ ClinicConstants.DB_CLINIC_NAME + "/" + ClinicConstants.DB_TYPE,
						HttpMethod.GET, httpEntity, DoctorDTO[].class);

		DoctorDTO[] appts = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assert appts != null;
	}

}
