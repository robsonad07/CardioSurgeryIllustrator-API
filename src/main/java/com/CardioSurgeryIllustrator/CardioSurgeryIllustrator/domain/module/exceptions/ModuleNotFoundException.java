package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.exceptions;

public class ModuleNotFoundException extends RuntimeException {
  public ModuleNotFoundException() {
    super("Module not found exception");
  }
}
