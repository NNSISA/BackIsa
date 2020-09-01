package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.MedicalStaff;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long> {

	MedicalStaff findByUsername(String username);

	List<MedicalStaff> findByFirstName(String firstName);

	List<MedicalStaff> findByRole(String role);

}
