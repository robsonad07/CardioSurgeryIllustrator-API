package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "performance")
@Data
public class PerformanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Float score;

    private UUID userId;

    private UUID quizId;

    @OneToMany(mappedBy = "performance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("performance-details")
    private List<PerformanceDetailEntity> details;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime responseDate;
}
