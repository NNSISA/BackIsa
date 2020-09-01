package com.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.ClinicalCenterAdministrator;
import com.model.Patient;
import com.repository.ClinicalCenterAdministratorRepository;
import com.repository.PatientRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	protected final Log LOGGER = LogFactory.getLog(getClass());

	@Autowired
	private PatientRepository pr;
	@Autowired
	private ClinicalCenterAdministratorRepository ccar;
	@Autowired
	private PasswordEncoder pe;
	@Autowired
	private AuthenticationManager am;

	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Patient user = pr.findByUsername(username);
		if (user != null) {
			return user;
		}

		ClinicalCenterAdministrator cca = ccar.findByUsername(username);
		if (cca != null) {
			return cca;
		} else {
			throw new UsernameNotFoundException(
					String.format("Ne postoji korisnik sa takvim korisnickim imenom '%s'.", username));
		}
	}

	// Funkcija pomocu koje korisnik menja svoju lozinku
	public void changePassword(String oldPassword, String newPassword) {

		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();

		if (am != null) {
			LOGGER.debug("Korisnik '" + username + "' za menjanje sifre.");

			am.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		} else {
			LOGGER.debug("Ne moze se menjati sifra");
			return;
		}
		LOGGER.debug("Changing password for user '" + username + "'");
		Patient user = (Patient) loadUserByUsername(username);
		// Pre upisavanja lozinke u bazu potrebno je napraviti hash
		user.setPassword(pe.encode(newPassword));
		pr.save(user);
	}
}
