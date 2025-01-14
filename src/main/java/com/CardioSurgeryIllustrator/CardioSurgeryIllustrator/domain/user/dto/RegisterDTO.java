package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.dto;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


@Builder
public record RegisterDTO(@NotBlank String name, @NotBlank String email, @NotBlank String password, @NotBlank String phone, UserRole role) {
}
