package com.repository;

import com.model.RequestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestRepository extends JpaRepository<RequestUser, Long> {

	List<RequestUser> findAll();
}
