package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.repository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizRepository extends JpaRepository<QuizEntity, UUID> {
}
