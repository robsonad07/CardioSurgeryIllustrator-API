package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.dto.RegisterDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.entity.User;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterAdminUseCase {
    @Autowired
    private UserRepository repository;

    public User execute(RegisterDTO data){
        if(this.repository.findByEmail(data.email()) != null) return null;

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.phone(), data.role());
        this.repository.save(newUser);
        return newUser;
    }
}
