package com.medi.docs.medidocsapi.service.impl;

import com.medi.docs.medidocsapi.entity.RecipeEntity;
import com.medi.docs.medidocsapi.mapper.RecipeMapper;
import com.medi.docs.medidocsapi.model.RecipeRequest;
import com.medi.docs.medidocsapi.model.RecipeResponse;
import com.medi.docs.medidocsapi.repository.RecipeRepository;
import com.medi.docs.medidocsapi.service.RecipeService;
import com.medi.docs.medidocsapi.util.PdfGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public RecipeResponse createRecipe(RecipeRequest request) {
        // Map RecipeRequest to RecipeEntity
        RecipeEntity recipeEntity = RecipeMapper.toEntity(request);

        // Save the entity to the database
        RecipeEntity savedEntity = recipeRepository.save(recipeEntity);

        // Map to response
        RecipeResponse response = RecipeMapper.toResponse(savedEntity);

        // Generate the PDF automatically
        byte[] pdfContent = PdfGenerator.generateRecipePdf(response);

        // Here, you can now use pdfContent to send by email (future step)
        sendRecipeByEmail(response, pdfContent);

        return response;
    }

    private void sendRecipeByEmail(RecipeResponse recipe, byte[] pdfContent) {
        // TODO: Implement email sending here
    }
}
