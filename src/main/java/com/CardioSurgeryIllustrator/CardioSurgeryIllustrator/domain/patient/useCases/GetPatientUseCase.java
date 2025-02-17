package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.useCases;


import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetPatientUseCase {
    @Autowired
    public PatientRepository patientRepository;

    public Optional<PatientEntity> execute(UUID id) {
        return this.patientRepository.findById(id);
    }
}
