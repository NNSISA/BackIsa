package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Nurse;
import java.util.List;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

	List<Nurse> findByUsername(String username);

}
