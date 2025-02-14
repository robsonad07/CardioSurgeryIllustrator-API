package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity.ModuleEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetModuleByIdUseCase {

    @Autowired
    private ModuleRepository moduleRepository;

    public Optional<ModuleEntity> execute (UUID moduleId) {
        return this.moduleRepository.findById(moduleId);
    }
}
