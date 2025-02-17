package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.dto.CreateModuleDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity.ModuleEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.repository.ModuleRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.entity.SubjectEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.repository.SubjectRepository;

@Service
public class CreateModuleUseCase {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public ModuleEntity execute(CreateModuleDTO createModuleDTO) {

        SubjectEntity subject = subjectRepository.findById(createModuleDTO.getSubjectId())
            .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + createModuleDTO.getSubjectId()));


        ModuleEntity moduleEntity = ModuleEntity.builder()
            .title(createModuleDTO.getTitle())
            .longDescription(createModuleDTO.getLongDescription())
            .isFavorite(createModuleDTO.getIsFavorite())
            .description(createModuleDTO.getDescription())
            .cover(createModuleDTO.getCover())
            .progress(createModuleDTO.getProgress())
            .subject(subject)
            .build();

        return this.moduleRepository.save(moduleEntity);
    }
}
