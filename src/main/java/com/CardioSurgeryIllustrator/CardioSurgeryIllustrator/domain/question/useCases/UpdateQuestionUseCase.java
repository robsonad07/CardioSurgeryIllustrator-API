package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.QuestionEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.exceptions.QuestionNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateQuestionUseCase {
    @Autowired
    private QuestionRepository questionRepository;

    public QuestionEntity execute(UUID id, QuestionEntity question) {
        Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(id);
        if(optionalQuestionEntity.isEmpty()) {
            throw new QuestionNotFoundException();
        }

        QuestionEntity questionEntity = optionalQuestionEntity.get();
        questionEntity.setProblem(question.getProblem());
        questionEntity.setAlternativeA(question.getAlternativeA());
        questionEntity.setAlternativeB(question.getAlternativeB());
        questionEntity.setAlternativeC(question.getAlternativeC());
        questionEntity.setAlternativeD(question.getAlternativeD());
        questionEntity.setAnswer(question.getAnswer());
        questionEntity.setQuizEntity(question.getQuizEntity());

        questionRepository.save(questionEntity);
        return questionEntity;
    }
}
