package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.AnswerEnum;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.QuestionEntity;
import lombok.Data;

@Data
public class ReportDetailsEntity {
    private QuestionEntity question;
    private AnswerEnum answerUser;
    private String gotItRight;
}
