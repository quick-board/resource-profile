package com.quickboard.resourceprofile.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickboard.resourceprofile.common.security.filter.PassportBindingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request -> {
            request.anyRequest().permitAll();
        });

        //필요없는 것들 끄기
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.logout(AbstractHttpConfigurer::disable);

        // /h2-console 화면 표시안되는 문제 해결
        http.headers(h -> h
                .frameOptions(HeadersConfigurer
                        .FrameOptionsConfig::disable
                )
        );

        http.addFilterBefore(new PassportBindingFilter(objectMapper), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
