package com.service;

import com.model.MedicalStaff;
import com.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalStaffService {

	@Autowired
	private MedicalStaffRepository msr;

	public MedicalStaff findByUsername(String username) {
		return msr.findByUsername(username);
	}

	public MedicalStaff save(MedicalStaff medicalStaff) {
		return msr.save(medicalStaff);
	}

	public void delete(MedicalStaff medicalStaff) {
		msr.delete(medicalStaff);
	}
	
	public List<MedicalStaff> findByRole(String role) {
		return msr.findByRole(role);
	}
}
