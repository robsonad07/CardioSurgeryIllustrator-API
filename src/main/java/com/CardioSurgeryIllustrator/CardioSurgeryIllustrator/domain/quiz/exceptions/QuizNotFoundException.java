package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.exceptions;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException() {
        super("Subject ID cannot be null or empty");
    }
}
