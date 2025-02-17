package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.useCase;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.entity.FaqEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.exceptions.FaqNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.faq.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetOneFaqUseCase {
    @Autowired
    private FaqRepository faqRepository;

    public FaqEntity execute(UUID id) {
        Optional<FaqEntity> optionalFaqEntity = faqRepository.findById(id);
        if (optionalFaqEntity.isEmpty()) {
            throw new FaqNotFoundException();
        }
        return optionalFaqEntity.get();
    }
}
