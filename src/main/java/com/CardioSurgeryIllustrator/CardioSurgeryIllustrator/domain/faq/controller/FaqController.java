package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.controller;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.entity.FaqEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.useCase.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/faq")
public class FaqController {
    @Autowired
    private CreateFaqUseCase createFaqUseCase;

    @Autowired
    private GetOneFaqUseCase getOneFaqUseCase;

    @Autowired
    private GetAllFaqUseCase getAllFaqUseCase;

    @Autowired
    private UpdateFaqUseCase updateFaqUseCase;

    @Autowired
    private DeleteFaqUseCase deleteFaqUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> createFaq(@RequestBody FaqEntity faqEntity) {
        try {
            FaqEntity response = createFaqUseCase.execute(faqEntity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllFaq() {
        try {
            List<FaqEntity> response = getAllFaqUseCase.execute();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-one/{id}")
    public ResponseEntity<Object> getOneFaq(@PathVariable UUID id) {
        try {
            FaqEntity response = getOneFaqUseCase.execute(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateFaq(@PathVariable UUID id, @RequestBody FaqEntity faqEntity) {
        try {
            FaqEntity response = updateFaqUseCase.execute(id, faqEntity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFaq(@PathVariable UUID id) {
        try {
            UUID response = deleteFaqUseCase.execute(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
