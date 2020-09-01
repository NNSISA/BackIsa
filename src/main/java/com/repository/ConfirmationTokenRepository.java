package com.repository;

import com.model.ConfirmationTokenRegistration;
import com.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationTokenRegistration, String> {

	ConfirmationTokenRegistration findByPatient(Patient patient);

	ConfirmationTokenRegistration findByPatientUsername(String patientUsername);
	
	ConfirmationTokenRegistration findByConfirmationToken(String confirmationToken);
}
