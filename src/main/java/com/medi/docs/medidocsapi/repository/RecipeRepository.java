package com.medi.docs.medidocsapi.repository;

import com.medi.docs.medidocsapi.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByPatientId, findByDoctorId, etc.
}
