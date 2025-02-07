package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.controllers;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.dto.FormResponseRequest;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.entity.FormResponseEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.useCases.CreateFormResponseUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.useCases.GetFormResponseUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/form-response")
public class FormResponseController {
    @Autowired
    private CreateFormResponseUseCase createFormResponseUseCase;
    @Autowired
    private GetFormResponseUseCase getFormResponseUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> createFormResponse(@RequestBody @Valid FormResponseRequest request) {
        try {
            FormResponseEntity response = createFormResponseUseCase.execute(request.userId(), request.questionsAndAnswers());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/form/{userId}")
    public ResponseEntity<Object> getFormResponseByUserId(@PathVariable UUID userId) {
        try {
            List<Map<String, Object>> response = getFormResponseUseCase.execute(userId);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}