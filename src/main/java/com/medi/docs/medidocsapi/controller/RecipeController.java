package com.medi.docs.medidocsapi.controller;


import com.medi.docs.medidocsapi.model.RecipeRequest;
import com.medi.docs.medidocsapi.model.RecipeResponse;
import com.medi.docs.medidocsapi.service.RecipeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody @Valid RecipeRequest recipeRequest) {
        logger.info("Received new recipe request for patient: {}", recipeRequest.fullName());

        // Save recipe using the service
        RecipeResponse savedRecipe = recipeService.createRecipe(recipeRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe.toString());
    }
}
