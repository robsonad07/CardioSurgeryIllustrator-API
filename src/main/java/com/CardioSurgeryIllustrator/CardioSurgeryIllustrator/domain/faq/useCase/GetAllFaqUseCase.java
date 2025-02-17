package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.useCase;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.entity.FaqEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllFaqUseCase {
    @Autowired
    private FaqRepository faqRepository;

    public List<FaqEntity> execute() {
        return faqRepository.findAll();
    }
}
