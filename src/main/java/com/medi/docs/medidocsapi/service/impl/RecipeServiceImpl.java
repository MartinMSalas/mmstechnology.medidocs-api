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

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        // Map the saved entity to RecipeResponse
        RecipeResponse response = RecipeMapper.toResponse(savedEntity);

        // Generate PDF based on RecipeResponse
        byte[] pdfContent = PdfGenerator.generateRecipePdf(response);

        // Save the generated PDF to disk
        savePdfToDisk(pdfContent, savedEntity.getId());

        // (Future) Send the PDF by email to the user and admin
        // sendRecipeByEmail(response, pdfContent);

        return response;
    }

    /**
     * Saves the generated PDF to the disk in the 'pdfs' directory.
     *
     * @param pdfContent the content of the PDF
     * @param recipeId   the ID of the saved recipe
     */
    private void savePdfToDisk(byte[] pdfContent, Long recipeId) {
        try {
            Path pdfDirectory = Paths.get("pdfs");
            if (!Files.exists(pdfDirectory)) {
                Files.createDirectories(pdfDirectory); // Create directory if it doesn't exist
            }

            Path pdfPath = pdfDirectory.resolve("recipe-" + recipeId + ".pdf");
            try (FileOutputStream fos = new FileOutputStream(pdfPath.toFile())) {
                fos.write(pdfContent);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save PDF to disk", e);
        }
    }

    /**
     * Placeholder method to send the PDF by email.
     *
     * @param recipe     the recipe information
     * @param pdfContent the generated PDF content
     */
    private void sendRecipeByEmail(RecipeResponse recipe, byte[] pdfContent) {
        // TODO: Implement Email sending here
    }
}
