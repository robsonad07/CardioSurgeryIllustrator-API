package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.controllers;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.entity.User;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.useCases.GetAllUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private GetAllUserUseCase getAllUserUseCase;

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllUser() {
        try {
            List<User> response = getAllUserUseCase.execute();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
