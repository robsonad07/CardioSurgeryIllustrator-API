package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.useCases;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GetSavedForumsIdsUseCase {

    @Autowired
    private PatientRepository patientRepository;

    public List<UUID> execute(UUID patientId) {
        PatientEntity patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        return patient.getSavedForums().stream()
                .map(forum -> forum.getId())
                .collect(Collectors.toList());
    }
}