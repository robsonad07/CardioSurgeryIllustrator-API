package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.entity.SubjectEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.subject.repository.SubjectRepository;

@Service
public class ListAllSubjectsUseCase {

  @Autowired
  private SubjectRepository subjectRepository;

  public List<SubjectEntity> execute() {
    return this.subjectRepository.findAll();
  }
}
