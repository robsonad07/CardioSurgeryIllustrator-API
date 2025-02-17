package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.exceptions;

public class QuestionNotFoundException extends RuntimeException {
    public  QuestionNotFoundException() {
        super("Question not founded");
    }
}
