package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.entity.FormResponseEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.repository.FormResponseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class GetFormResponseUseCase {
    @Autowired
    private FormResponseRepository formResponseRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public List<Map<String, Object>> execute(UUID userId) throws JsonProcessingException {
        Optional<FormResponseEntity> optionalFormResponseEntity = formResponseRepository.findById(userId);
        if (optionalFormResponseEntity.isEmpty()){
            throw new RuntimeException("Form not founded");
        }
        FormResponseEntity formResponseEntity = optionalFormResponseEntity.get();
        return objectMapper.readValue(formResponseEntity.getQuestionsAndAnswers(), List.class);
    }
}
