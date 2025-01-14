package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.dto;

import lombok.Data;

@Data
public class FeedbackDTO {
    private String name;
    private String email;
    private String country;
    private String state;
    private String city;
    private String feedback;
}