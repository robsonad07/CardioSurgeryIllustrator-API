package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity;

import java.util.UUID;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.entity.SubjectEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "Module")
@Table(name = "Module")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String longDescription;

    public Boolean isFavorite;

    private String description;

    private String cover;

    private float progress;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    @JsonBackReference
    private SubjectEntity subject;

    @JsonProperty("subjectId")
    public UUID getSubjectId() {
        return subject != null ? subject.getId() : null;
    }

    @OneToOne(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("module-quiz")
    private QuizEntity quiz;
}
