package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.controllers;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.dto.EmailCodeDTO;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.service.PasswordRecoveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/password-recovery")
public class PasswordRecoveryController {
    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/generate-code")
    public ResponseEntity<Object> generateCode(@RequestBody @Valid EmailCodeDTO request) {
        try {
            String response = passwordRecoveryService.generateCode(request.email());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/valid-code")
    public ResponseEntity<Object> validCode(@RequestBody @Valid EmailCodeDTO request) {
        try {
            String response = passwordRecoveryService.validCode(request.email(), request.code());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/new-password")
    public ResponseEntity<Object> newPassword(@RequestBody @Valid EmailCodeDTO request) {
        try {
            String response = passwordRecoveryService.changePassword(request.email(), request.code(), request.password());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
