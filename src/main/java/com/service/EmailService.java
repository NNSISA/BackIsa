package com.service;

import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender jms;
	@Autowired
	private Environment env;

	@Async
	public void sendNotificaitionAsync(Patient user, ConfirmationTokenRegistration confirmationToken)
			throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Potvrda registracije!");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setText("Da biste potvrdili svoj nalog, kliknite na sledeci link : "
				+ "http://localhost:4200/confirm-account?token=" + confirmationToken.getConfirmationToken());
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendNotificaitionAsync2(String email, String message) throws MailException, InterruptedException {

		String email2 = email + ".com";
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email2);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odbijen zahtev");

		mail.setText("Postovani, " + ",\n\nVas zahtev je odbijen.\n\n" + message);
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendNotificaitionAsync3() throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("noreply.isa95@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Novi zahtev");
		mail.setText("Postovani, imate novi zahtev za rezervaciju sobe. Proverite listu zahteva. ");
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendNotificaitionAsyncc3(ClinicAdministrator cadmin) throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(cadmin.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Novi zahtev");
		mail.setText("Postovani, imate novi zahtev za rezervaciju sobe. Proverite listu zahteva. ");
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendNotificaitionAsync4(Patient patient) throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(patient.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Potvrda");
		mail.setText("Vas pregled je potvrdjen. ");
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendDoctorNotificaition(Surgery surgery, Doctor doctor) throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(doctor.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Informacije o operaciji");

		mail.setText("Postovani, " + doctor.getFirstName() + " " + doctor.getLastName() + ","
				+ "\n\nImate novu zakazanu operaciju\n\nDatum: " + surgery.getDate() + "\nSoba: "
				+ surgery.getHospitalRoom().getName() + " no." + surgery.getHospitalRoom().getRoom_number()
				+ ".\n\nSve najbolje,\nVasa Klinika.");
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendPatientNotificaition(Surgery surgery, Patient patient) throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(patient.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Informacije o operaciji");

		mail.setText("Postovani, " + patient.getFirstName() + " " + patient.getLastName() + ","
				+ "\n\nVasa operacija je zakana\n\nDatum: " + surgery.getDate() + "\nSoba: "
				+ surgery.getHospitalRoom().getName() + " no." + surgery.getHospitalRoom().getRoom_number()
				+ ".\n\nSve najbolje,\nVasa Klinika.");
	}

	@Async
	public void sendPatientNotificaition2(Appointment surgery, Patient patient)
			throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(patient.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Informacije o operaciji");

		mail.setText("Postovani, " + patient.getFirstName() + " " + patient.getLastName() + ","
				+ "\n\nVasa operacija je zakazana\n\nDatum: " + surgery.getDate() + "\nSoba: "
				+ surgery.getHospitalRoom().getName() + " no." + surgery.getHospitalRoom().getRoom_number()
				+ ".\n\nSve najbolje,\nVasa Klinika.");
	}

	@Async
	public void sendNotificaitionAsync5() throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("noreply.isa95@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zahtev");
		mail.setText("Postovani, Vas zahtev za odmor je odobren. ");
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendNotificaitionAsync6(String message) throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("noreply.isa95@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zahtev");
		mail.setText("Postovani, Vas zahtev za odmor je odbijen. Objasnjenje: " + message);
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendPatientNotificaition7(Appointment surgery, Patient patient)
			throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(patient.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Informacije o operaciji");

		mail.setText("Postovani, " + patient.getFirstName() + " " + patient.getLastName() + ","
				+ "\n\nVas pregled \n\nDatum: " + surgery.getDate() + "\nSoba: " + surgery.getHospitalRoom().getName()
				+ " no." + surgery.getHospitalRoom().getRoom_number() + "je odobren .\n\nSve najbolje,\nVasa Klinika.");
	}

	@Async
	public void sendPatientNotificaition7(Nurse nurse) throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(nurse.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Informacije");

		mail.setText("Postovani, " + nurse.getFirstName() + ".");
	}

	@Async
	public void sendNotificaitionAsync8(ClinicAdministrator ca) throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(ca.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zahtev");
		mail.setText("Postovani, imate zahtev za nov pregled. Proverite listu zahteva. ");
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendPatientNotificaition9(Nurse nurse) throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(nurse.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Informacije");

		mail.setText("Postovani, " + nurse.getFirstName() + ".");
	}

}