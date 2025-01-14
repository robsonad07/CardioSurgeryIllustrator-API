package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.dto.FeedbackDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.entity.FeedbackEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.feedback.repository.FeedbackRepository;


@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public FeedbackEntity saveFeedback(FeedbackDTO feedbackDTO) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setName(feedbackDTO.getName());
        feedbackEntity.setEmail(feedbackDTO.getEmail());
        feedbackEntity.setCountry(feedbackDTO.getCountry());
        feedbackEntity.setState(feedbackDTO.getState());
        feedbackEntity.setCity(feedbackDTO.getCity());
        feedbackEntity.setFeedback(feedbackDTO.getFeedback());

        return feedbackRepository.save(feedbackEntity);
    }
}
