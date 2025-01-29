package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.exceptions;

public class FaqNotFoundException extends RuntimeException {
    public FaqNotFoundException() {
        super("FAQ not founded");
    }
}
