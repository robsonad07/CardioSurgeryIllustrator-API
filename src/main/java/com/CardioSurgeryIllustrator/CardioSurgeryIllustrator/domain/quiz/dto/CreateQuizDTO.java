package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.dto;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuizDTO {
    private String title;
    private String description;
    private UUID moduleId;
    private UUID questionId; // Lista de IDs das questões
}
