package com.medi.docs.medidocsapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record RecipeRequest(
        @NotBlank String fullName,
        @NotBlank String dni,
        @NotBlank String healthInsurance,
        @NotBlank String affiliateNumber,
        Integer age,
        String plan,
        @NotBlank String rp1Medication,
        @NotBlank String rp1Diagnosis,
        String rp2Medication,
        String rp2Diagnosis,
        String city,
        LocalDate date,
        String doctorName,
        String doctorLicenseNumber
) {}
