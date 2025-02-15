package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.dto.CreateForumDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.entity.ForumEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.repository.ForumRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CreateForumUseCase {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private PatientRepository patientRepository;

    public ForumEntity execute(CreateForumDTO createForum) {

        PatientEntity creator = patientRepository.findById(createForum.creatorId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        // Criação do fórum
        ForumEntity forumEntity = new ForumEntity();
        forumEntity.setTheme(createForum.theme());
        forumEntity.setTitle(createForum.title());
        forumEntity.setCreator(creator);
        forumEntity.setLikesAmount(0);
        forumEntity.setCommentsAmount(0);
        forumEntity.setComments(Collections.emptyList());
        forumEntity.setLikedByPatients(Collections.emptyList()); 
        forumEntity.setSavedByPatients(Collections.emptyList()); 

        return forumRepository.save(forumEntity);
    }
}
