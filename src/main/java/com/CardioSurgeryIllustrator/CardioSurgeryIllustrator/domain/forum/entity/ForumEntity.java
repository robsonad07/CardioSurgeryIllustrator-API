package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.entity.CommentEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Forum")
@Table(name = "forum")
@Data
@NoArgsConstructor
public class ForumEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String theme;

  private String title;

  private int likesAmount;

  private int commentsAmount;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<CommentEntity> comments;

  @ManyToOne
  @JoinColumn(name = "creator_id", nullable = false)
  @JsonBackReference
  private PatientEntity creator;

  @ManyToMany(mappedBy = "likedForums")
  @JsonBackReference
  private List<PatientEntity> likedByPatients;

  @ManyToMany(mappedBy = "savedForums")
  @JsonBackReference
  private List<PatientEntity> savedByPatients;

}
