package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.exceptions;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException() {
        super("Quiz not founded");
    }
}
