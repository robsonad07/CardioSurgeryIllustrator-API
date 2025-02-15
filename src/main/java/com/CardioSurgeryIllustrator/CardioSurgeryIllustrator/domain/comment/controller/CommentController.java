package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.controller;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.dto.CreateCommentDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.entity.CommentEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.repository.CommentRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.comment.useCases.CreateCommentUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CreateCommentUseCase createCommentUseCase;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/")
    public ResponseEntity<CommentEntity> createComment(@Valid @RequestBody CreateCommentDTO createCommentDTO) {
        CommentEntity comment = createCommentUseCase.execute(createCommentDTO);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/")
    public ResponseEntity<List<CommentEntity>> getAllComments() {
        List<CommentEntity> comments = commentRepository.findAll();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentEntity> getCommentById(@PathVariable UUID id) {
        return commentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

