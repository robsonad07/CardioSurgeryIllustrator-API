package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.entity.SubjectEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.exceptions.SubjectNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.repository.SubjectRepository;

@Service
public class UpdateSubjectUseCase {

    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectEntity execute(UUID subjectId, SubjectEntity updatedSubject) {
        
        Optional<SubjectEntity> optinalSubject = this.subjectRepository.findById(subjectId);

        if(optinalSubject.isEmpty()) {
          throw new SubjectNotFoundException();
        }

        var existingSubject = optinalSubject.get();

        existingSubject.setDescription(updatedSubject.description);
        existingSubject.setTitle(updatedSubject.description);

        return subjectRepository.save(existingSubject);
    }
}