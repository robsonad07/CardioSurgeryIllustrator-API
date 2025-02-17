package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.entity.PasswordRecoveryEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.entity.User;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.exceptions.CodeExpiredException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.exceptions.EmailNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.exceptions.InvalidCodeException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.exceptions.UserNotFoundException;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.repository.PasswordRecoveryRepository;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.time.Instant;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.time.Duration;

@Service
public class PasswordRecoveryService {
    @Value("${api.email.host}")
    private String HOST;

    @Value("${api.email.port}")
    private String PORT;

    @Value("${api.email.login}")
    private String USERNAME;

    @Value("${api.email.password}")
    private String PASSWORD;

    @Autowired
    private PasswordRecoveryRepository passwordRecoveryRepository;

    @Autowired
    private UserRepository userRepository;

    public String generateCode(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new EmailNotFoundException();
        }

        String code = generateCode6Digits();
        Instant instant = Instant.now();

        PasswordRecoveryEntity passwordRecovery = new PasswordRecoveryEntity(email, code, instant);

        passwordRecoveryRepository.save(passwordRecovery);

        sendEmail(email, code);

        return "{\"status\": \"success\"}";
    }

    public String validCode(String email, String code) {
        Optional<PasswordRecoveryEntity> optionalPasswordRecoveryEntity = passwordRecoveryRepository.findById(email);

        if(optionalPasswordRecoveryEntity.isEmpty()) {
            throw new EmailNotFoundException();
        }

        PasswordRecoveryEntity passwordRecovery = optionalPasswordRecoveryEntity.get();
        if(isExpired(passwordRecovery.getGenerationMoment())) {
            throw new CodeExpiredException();
        }

        if(!code.equalsIgnoreCase(passwordRecovery.getCode())) {
            throw new InvalidCodeException();
        }

        return "{\"status\": \"success\"}";
    }

    public String changePassword(String email, String code, String newPassword) {
        validCode(email, code);
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();

        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
        return "{\"status\": \"success\"}";
    }

    private String generateCode6Digits() {
        int code = ThreadLocalRandom.current().nextInt(1000000);
        return String.format("%06d", code);
    }

    private boolean isExpired(Instant instantCode) {
        Instant now = Instant.now();

        Duration duration = Duration.between(instantCode, now);

        return duration.toMinutes() >= 10;
    }

    private void sendEmail(String email, String code) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Recuperação de Senha");

            String htmlContent = "<html><body>"
                    + "<h2>Recuperação de Senha</h2>"
                    + "<p>Você solicitou a recuperação de senha. Use o código abaixo para redefinir sua senha:</p>"
                    + "<h3>" + code + "</h3>"
                    + "<p>Se você não solicitou essa recuperação, por favor, ignore este e-mail.</p>"
                    + "</body></html>";

            message.setContent(htmlContent, "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
            throw new RuntimeException("Falha ao enviar e-mail de recuperação", e);
        }
    }
}
