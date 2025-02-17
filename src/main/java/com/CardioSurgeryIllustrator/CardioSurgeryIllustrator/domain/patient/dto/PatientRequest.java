package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.QuestionAndAnswer;

public record PatientRequest(@NotNull UUID userId, @NotNull List<QuestionAndAnswer> questionsAndAnswers) {
}
