package com.contoller;

import com.dto.DiagnosisDTO;
import com.model.Diagnosis;
import com.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DiagnosisController {

	@Autowired
	private DiagnosisService dis;

	//Cuvanje dijagnoza
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-diagnosis", method = RequestMethod.POST)
	public void saveDiagnosis(@RequestBody DiagnosisDTO diagnosisDTO) {
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setName(diagnosisDTO.getName());
		diagnosis.setDescription(diagnosisDTO.getDescription());
		diagnosis = dis.save(diagnosis);
	}

	//Dobavljanje svih dijagnoza
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-diagnosis", method = RequestMethod.GET)
	public List<Diagnosis> getDiagnosis() {
		return dis.findAll();
	}
}
