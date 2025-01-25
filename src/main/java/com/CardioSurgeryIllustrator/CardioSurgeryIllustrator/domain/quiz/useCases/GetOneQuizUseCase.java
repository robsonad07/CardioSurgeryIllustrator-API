package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.exceptions.QuizNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetOneQuizUseCase {
    @Autowired
    private QuizRepository quizRepository;

    public QuizEntity execute(UUID id) {
        Optional<QuizEntity> quizEntity = quizRepository.findById(id);
        if (quizEntity.isEmpty()) {
            throw new QuizNotFoundException();
        }
        return quizEntity.get();
    }
}
