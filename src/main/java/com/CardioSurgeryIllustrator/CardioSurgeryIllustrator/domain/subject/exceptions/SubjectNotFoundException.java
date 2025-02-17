package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.exceptions;

public class SubjectNotFoundException extends RuntimeException {
  public SubjectNotFoundException() {
    super("Subject ID cannot be null or empty");
  }
}