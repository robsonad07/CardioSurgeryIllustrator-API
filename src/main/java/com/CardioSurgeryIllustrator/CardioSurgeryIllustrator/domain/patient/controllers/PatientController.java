package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.controllers;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.useCases.GetLikedForumsIdsUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.useCases.GetSavedForumsIdsUseCase;
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

    @Autowired
    private GetLikedForumsIdsUseCase getLikedForumsIdsUseCase;

    @Autowired
    private GetSavedForumsIdsUseCase getSavedForumsIdsUseCase;

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

    // 🔥 **Novo endpoint para buscar os IDs dos fóruns curtidos pelo paciente**
    @GetMapping("/{userId}/forum/liked")
    public ResponseEntity<Object> getLikedForumsByUserId(@PathVariable UUID userId) {
        try {
            List<UUID> likedForums = getLikedForumsIdsUseCase.execute(userId);
            return ResponseEntity.ok().body(likedForums);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔥 **Novo endpoint para buscar os IDs dos fóruns salvos pelo paciente**
    @GetMapping("/{userId}/forum/saved")
    public ResponseEntity<Object> getSavedForumsByUserId(@PathVariable UUID userId) {
        try {
            List<UUID> savedForums = getSavedForumsIdsUseCase.execute(userId);
            return ResponseEntity.ok().body(savedForums);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
