package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity.ModuleEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.repository.ModuleRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.exceptions.SubjectIdNotFoundException;

@Service
public class GetModulesBySubjectId {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<ModuleEntity> execute(UUID subjectId) {
        if (subjectId == null) {
            throw new SubjectIdNotFoundException();
        }

        List<ModuleEntity> modules = moduleRepository.findBySubjectId(subjectId);

        return modules;
    }
}

