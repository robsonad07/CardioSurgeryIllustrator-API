package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.QuestionAndAnswer;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.repository.PatientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CreatePatientUseCase {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public PatientEntity execute(UUID userId, List<QuestionAndAnswer> questionsAndAnswers) throws JsonProcessingException {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setUserId(userId);
        patientEntity.setQuestionsAndAnswers(objectMapper.writeValueAsString(questionsAndAnswers));
        patientEntity.setCreatedForums(Collections.emptyList());
        patientEntity.setLikedForums(Collections.emptyList());
        patientEntity.setSavedForums(Collections.emptyList());
        patientEntity.setComments(Collections.emptyList());
        return this.patientRepository.save(patientEntity);
    }
}
