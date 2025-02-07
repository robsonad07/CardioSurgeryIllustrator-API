package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "FormResponse")
@Table(name = "form_response")
@Data
public class FormResponseEntity {
    @Id
    @Column(nullable = false)
    private UUID userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionsAndAnswers;
}
