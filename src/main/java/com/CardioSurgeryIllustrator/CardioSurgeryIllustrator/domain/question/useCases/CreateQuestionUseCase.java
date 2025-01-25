package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.QuestionEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateQuestionUseCase {
    @Autowired
    private QuestionRepository questionRepository;

    public QuestionEntity execute(QuestionEntity question) {
        return questionRepository.save(question);
    }
}
