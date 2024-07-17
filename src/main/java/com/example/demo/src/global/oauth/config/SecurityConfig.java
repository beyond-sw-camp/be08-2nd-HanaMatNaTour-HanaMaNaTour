package com.example.demo.src.global.oauth.config;

import com.example.demo.src.global.oauth.oauth2.CustomClientRegistrationRepo;
import com.example.demo.src.global.oauth.service.Oauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Oauth2UserService oauth2UserService;
    private final CustomClientRegistrationRepo customClientRegistrationRepo;

    public SecurityConfig(Oauth2UserService oauth2UserService, CustomClientRegistrationRepo customClientRegistrationRepo) {
        this.oauth2UserService = oauth2UserService;
        this.customClientRegistrationRepo = customClientRegistrationRepo;
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/users/login")
                        .clientRegistrationRepository(customClientRegistrationRepo.clientRegistrationRepository())
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(oauth2UserService))
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
