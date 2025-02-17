package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.entity.CommentEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.entity.ForumEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "Patient")
@Table(name = "patient")
@Data
@NoArgsConstructor
public class PatientEntity {

    @Id
    @Column(nullable = false)
    private UUID userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionsAndAnswers;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ForumEntity> createdForums;

    @ManyToMany
    @JoinTable(
            name = "patient_likes_forum",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "forum_id")
    )
    @JsonManagedReference // Permite a serialização pelo lado do paciente
    private List<ForumEntity> likedForums;

    @ManyToMany
    @JoinTable(
            name = "patient_saves_forum",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "forum_id")
    )
    @JsonManagedReference // Permite a serialização pelo lado do paciente
    private List<ForumEntity> savedForums;

    // Novo relacionamento: Um paciente pode ter N comentários
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CommentEntity> comments;
}
