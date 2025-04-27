package com.medi.docs.medidocsapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String healthInsurance;

    @Column(nullable = false)
    private String affiliateNumber;

    private Integer age;

    private String plan;

    @Column(nullable = false)
    private String rp1Medication;

    @Column(nullable = false)
    private String rp1Diagnosis;

    private String rp2Medication;

    private String rp2Diagnosis;

    private String city;

    /**
     * Date of emission selected by the user.
     */
    private LocalDate date;

    private String doctorName;

    private String doctorLicenseNumber;

    /**
     * Timestamp when the record was created.
     * Automatically set when the entity is persisted.
     */
    @CreatedDate
    @Column(name = "create_dt", updatable = false)
    private LocalDateTime createDt;

    /**
     * Timestamp when the record was last updated.
     * Automatically updated on entity modification.
     */
    @LastModifiedDate
    @Column(name = "update_dt")
    private LocalDateTime updateDt;

    /**
     * Version field for optimistic locking.
     */
    @Version
    private Integer version;
}
