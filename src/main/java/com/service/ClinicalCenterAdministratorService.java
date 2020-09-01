package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.model.ClinicalCenterAdministrator;
import com.repository.ClinicalCenterAdministratorRepository;

@Service
public class ClinicalCenterAdministratorService implements UserDetailsService {

	@Autowired
	private ClinicalCenterAdministratorRepository ccar;

	public ClinicalCenterAdministrator findByUsername(String username) {

		return ccar.findByUsername(username);
	}

	public ClinicalCenterAdministrator save(ClinicalCenterAdministrator cca) {

		return ccar.save(cca);
	}

	//Implementacija metode interfejsa
	//Provera da li postoji admin klinickog centra sa takvim korisnickim imenom
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ClinicalCenterAdministrator admin = ccar.findByUsername(username);

		if (admin == null) {
			throw new UsernameNotFoundException(
					String.format("Ne postoji korisnik sa takvim korisnickim imenom '%s'.", username));
		} else {
			return admin;
		}
	}
}
