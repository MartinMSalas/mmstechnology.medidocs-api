package com.medi.docs.medidocsapi.controller;

import com.medi.docs.medidocsapi.model.RecipeRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody @Valid RecipeRequest recipeRequest) {

        // 1. Completar valores por defecto si no vinieron
        String city = (recipeRequest.city() != null) ? recipeRequest.city() : "Catamarca";
        LocalDate date = (recipeRequest.date() != null) ? recipeRequest.date() : LocalDate.now();
        String doctorName = (recipeRequest.doctorName() != null) ? recipeRequest.doctorName() : "De La Fuente";
        String doctorLicenseNumber = (recipeRequest.doctorLicenseNumber() != null) ? recipeRequest.doctorLicenseNumber() : "1968";

        // 2. Loguear los datos importantes
        logger.info("Nueva receta recibida :");
        logger.info("Paciente: {}", recipeRequest.fullName());
        logger.info("Medicamento 1: {}", recipeRequest.rp1Medication());
        if (recipeRequest.rp2Medication() != null) {
            logger.info("Medicamento 2: {}", recipeRequest.rp2Medication());
        }
        logger.info("Ciudad: {}", city);
        logger.info("Fecha: {}", date);
        logger.info("Doctor: {}", doctorName);
        logger.info("Matrícula: {}", doctorLicenseNumber);

        // 3. Retornar respuesta simple
        return ResponseEntity.status(HttpStatus.CREATED).body("Receta procesada exitosamente.");
    }
}
