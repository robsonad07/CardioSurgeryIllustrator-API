package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.entity.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, UUID>  {
   
}
