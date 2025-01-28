package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.useCase;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.entity.FaqEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateFaqUseCase {
    @Autowired
    private FaqRepository faqRepository;

    public FaqEntity execute(FaqEntity faqEntity) {
        return faqRepository.save(faqEntity);
    }
}
