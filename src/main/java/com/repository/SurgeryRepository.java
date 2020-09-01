package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Surgery;
import java.util.List;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {

	List<Surgery> findByClinicId(Long id);

	List<Surgery> findByHospitalRoomId(Long id);
	
	List<Surgery> findByMedicalRecordId(Long id);
}
