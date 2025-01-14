package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.repository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
}