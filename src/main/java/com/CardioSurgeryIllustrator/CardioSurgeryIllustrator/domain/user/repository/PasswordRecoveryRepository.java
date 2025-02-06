package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.repository;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.entity.PasswordRecoveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecoveryEntity, String> {
}
