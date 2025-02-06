package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super("Email not found!");
    }
}
