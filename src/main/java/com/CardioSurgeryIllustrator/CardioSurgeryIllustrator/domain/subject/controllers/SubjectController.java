package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.entity.SubjectEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.useCases.CreateSubjectUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.useCases.DeleteSubjectUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.useCases.ListAllSubjectsUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.useCases.UpdateSubjectUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/subject")
@Tag(name = "Assunto", description = "Informações de assunto")
public class SubjectController {

    @Autowired
    private CreateSubjectUseCase createSubjectUseCase;

    @Autowired
    private DeleteSubjectUseCase deleteSubjectUseCase;

    @Autowired
    private ListAllSubjectsUseCase listAllSubjectsUseCase;

    @Autowired
    private UpdateSubjectUseCase updateSubjectUseCase;

    @PostMapping
    public ResponseEntity<Object> createSubject(@RequestBody SubjectEntity subjectEntity) {
        try {
            var createdSubject = this.createSubjectUseCase.execute(subjectEntity);
            return ResponseEntity.ok().body(createdSubject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Object> deleteSubject(@PathVariable UUID subjectId) {
        try {
            this.deleteSubjectUseCase.execute(subjectId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> listAllSubjects() {
        try {
            var subjects = this.listAllSubjectsUseCase.execute();
            return ResponseEntity.ok().body(subjects);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<Object> updateSubject(
            @PathVariable UUID subjectId,
            @RequestBody SubjectEntity updatedSubject) {
        try {
            var subject = this.updateSubjectUseCase.execute(subjectId, updatedSubject);
            return ResponseEntity.ok().body(subject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
