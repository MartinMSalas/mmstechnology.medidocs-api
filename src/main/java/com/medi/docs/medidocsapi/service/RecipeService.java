package com.medi.docs.medidocsapi.service;

import com.medi.docs.medidocsapi.model.RecipeRequest;
import com.medi.docs.medidocsapi.model.RecipeResponse;

public interface RecipeService {

    /**
     * Creates a new recipe, generates its PDF and prepares for email sending.
     *
     * @param request the recipe request
     * @return the saved recipe response
     */
    RecipeResponse createRecipe(RecipeRequest request);
}
