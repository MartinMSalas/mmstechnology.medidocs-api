package com.medi.docs.medidocsapi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RecipeResponse(
        Long id,
        String fullName,
        String dni,
        String healthInsurance,
        String affiliateNumber,
        Integer age,
        String plan,
        String rp1Medication,
        String rp1Diagnosis,
        String rp2Medication,
        String rp2Diagnosis,
        String city,
        LocalDate date,
        String doctorName,
        String doctorLicenseNumber,
        LocalDateTime createDt,
        LocalDateTime updateDt,
        Integer version
) {
}
