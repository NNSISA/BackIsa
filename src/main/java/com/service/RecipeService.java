package com.service;

import com.dto.RecipeDTO;
import com.model.Nurse;
import com.model.Recipe;
import com.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RecipeService implements RecipeServiceInterface {

	@Autowired
	private RecipeRepository rr;
	@Autowired
	private MedicalStaffService mss;
	@Autowired
	private RecipeService rs;
	@Autowired
	private EmailService es;

	public Recipe findById(Long id) {
		return rr.findById(id).get();
	}
	
	public Recipe save(Recipe recipe) {
		return rr.save(recipe);
	}

	public List<Recipe> findByAuthenticated(boolean auth) {
		return rr.findByAuthenticated(auth);
	}

	//rollbackFor oznacava za koje izuzetke ce se desiti roolback
	//REQUIRED prikljucuje metodu transakciji ili otvara novu ako transakcija ne postoji
	//READ_UNCOMMITTED eliminise problem poslednje izmene
	//Slanje mail-a medicinskog sestri
	@Transactional(rollbackFor = {
			RuntimeException.class }, readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Recipe authRecipe(RecipeDTO recipeDTO, String username) {
		Recipe recipe = rs.findById(recipeDTO.getId());
		Nurse nurse = (Nurse) mss.findByUsername(username);
		recipe.setNurse(nurse);
		recipe.setAuthenticated(true);
		try {
			es.sendPatientNotificaition9(nurse);
		} catch (Exception e) {
			System.out.println("Poruka nije poslata!");
		}
		return rr.save(recipe);

	}
}
