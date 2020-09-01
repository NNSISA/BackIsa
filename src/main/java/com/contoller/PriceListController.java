package com.contoller;

import com.dto.PriceListDTO;
import com.model.PriceList;
import com.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PriceListController {

	@Autowired
	private PriceListService pls;

	//Vraca listu cena
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getPrices", method = RequestMethod.GET)
	public List<PriceList> getPrices() {
		List<PriceList> list = pls.findAll();
		return pls.findAll();
	}

	//Dodavanje cene
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/add-price", method = RequestMethod.POST)
	public void addPrice(@RequestBody PriceList priceList) {
		pls.save(priceList);
	}

	//Menjanje cene
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/changePrice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PriceListDTO> changeInfo(@RequestBody PriceListDTO priceList) {
		Optional<PriceList> priceList1 = pls.findById(priceList.getId());
		PriceList priceList2 = priceList1.get();
		if (priceList2 != null) {
			priceList2.setPrice(priceList.getPrice());
			pls.save(priceList2);
		} else {
		}
		PriceListDTO p = new PriceListDTO();
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
}
