package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "Faq")
@Table(name = "faq")
@Data
public class FaqEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String question;
    private String response;
}
