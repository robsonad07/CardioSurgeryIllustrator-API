package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.controllers;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.QuestionEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.useCases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private CreateQuestionUseCase createQuestionUseCase;
    @Autowired
    private GetAllQuestionUseCase getAllQuestionUseCase;
    @Autowired
    private GetOneQuestionUseCase getOneQuestionUseCase;
    @Autowired
    private UpdateQuestionUseCase updateQuestionUseCase;
    @Autowired
    private DeleteQuestionUseCase deleteQuestionUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> createQuestion(@RequestBody QuestionEntity question) {
        try {
            QuestionEntity response = createQuestionUseCase.execute(question);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllQuestion() {
        try {
            List<QuestionEntity> response = getAllQuestionUseCase.execute();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-one/{id}")
    public ResponseEntity<Object> getOneQuestion(@PathVariable UUID id) {
        try {
            QuestionEntity response = getOneQuestionUseCase.execute(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateQuestion(@PathVariable UUID id, @RequestBody QuestionEntity questionEntity) {
        try {
            QuestionEntity response = updateQuestionUseCase.execute(id, questionEntity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable UUID id) {
        try {
            UUID response = deleteQuestionUseCase.execute(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
