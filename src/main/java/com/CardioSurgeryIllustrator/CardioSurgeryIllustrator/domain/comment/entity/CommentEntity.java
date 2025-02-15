package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.entity;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.entity.ForumEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "Comment")
@Table(name = "comment")
@Data
@NoArgsConstructor
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String content;

  @ManyToOne
  @JoinColumn(name = "forum_id", nullable = false)
  @JsonBackReference
  private ForumEntity forum;

  @ManyToOne
  @JoinColumn(name = "patient_id", nullable = false)
  @JsonBackReference
  private PatientEntity patient;
}
