package com.service;

import com.dto.ClinicDTO;
import com.model.*;
import com.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ClinicService implements ClinicServiceInterface {

	@Autowired
	private ClinicRepository cr;
	@Autowired
	MedicalStaffService mss;
	@Autowired
	AppointmentService as;
	@Autowired
	EmailService es;

	public Clinic findById(long id) {
		return cr.findById(id).get();
	}

	public List<Clinic> findAll() {
		return cr.findAll();
	}

	public Clinic findByName(String name) {
		return cr.findByName(name);
	}

	public Clinic save(Clinic clinic) {
		return cr.save(clinic);
	}

	public void delete(Clinic clinic) {
		cr.delete(clinic);
	}

	public List<Clinic> getSearchClinics(String date, String type) {
		List<MedicalStaff> doctors = mss.findByRole("doctor");
		List<Long> doctorsId = new ArrayList<>();
		for (MedicalStaff doctor : doctors) {
			doctorsId.add(doctor.getId());
		}

		List<Long> doctoriKojiSuZauzeti = new ArrayList<>();
		for (Appointment app : as.findByFinished(false)) {
			if (app.getDate().split("T")[0].equals(date)) {
				if (!doctoriKojiSuZauzeti.contains(app.getDoctor().getId())) {
					doctoriKojiSuZauzeti.add(app.getDoctor().getId());
				}
			}
		}

		if (doctoriKojiSuZauzeti.size() != 0) {
			for (Long id : doctoriKojiSuZauzeti) {
				doctorsId.remove(id);
			}
		}

		List<Clinic> clinics = new ArrayList<>();
		for (MedicalStaff doctor : doctors) {
			for (Long id : doctorsId) {
				if (!type.equals("-1")) {
					if (doctor.getId() == id && ((Doctor) doctor).getAppointmentType().getName().equals(type)) {
						// Provera tipa
						if (!clinics.contains(cr.findById(((Doctor) doctor).getClinic().getId()))) {
							clinics.add(cr.findById(((Doctor) doctor).getClinic().getId()).get());
						}
					}
				} else {
					if (doctor.getId() == id) {
						if (!clinics.contains(cr.findById(((Doctor) doctor).getClinic().getId()))) {
							clinics.add(cr.findById(((Doctor) doctor).getClinic().getId()).get());
						}
					}
				}
			}
		}
		return clinics;
	}

	public List<Doctor> getSearchDoctor(String date, String imeKlinike, String tipPregleda) {

		List<MedicalStaff> doctors = mss.findByRole("doctor");
		List<Long> doctorsId = new ArrayList<>();
		for (MedicalStaff doctor : doctors) {
			doctorsId.add(doctor.getId());
		}
		List<Long> doctoriKojiSuZauzeti = new ArrayList<>();
		for (Appointment app : as.findByFinished(false)) {
			if (app.getDate().split("T")[0].equals(date)) {
				if (!doctoriKojiSuZauzeti.contains(app.getDoctor().getId())) {
					doctoriKojiSuZauzeti.add(app.getDoctor().getId());
				}
			}
		}

		if (doctoriKojiSuZauzeti.size() != 0) {
			for (Long id : doctoriKojiSuZauzeti) {
				doctorsId.remove(id);
			}
		}

		Clinic klinika = cr.findByName(imeKlinike);
		List<Doctor> ret = new ArrayList<>();
		if (klinika.getDoctors().size() != 0) {
			Set<Doctor> doctorsInClinic = klinika.getDoctors();
			for (Doctor doc : doctorsInClinic) {
				for (Long id : doctorsId) {
					if (!tipPregleda.equals("-1")) {
						if (doc.getId() == id && doc.getAppointmentType().getName().equals(tipPregleda)) {
							ret.add(doc);
						}
					} else {
						if (doc.getId() == id) {
							ret.add(doc);
						}
					}
				}
			}
		} else {
			ret = null;
		}
		return ret;

	}

	@Transactional(rollbackFor = {
			RuntimeException.class }, readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public Clinic changeInfo(ClinicDTO newClinic, String name) {
		Clinic c = (Clinic) cr.findByName(name);
		if (c != null) {
			c.setLongitude(newClinic.getLongitude());
			c.setLat(newClinic.getLat());
			c.setAddress(newClinic.getAddress());
			c.setDescription(newClinic.getDescription());
			c.setName(newClinic.getName());
			cr.save(c);

			for (ClinicAdministrator ca : c.getClinicAdministrator()) {

				try {
					es.sendNotificaitionAsyncc3(ca);
				} catch (Exception e) {
					System.out.println("Poruka nije poslata");
				}
			}
			return c;
		} else {
			return null;
		}
	}

}
