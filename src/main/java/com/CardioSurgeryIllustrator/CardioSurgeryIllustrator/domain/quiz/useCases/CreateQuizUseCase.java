package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateQuizUseCase {
    @Autowired
    private QuizRepository quizRepository;

    public QuizEntity execute(QuizEntity quizEntity){
        return this.quizRepository.save(quizEntity);
    }
}
