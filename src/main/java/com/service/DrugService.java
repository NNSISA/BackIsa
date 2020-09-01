package com.service;

import com.model.Drug;
import com.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {

	@Autowired
	private DrugRepository dr;

	public List<Drug> findAll() {
		return dr.findAll();
	}

	public Drug findById(Long id) {
		return dr.findById(id).get();
	}

	public Drug save(Drug drug) {
		return dr.save(drug);
	}

}
