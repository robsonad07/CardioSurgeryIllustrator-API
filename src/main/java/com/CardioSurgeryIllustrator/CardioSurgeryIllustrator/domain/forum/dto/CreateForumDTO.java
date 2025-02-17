package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;
import java.util.Collections;
import java.util.List;

public record CreateForumDTO(
    @NotNull UUID creatorId,
    @NotBlank String theme,
    @NotBlank String title
) {
    public List<UUID> likedForums() {
        return Collections.emptyList(); 
    }

    public List<UUID> savedForums() {
        return Collections.emptyList(); 
    }
}
