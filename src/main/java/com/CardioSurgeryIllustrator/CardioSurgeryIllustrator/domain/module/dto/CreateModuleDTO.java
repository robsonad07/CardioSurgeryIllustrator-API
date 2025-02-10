package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateModuleDTO {
    private String title;
    private String longDescription;
    private Boolean isFavorite;
    private String description;
    private String cover;
    private float progress;
    private UUID subjectId;
}
