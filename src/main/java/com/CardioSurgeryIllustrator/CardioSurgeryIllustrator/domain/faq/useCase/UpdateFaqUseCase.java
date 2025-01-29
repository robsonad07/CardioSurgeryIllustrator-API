package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.useCase;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.entity.FaqEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateFaqUseCase {
    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private GetOneFaqUseCase getOneFaqUseCase;

    public FaqEntity execute(UUID id, FaqEntity faqEntity) {
        FaqEntity faq = getOneFaqUseCase.execute(id);
        faq.setQuestion(faqEntity.getQuestion());
        faq.setResponse(faqEntity.getResponse());
        return faqRepository.save(faq);
    }
}
