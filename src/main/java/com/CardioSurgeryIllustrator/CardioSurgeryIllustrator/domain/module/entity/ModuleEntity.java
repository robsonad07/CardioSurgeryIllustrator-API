package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.entity.SubjectEntity;

@Entity(name = "Module")
@Table(name = "Module")
@Data
public class ModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    public String longDescription;

    public Boolean isFavorite;

    private String description;

    private String cover;

    private float progress;

    @ManyToOne
    private SubjectEntity subject; 
}
