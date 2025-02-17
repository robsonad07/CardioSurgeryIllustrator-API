package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.exceptions;

public class UserNotFoundException  extends RuntimeException {
    public UserNotFoundException() {
        super("User not founded");
    }
}
