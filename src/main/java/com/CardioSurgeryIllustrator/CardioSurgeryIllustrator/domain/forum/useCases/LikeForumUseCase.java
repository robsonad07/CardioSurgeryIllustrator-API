package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.entity.ForumEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.forum.repository.ForumRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.entity.PatientEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class LikeForumUseCase {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public void execute(UUID patientId, UUID forumId) {
        PatientEntity patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        ForumEntity forum = forumRepository.findById(forumId)
                .orElseThrow(() -> new IllegalArgumentException("Fórum não encontrado"));

        // Garante que as listas não sejam nulas
        if (patient.getLikedForums() == null) {
            patient.setLikedForums(new ArrayList<>());
        }
        if (forum.getLikedByPatients() == null) {
            forum.setLikedByPatients(new ArrayList<>());
        }

        // Verifica se o paciente já curtiu o fórum
        boolean alreadyLiked = patient.getLikedForums().contains(forum);

        if (alreadyLiked) {
            // Se já curtiu, remove da lista (descurtir)
            patient.getLikedForums().remove(forum);
            forum.getLikedByPatients().remove(patient);
            forum.setLikesAmount(forum.getLikesAmount() - 1);
        } else {
            // Se ainda não curtiu, adiciona à lista
            patient.getLikedForums().add(forum);
            forum.getLikedByPatients().add(patient);
            forum.setLikesAmount(forum.getLikesAmount() + 1);
        }

        // Salva as alterações no banco de dados
        patientRepository.save(patient);
        forumRepository.save(forum);
    }
}
