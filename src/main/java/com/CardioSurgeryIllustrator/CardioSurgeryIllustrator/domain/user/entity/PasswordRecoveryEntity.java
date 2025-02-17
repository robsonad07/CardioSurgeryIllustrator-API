package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity(name = "PasswordRecovery")
@Table(name = "password_recovery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRecoveryEntity {
    @Id
    private String email;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "generation_moment", nullable = false)
    private Instant generationMoment;
}
