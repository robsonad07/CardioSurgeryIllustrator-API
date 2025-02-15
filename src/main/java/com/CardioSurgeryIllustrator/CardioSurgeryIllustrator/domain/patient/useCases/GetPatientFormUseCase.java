package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.repository.PatientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class GetPatientFormUseCase {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> execute(UUID userId) throws JsonProcessingException {
        Optional<PatientEntity> optionalPatientEntity = patientRepository.findById(userId);
        if (optionalPatientEntity.isEmpty()){
            throw new RuntimeException("Patient not founded");
        }
        PatientEntity patientEntity = optionalPatientEntity.get();
        return objectMapper.readValue(patientEntity.getQuestionsAndAnswers(), List.class);
    }
}
