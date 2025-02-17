package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity.PerformanceDetailEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity.PerformanceEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity.ReportDetailsEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity.ReportEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.repository.PerformanceRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.entity.QuestionEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.question.repository.QuestionRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.entity.QuizEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.exceptions.QuizNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GetReportUseCase {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<ReportEntity> execute(UUID userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<PerformanceEntity> performanceEntities = performanceRepository.findByUserIdAndResponseDateBetween(userId, startDate, endDate);

        Map<UUID, QuizEntity> quizMap = performanceEntities.stream()
                .map(PerformanceEntity::getQuizId)
                .distinct()
                .collect(Collectors.toMap(id -> id, id -> quizRepository.findById(id).orElseThrow(QuizNotFoundException::new)));


        return performanceEntities.stream().map(performance -> {
            QuizEntity quiz = quizMap.get(performance.getQuizId());
            List<QuestionEntity> questionList = quiz.getQuestionEntityList();

            List<ReportDetailsEntity> reportDetails = performance.getDetails().stream()
                    .map(detail -> {
                        QuestionEntity question = questionList.stream()
                                .filter(q -> q.getId().equals(detail.getQuestionId()))
                                .findFirst()
                                .orElse(null);
                        if (question == null) return null;
                        return buildReportDetail(detail, question);
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            return buildReport(performance, quiz, reportDetails);
        }).collect(Collectors.toList());
    }

    private ReportDetailsEntity buildReportDetail(PerformanceDetailEntity detail, QuestionEntity question) {
        ReportDetailsEntity reportDetail = new ReportDetailsEntity();
        reportDetail.setQuestion(question);
        reportDetail.setAnswerUser(detail.getAnswer());
        reportDetail.setGotItRight(detail.getAnswer().equals(question.getAnswer()) ? "Resposta correta" : "Resposta errada");
        return reportDetail;
    }

    private ReportEntity buildReport(PerformanceEntity performance, QuizEntity quiz, List<ReportDetailsEntity> reportDetails) {
        ReportEntity report = new ReportEntity();
        report.setTitle(quiz.getTitle());
        report.setScore(performance.getScore());
        report.setReportDetailsEntities(reportDetails);
        return report;
    }
}
