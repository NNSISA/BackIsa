package com.service;

import com.model.HospitalRoom;
import com.repository.HospitalRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HospitalRoomService {

	@Autowired
	private HospitalRoomRepository hrr;

	public HospitalRoom findById(Long id) {
		return hrr.findById(id).get();
	}
	
	public List<HospitalRoom> findAll() {
		return hrr.findAll();
	}
	
	public HospitalRoom findByName(String name) {
		return hrr.findByName(name);
	}

	public HospitalRoom save(HospitalRoom room) {
		return hrr.save(room);
	}
	
	public void delete(HospitalRoom room) {
		hrr.delete(room);
	}
	
	public List<HospitalRoom> findByClinicId(Long id) {
		return hrr.findByClinicId(id);
	}

}
