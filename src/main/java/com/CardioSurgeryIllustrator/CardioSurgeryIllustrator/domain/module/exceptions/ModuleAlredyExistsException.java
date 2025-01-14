package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.module.exceptions;

public class ModuleAlredyExistsException extends RuntimeException {
  public ModuleAlredyExistsException() {
    super("Module alredy exists");
  }
}
