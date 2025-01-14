package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.entity.SubjectEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.repository.SubjectRepository;

@Service
public class CreateSubjectUseCase {

    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectEntity execute(SubjectEntity subjectEntity) {
        return this.subjectRepository.save(subjectEntity);
    }
}
