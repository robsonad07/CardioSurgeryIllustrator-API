package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.entity.SubjectEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "Module")
@Table(name = "Module")
@Data
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
}
