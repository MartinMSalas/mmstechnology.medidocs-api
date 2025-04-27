package com.medi.docs.medidocsapi.mapper;

import com.medi.docs.medidocsapi.entity.RecipeEntity;
import com.medi.docs.medidocsapi.model.RecipeRequest;
import com.medi.docs.medidocsapi.model.RecipeResponse;

public final class RecipeMapper {

    private RecipeMapper() {
        // Utility class, no instances allowed
    }

    /**
     * Maps a RecipeEntity to a RecipeResponse.
     *
     * @param entity the RecipeEntity to map
     * @return a RecipeResponse
     */
    public static RecipeResponse toResponse(RecipeEntity entity) {
        return new RecipeResponse(
                entity.getId(),
                entity.getFullName(),
                entity.getDni(),
                entity.getHealthInsurance(),
                entity.getAffiliateNumber(),
                entity.getAge(),
                entity.getPlan(),
                entity.getRp1Medication(),
                entity.getRp1Diagnosis(),
                entity.getRp2Medication(),
                entity.getRp2Diagnosis(),
                entity.getCity(),
                entity.getDate(),
                entity.getDoctorName(),
                entity.getDoctorLicenseNumber(),
                entity.getCreateDt(),
                entity.getUpdateDt(),
                entity.getVersion()
        );
    }

    /**
     * Maps a RecipeRequest to a RecipeEntity.
     *
     * @param request the RecipeRequest to map
     * @return a RecipeEntity
     */
    public static RecipeEntity toEntity(RecipeRequest request) {
        RecipeEntity entity = new RecipeEntity();
        entity.setFullName(request.fullName());
        entity.setDni(request.dni());
        entity.setHealthInsurance(request.healthInsurance());
        entity.setAffiliateNumber(request.affiliateNumber());
        entity.setAge(request.age());
        entity.setPlan(request.plan());
        entity.setRp1Medication(request.rp1Medication());
        entity.setRp1Diagnosis(request.rp1Diagnosis());
        entity.setRp2Medication(request.rp2Medication());
        entity.setRp2Diagnosis(request.rp2Diagnosis());
        entity.setCity(request.city() != null ? request.city() : "Catamarca");
        entity.setDate(request.date() != null ? request.date() : java.time.LocalDate.now());
        entity.setDoctorName(request.doctorName() != null ? request.doctorName() : "De La Fuente");
        entity.setDoctorLicenseNumber(request.doctorLicenseNumber() != null ? request.doctorLicenseNumber() : "1968");
        return entity;
    }
}
