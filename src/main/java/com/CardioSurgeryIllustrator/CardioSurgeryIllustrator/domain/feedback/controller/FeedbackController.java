package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.controller;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.dto.FeedbackDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.entity.FeedbackEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackEntity> saveFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        FeedbackEntity savedFeedback = feedbackService.saveFeedback(feedbackDTO);
        return ResponseEntity.ok(savedFeedback);
    }
}
