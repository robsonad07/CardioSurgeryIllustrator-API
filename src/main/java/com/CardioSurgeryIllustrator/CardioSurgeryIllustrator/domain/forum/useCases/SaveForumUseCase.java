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
public class SaveForumUseCase {

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

        if (patient.getSavedForums() == null) {
            patient.setSavedForums(new ArrayList<>());
        }
        if (forum.getSavedByPatients() == null) {
            forum.setSavedByPatients(new ArrayList<>());
        }

        // Verifica se o paciente já salvou o fórum
        boolean alreadySaved = patient.getSavedForums().contains(forum);

        if (alreadySaved) {
            // Se já salvou, remove da lista (desalva)
            patient.getSavedForums().remove(forum);
            forum.getSavedByPatients().remove(patient);
        } else {
            // Se ainda não salvou, adiciona à lista
            patient.getSavedForums().add(forum);
            forum.getSavedByPatients().add(patient);
        }

        // Salva as alterações no banco de dados
        patientRepository.save(patient);
        forumRepository.save(forum);
    }
}
