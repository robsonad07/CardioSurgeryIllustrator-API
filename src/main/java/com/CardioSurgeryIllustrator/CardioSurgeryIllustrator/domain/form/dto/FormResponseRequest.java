package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.dto;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.entity.QuestionAndAnswer;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record FormResponseRequest(@NotNull UUID userId, @NotNull List<QuestionAndAnswer> questionsAndAnswers) {
}
