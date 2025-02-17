package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity.ModuleEntity;

public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID> {
  List<ModuleEntity> findBySubject_Id(UUID subjectId);
}
