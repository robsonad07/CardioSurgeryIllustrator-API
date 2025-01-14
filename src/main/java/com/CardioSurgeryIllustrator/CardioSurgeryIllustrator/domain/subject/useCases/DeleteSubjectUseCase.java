package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.entity.SubjectEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.exceptions.SubjectNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.repository.SubjectRepository;

@Service
public class DeleteSubjectUseCase {

    @Autowired
    private SubjectRepository subjectRepository;

    public void execute(UUID subjectId) {
        
        Optional<SubjectEntity> optinalSubject = this.subjectRepository.findById(subjectId);

        if(optinalSubject.isEmpty()) {
          throw new SubjectNotFoundException();
        }

       subjectRepository.deleteById(subjectId);
    }
}
