package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.exceptions.QuizNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateQuizUseCase {
    @Autowired
    private QuizRepository quizRepository;

    public QuizEntity execute(UUID id, QuizEntity quizEntity) {
        Optional<QuizEntity> optionalQuizEntity = quizRepository.findById(id);

        if(optionalQuizEntity.isEmpty()) {
            throw new QuizNotFoundException();
        }

        QuizEntity tempQuizEntity = optionalQuizEntity.get();

        tempQuizEntity.setTitle(quizEntity.getTitle());
        tempQuizEntity.setDescription(quizEntity.getDescription());

        return quizRepository.save(tempQuizEntity);
    }
}
