package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.QuestionEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.exceptions.QuestionNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteQuestionUseCase {
    @Autowired
    private QuestionRepository questionRepository;

    public UUID execute(UUID id) {
        Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(id);

        if (optionalQuestionEntity.isEmpty()) {
            throw new QuestionNotFoundException();
        }

        questionRepository.deleteById(id);
        return id;
    }
}
