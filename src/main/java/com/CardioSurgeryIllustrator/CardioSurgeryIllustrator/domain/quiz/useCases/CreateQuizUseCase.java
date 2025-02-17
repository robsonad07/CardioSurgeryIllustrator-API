package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity.ModuleEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.repository.ModuleRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.dto.CreateQuizDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.repository.QuizRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.QuestionEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateQuizUseCase {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public QuizEntity execute(CreateQuizDTO createQuizDTO) {

        ModuleEntity module = moduleRepository.findById(createQuizDTO.getModuleId())
                .orElseThrow(() -> new IllegalArgumentException("Module not found with ID: " + createQuizDTO.getModuleId()));

        QuestionEntity question = questionRepository.findById(createQuizDTO.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + createQuizDTO.getQuestionId()));

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setTitle(createQuizDTO.getTitle());
        quizEntity.setDescription(createQuizDTO.getDescription());
        quizEntity.setModule(module);

        List<QuestionEntity> questionsList = new ArrayList<>();

        questionsList.add(question);

        quizEntity.setQuestionEntityList(questionsList);

        var quizResult = quizRepository.save(quizEntity);
        question.setQuizEntity(quizResult);

        questionRepository.save(question);

        return quizEntity;
    }
}
