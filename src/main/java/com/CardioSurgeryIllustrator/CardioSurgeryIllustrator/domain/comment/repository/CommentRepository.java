package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID>{
  
}
