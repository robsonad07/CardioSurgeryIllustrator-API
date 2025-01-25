package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.controllers;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.useCases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private CreateQuizUseCase createQuizUseCase;

    @Autowired
    private GetAllQuizUseCase getAllQuizUseCase;

    @Autowired
    private GetOneQuizUseCase getOneQuizUseCase;

    @Autowired
    private UpdateQuizUseCase updateQuizUseCase;

    @Autowired
    private DeleteQuizUseCase deleteQuizUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> createQuiz(@RequestBody QuizEntity quizEntity) {
        try {
           QuizEntity response = createQuizUseCase.execute(quizEntity);
           return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllQuiz() {
        try{
            List<QuizEntity> response =getAllQuizUseCase.execute();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-one/{id}")
    public ResponseEntity<Object> getOneQuiz(@PathVariable UUID id) {
        try {
            QuizEntity response = getOneQuizUseCase.execute(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<Object> updateQuiz(@PathVariable UUID id, @RequestBody QuizEntity quizEntity) {
        try {
            QuizEntity response = updateQuizUseCase.execute(id, quizEntity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteQuiz(@PathVariable UUID id) {
        try {
            UUID response = deleteQuizUseCase.execute(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
