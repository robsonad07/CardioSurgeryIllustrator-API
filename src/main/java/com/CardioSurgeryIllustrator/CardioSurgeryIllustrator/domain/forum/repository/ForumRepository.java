package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.entity.ForumEntity;

public interface ForumRepository extends JpaRepository<ForumEntity, UUID>{
  
}
