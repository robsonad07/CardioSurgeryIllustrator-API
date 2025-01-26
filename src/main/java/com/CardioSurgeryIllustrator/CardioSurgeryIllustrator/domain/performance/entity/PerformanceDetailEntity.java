package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.AnswerEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "performance_detail")
@Data
public class PerformanceDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "performance_id", nullable = false)
    @JsonBackReference("performance-details")
    private PerformanceEntity performance;

    private UUID questionId;

    @Column(nullable = false)
    private AnswerEnum answer;
}
