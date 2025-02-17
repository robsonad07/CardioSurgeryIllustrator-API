package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity;

import lombok.Data;

import java.util.List;

@Data
public class ReportEntity {
    private String title;
    private Float score;

    private List<ReportDetailsEntity> reportDetailsEntities;
}
