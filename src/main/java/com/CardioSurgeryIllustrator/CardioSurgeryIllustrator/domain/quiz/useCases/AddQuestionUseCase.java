package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.QuestionEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.repository.QuestionRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.exceptions.QuizNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddQuestionUseCase {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public QuestionEntity execute(UUID quizId, UUID questionId) {
        Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);
        Optional<QuizEntity> optionalQuizEntity = quizRepository.findById(quizId);

        if(optionalQuizEntity.isEmpty()) {
            throw new QuizNotFoundException();
        }
        if(optionalQuestionEntity.isEmpty()) {
            throw new QuizNotFoundException();
        }

        QuizEntity quizEntity = optionalQuizEntity.get();
        QuestionEntity questionEntity = optionalQuestionEntity.get();
        var questionList = quizEntity.getQuestionEntityList();

        questionList.add(questionEntity);

        quizEntity.setQuestionEntityList(questionList);

        var quizResult = quizRepository.save(quizEntity);
        questionEntity.setQuizEntity(quizResult);

         return questionRepository.save(questionEntity);
    }
}
