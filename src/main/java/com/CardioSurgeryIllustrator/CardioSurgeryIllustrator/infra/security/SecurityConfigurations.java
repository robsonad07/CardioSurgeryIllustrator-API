package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    private static final String[] PERMIT_ALL_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PERMIT_ALL_LIST).permitAll()
                        .requestMatchers("/module/**").permitAll()
                        .requestMatchers("/subject/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register-user").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/get-all").permitAll()
                        .requestMatchers(HttpMethod.POST, "/quiz/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/quiz/get-all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/quiz/get-one/*").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/quiz/update/*").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/quiz/delete/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/question/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/question/get-all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/question/get-one/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/forum/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/forum/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/comment/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/comment/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/question/update/*").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/question/delete/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/performance/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/performance/get-report/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/faq/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/faq/get-all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/faq/get-one/*").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/faq/update/*").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/faq/delete/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/password-recovery/generate-code").permitAll()
                        .requestMatchers(HttpMethod.POST, "/password-recovery/valid-code").permitAll()
                        .requestMatchers(HttpMethod.POST, "/password-recovery/new-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/patient/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/patient/form/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/patient/**").permitAll()
                        .requestMatchers(PERMIT_ALL_LIST).permitAll()
                        .requestMatchers("/module/**").permitAll()
                        .requestMatchers("/subject/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
