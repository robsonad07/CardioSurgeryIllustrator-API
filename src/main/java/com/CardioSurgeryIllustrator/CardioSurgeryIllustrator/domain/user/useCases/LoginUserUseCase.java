package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.useCases;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.entity.User;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUseCase {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public String execute(UsernamePasswordAuthenticationToken usernamePassword){
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());
    }
}
