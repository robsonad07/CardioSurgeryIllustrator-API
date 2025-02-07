package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.entity.FormResponseEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.entity.QuestionAndAnswer;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.repository.FormResponseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CreateFormResponseUseCase {
    @Autowired
    private FormResponseRepository formResponseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public FormResponseEntity execute(UUID userId, List<QuestionAndAnswer> questionsAndAnswers) throws JsonProcessingException {
        FormResponseEntity formResponseEntity = new FormResponseEntity();
        formResponseEntity.setUserId(userId);
        formResponseEntity.setQuestionsAndAnswers(objectMapper.writeValueAsString(questionsAndAnswers));
        return this.formResponseRepository.save(formResponseEntity);
    }
}
