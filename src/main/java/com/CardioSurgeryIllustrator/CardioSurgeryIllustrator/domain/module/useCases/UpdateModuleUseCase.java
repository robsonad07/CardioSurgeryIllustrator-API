package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity.ModuleEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.exceptions.ModuleNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.repository.ModuleRepository;

@Service
public class UpdateModuleUseCase {

    @Autowired
    private ModuleRepository moduleRepository;

    public ModuleEntity execute(UUID moduleId, ModuleEntity updatedModule) {

        Optional<ModuleEntity> optionalModule = this.moduleRepository.findById(moduleId);

        if (optionalModule.isEmpty()) {
            throw new ModuleNotFoundException();
        }

        var existingModule = optionalModule.get();

        existingModule.setTitle(updatedModule.getTitle());
        existingModule.setLongDescription(updatedModule.getLongDescription());
        existingModule.setDescription(updatedModule.getDescription());
        existingModule.setCover(updatedModule.getCover());
        existingModule.setProgress(updatedModule.getProgress());
        existingModule.setIsFavorite(updatedModule.getIsFavorite());
        existingModule.setSubject(updatedModule.getSubject());

        return moduleRepository.save(existingModule);
    }
}

