package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "Question")
@Table(name = "question")
@Data
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String problem;
    @Column(nullable = false)
    private String alternativeA;
    @Column(nullable = false)
    private String alternativeB;
    @Column(nullable = false)
    private String alternativeC;
    @Column(nullable = false)
    private String alternativeD;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerEnum answer;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonBackReference("quiz-questions")
    private QuizEntity quizEntity;

}
