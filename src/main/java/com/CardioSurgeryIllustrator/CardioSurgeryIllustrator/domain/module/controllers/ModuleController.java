package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.dto.CreateModuleDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity.ModuleEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases.CreateModuleUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases.GetAllModulesUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases.GetModulesBySubjectId;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases.ToggleFavoriteModuleUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases.UpdateModuleUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/module")
@Tag(name = "Módulo", description = "Informações de módulo")
public class ModuleController {

    @Autowired
    private CreateModuleUseCase createModuleUseCase;

    @Autowired
    private GetModulesBySubjectId getModulesBySubjectId;

    @Autowired
    private ToggleFavoriteModuleUseCase toggleFavoriteModuleUseCase;

    @Autowired
    private UpdateModuleUseCase updateModuleUseCase;

    @Autowired
    private GetAllModulesUseCase getAllModulesUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> createModule(@RequestBody CreateModuleDTO createModuleDTO) {
        try {
            var createdModule = this.createModuleUseCase.execute(createModuleDTO);
            return ResponseEntity.ok().body(createdModule);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/by-subject/{subjectId}")
    public ResponseEntity<Object> getModulesBySubjectId(@PathVariable UUID subjectId) {
        try {
            var modules = this.getModulesBySubjectId.execute(subjectId);
            return ResponseEntity.ok().body(modules);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllModules() {
        try {
            var modules = this.getAllModulesUseCase.execute();
            return ResponseEntity.ok().body(modules);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{moduleId}/toggle-favorite")
    public ResponseEntity<Object> toggleFavorite(@PathVariable UUID moduleId) {
        try {
            var updatedModule = this.toggleFavoriteModuleUseCase.execute(moduleId);
            return ResponseEntity.ok().body(updatedModule);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{moduleId}")
    public ResponseEntity<Object> updateModule(
            @PathVariable UUID moduleId,
            @RequestBody ModuleEntity updatedModule) {
        try {
            var module = this.updateModuleUseCase.execute(moduleId, updatedModule);
            return ResponseEntity.ok().body(module);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

