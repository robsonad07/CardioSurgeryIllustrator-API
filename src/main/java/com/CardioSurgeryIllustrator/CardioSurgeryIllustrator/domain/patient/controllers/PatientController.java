package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.controllers;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.useCases.GetPatientUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.dto.PatientRequest;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.useCases.CreatePatientUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.useCases.GetPatientFormUseCase;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private CreatePatientUseCase createPatientUseCase;
    @Autowired
    private GetPatientFormUseCase getPatientFormUseCase;
    @Autowired
    private GetPatientUseCase getPatientUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> createPatient(@RequestBody @Valid PatientRequest request) {
        try {
            PatientEntity response = createPatientUseCase.execute(request.userId(), request.questionsAndAnswers());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/form/{userId}")
    public ResponseEntity<Object> getPatientFormByUserId(@PathVariable UUID userId) {
        try {
            List<Map<String, Object>> response = getPatientFormUseCase.execute(userId);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getPatientByUserId(@PathVariable UUID userId) {
        try {
            var response = getPatientUseCase.execute(userId);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}