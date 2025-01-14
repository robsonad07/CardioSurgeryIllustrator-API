package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity.ModuleEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.exceptions.ModuleNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.repository.ModuleRepository;

@Service
public class ToggleFavoriteModuleUseCase {

    @Autowired
    private ModuleRepository moduleRepository;

    public ModuleEntity execute(UUID moduleId) {

        Optional<ModuleEntity> optionalModule = this.moduleRepository.findById(moduleId);

        if (optionalModule.isEmpty()) {
            throw new ModuleNotFoundException();
        }

        var module = optionalModule.get();

        module.setIsFavorite(module.getIsFavorite() == null ? true : !module.getIsFavorite());

        return moduleRepository.save(module);
    }
}

