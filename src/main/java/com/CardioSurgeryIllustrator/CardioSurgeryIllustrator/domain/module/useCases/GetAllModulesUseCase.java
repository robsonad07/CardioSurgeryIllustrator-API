package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.entity.ModuleEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.repository.ModuleRepository;

@Service
public class GetAllModulesUseCase {
  
  @Autowired
  private ModuleRepository moduleRepository;

  public List<ModuleEntity> execute() {
    return this.moduleRepository.findAll();
  }
}
