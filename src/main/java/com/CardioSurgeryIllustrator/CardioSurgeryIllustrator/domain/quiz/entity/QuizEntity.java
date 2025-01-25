package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "Quiz")
@Table(name = "quiz")
@Data
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
}
