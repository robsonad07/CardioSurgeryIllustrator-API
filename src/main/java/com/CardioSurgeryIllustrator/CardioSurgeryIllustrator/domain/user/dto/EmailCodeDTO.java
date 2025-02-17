package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailCodeDTO(@NotBlank String email, String code, String password) {
}
