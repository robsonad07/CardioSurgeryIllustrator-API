package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateCommentDTO(
    @NotNull UUID forumId,
    @NotNull UUID patientId,
    @NotBlank String content
) {}
