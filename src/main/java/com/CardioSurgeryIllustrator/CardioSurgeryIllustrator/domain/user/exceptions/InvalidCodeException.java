package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.exceptions;

public class InvalidCodeException extends RuntimeException {
    public InvalidCodeException() {
        super("invalid code");
    }
}
