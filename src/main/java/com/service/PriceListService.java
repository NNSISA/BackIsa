package com.service;

import com.model.PriceList;
import com.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceListService {

	@Autowired
	private PriceListRepository plr;

	public List<PriceList> findAll() {
		return plr.findAll();
	}

	public Optional<PriceList> findById(Long id) {
		return plr.findById(id);
	}

	public PriceList save(PriceList priceList) {
		return plr.save(priceList);
	}

}
