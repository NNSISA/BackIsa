package com.service;

import com.dto.RecipeDTO;
import com.model.Recipe;
import java.util.List;

public interface RecipeServiceInterface {

	public Recipe findById(Long id);
	
	public Recipe save(Recipe recipe);

	public Recipe authRecipe(RecipeDTO r, String username);
	
	public List<Recipe> findByAuthenticated(boolean auth);
}
