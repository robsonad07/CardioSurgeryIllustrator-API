package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.repository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.entity.FaqEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FaqRepository extends JpaRepository<FaqEntity, UUID> {

}
