package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllQuizUseCase {
    @Autowired
    private QuizRepository quizRepository;

    public List<QuizEntity> execute(){
        return quizRepository.findAll();
    }
}
