package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity.ModuleEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.repository.ModuleRepository;

@Service
public class CreateModuleUseCase {
  
  @Autowired
  private ModuleRepository moduleRepository;

  public ModuleEntity execute(ModuleEntity moduleEntity) {
    
    return this.moduleRepository.save(moduleEntity);

  }
}
