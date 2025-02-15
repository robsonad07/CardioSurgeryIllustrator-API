package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.controller;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.dto.CreateForumDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.entity.ForumEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.repository.ForumRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.useCases.CreateForumUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.useCases.LikeForumUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.useCases.SaveForumUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private CreateForumUseCase createForumUseCase;

    @Autowired
    private LikeForumUseCase likeForumUseCase;

    @Autowired
    private SaveForumUseCase saveForumUseCase;

    @Autowired
    private ForumRepository forumRepository;

    @PostMapping("/")
    public ResponseEntity<ForumEntity> createForum(@Valid @RequestBody CreateForumDTO createForum) {
        ForumEntity forum = createForumUseCase.execute(createForum);
        return ResponseEntity.ok(forum);
    }

    @GetMapping("/")
    public ResponseEntity<List<ForumEntity>> getAllForums() {
        List<ForumEntity> forums = forumRepository.findAll();
        return ResponseEntity.ok(forums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumEntity> getForumById(@PathVariable UUID id) {
        return forumRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{forumId}/like/{patientId}")
    public ResponseEntity<ForumEntity> likeForum(@PathVariable UUID forumId, @PathVariable UUID patientId) {
        likeForumUseCase.execute(patientId, forumId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{forumId}/save/{patientId}")
    public ResponseEntity<ForumEntity> saveForum(@PathVariable UUID forumId, @PathVariable UUID patientId) {
        saveForumUseCase.execute(patientId, forumId);
        return ResponseEntity.ok().build();
    }
}
