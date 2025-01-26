package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.entity.User;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUserUseCase {
    @Autowired
    private UserRepository userRepository;

    public List<User> execute() {
        return userRepository.findAll();
    }
}
