package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.exceptions;

public class SubjectIdNotFoundException extends RuntimeException {
  public SubjectIdNotFoundException() {
    super("Subject ID cannot be null or empty");
  }
}
