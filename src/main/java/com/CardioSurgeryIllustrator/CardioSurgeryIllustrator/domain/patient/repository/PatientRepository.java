package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {
}
