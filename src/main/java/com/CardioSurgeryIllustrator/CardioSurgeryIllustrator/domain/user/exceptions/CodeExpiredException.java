package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.exceptions;

public class CodeExpiredException extends RuntimeException {
    public CodeExpiredException() {
        super("Code expired");
    }
}
