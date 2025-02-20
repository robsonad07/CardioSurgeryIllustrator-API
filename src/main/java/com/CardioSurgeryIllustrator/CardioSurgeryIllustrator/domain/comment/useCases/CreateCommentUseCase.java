package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.dto.CreateCommentDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.entity.CommentEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.repository.CommentRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.entity.ForumEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.repository.ForumRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCommentUseCase {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public CommentEntity execute(CreateCommentDTO createCommentDTO) {
        ForumEntity forum = forumRepository.findById(createCommentDTO.forumId())
                .orElseThrow(() -> new IllegalArgumentException("Fórum não encontrado"));

        PatientEntity patient = patientRepository.findById(createCommentDTO.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(createCommentDTO.content());
        commentEntity.setForum(forum);
        commentEntity.setPatient(patient);

        // Salva o comentário no repositório
        CommentEntity commentResponse = commentRepository.save(commentEntity);

        forum.getComments().add(commentResponse);
        forum.setCommentsAmount(forum.getCommentsAmount() + 1);

        forumRepository.save(forum);

        return commentResponse;
    }
}
