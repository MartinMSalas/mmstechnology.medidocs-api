package com.medi.docs.medidocsapi.service;

import com.medi.docs.medidocsapi.model.RecipeRequest;
import com.medi.docs.medidocsapi.model.RecipeResponse;

public interface RecipeService {
    RecipeResponse createRecipe(RecipeRequest recipeRequest);
}
