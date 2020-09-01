package com.contoller;

import com.dto.DrugDTO;
import com.model.Drug;
import com.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DrugController {

	@Autowired
	private DrugService drs;

	//Dodavanje lekova
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-drug", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveDrug(@RequestBody DrugDTO drugDTO) {
		Drug drug = new Drug();
		drug.setName(drugDTO.getName());
		drug.setPrice(drugDTO.getPrice());
		drug.setQuantity(drugDTO.getQuantity());
		drug = drs.save(drug);
	}
}
