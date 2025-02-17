package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.repository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PerformanceRepository extends JpaRepository<PerformanceEntity, UUID> {
    @Query("SELECT p FROM PerformanceEntity p WHERE p.userId = :userId AND p.responseDate BETWEEN :startDate AND :endDate")
    List<PerformanceEntity> findByUserIdAndResponseDateBetween(
            @Param("userId") UUID userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
