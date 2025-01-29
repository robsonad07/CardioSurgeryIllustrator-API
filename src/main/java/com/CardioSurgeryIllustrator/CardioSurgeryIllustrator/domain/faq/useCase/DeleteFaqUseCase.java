package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.useCase;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.entity.FaqEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteFaqUseCase {
    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private GetOneFaqUseCase getOneFaqUseCase;

    public UUID execute(UUID id) {
        FaqEntity faq = getOneFaqUseCase.execute(id);
        faqRepository.deleteById(faq.getId());
        return id;
    }
}
