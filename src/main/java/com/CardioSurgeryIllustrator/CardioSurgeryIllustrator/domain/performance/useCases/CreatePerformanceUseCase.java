package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity.PerformanceEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePerformanceUseCase {
    @Autowired
    private PerformanceRepository performanceRepository;

    public PerformanceEntity execute(PerformanceEntity performance) {
        return performanceRepository.save(performance);
    }
}
