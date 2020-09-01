package com.contoller;

import com.dto.RecipeDTO;
import com.model.Appointment;
import com.model.Drug;
import com.model.Recipe;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RecipeController {

	@Autowired
	private RecipeService rs;
	@Autowired
	private DrugService drs;
	@Autowired
	private AppointmentService as;

	//Vraca listu neoverenih recepata
	@RequestMapping(method = RequestMethod.GET, value = "/r")
	public List<Recipe> getRecipes() {

		List<Recipe> recipes = rs.findByAuthenticated(false);
		return recipes;
	}

	//Dodavanje leka u recept
	@RequestMapping(method = RequestMethod.GET, value = "/api/get-recipes-dto")
	public List<RecipeDTO> getRecipesDTO() {
		List<Recipe> recipes = rs.findByAuthenticated(false);
		List<RecipeDTO> ret = new ArrayList<>();
		for (Recipe r : recipes) {
			RecipeDTO recipeDTO = new RecipeDTO();
			recipeDTO.setId(r.getId());
			recipeDTO.setDescription(r.getDescription());
			String drugs = "";
			for (Drug d : r.getDrug()) {
				drugs += d.getName() + " | ";
			}
			recipeDTO.setDrugString(drugs);
			ret.add(recipeDTO);
		}
		return ret;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/get-drug/{id}")
	public Drug getDrug(@PathVariable Long id) {
		Drug drug = new Drug();
		Recipe recipes = rs.findById(id);
		return drug;
	}

	//Overa recepta
	@RequestMapping(method = RequestMethod.POST, value = "/api/auth-recipe/{username}")
	public @ResponseBody ResponseEntity authRecipe(@RequestBody RecipeDTO recipeDTO, @PathVariable String username) {
		try {
			Recipe recipe = rs.authRecipe(recipeDTO, username);
			try {
				Appointment app = as.findById(recipe.getAppointment().getId());
				Appointment appointment = as.setFinished(app);
			} catch (Exception e) {
			}
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//Vraca lekove
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-drugs", method = RequestMethod.GET)
	public List<Drug> getDrugs() {
		return drs.findAll();
	}
}
