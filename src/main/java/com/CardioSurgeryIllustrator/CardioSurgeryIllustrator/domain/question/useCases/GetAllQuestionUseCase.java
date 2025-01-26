package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.QuestionEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllQuestionUseCase {
    @Autowired
    private QuestionRepository questionRepository;

    public List<QuestionEntity> execute() {
        return questionRepository.findAll();
    }
}
