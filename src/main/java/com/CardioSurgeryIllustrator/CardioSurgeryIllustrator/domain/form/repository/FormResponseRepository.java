package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.repository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.form.entity.FormResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FormResponseRepository extends JpaRepository<FormResponseEntity, UUID> {
}
