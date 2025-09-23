package com.quickboard.resourceprofile.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickboard.resourceprofile.common.security.authentication.PassportAuthentication;
import com.quickboard.resourceprofile.common.security.constants.PassportConstants;
import com.quickboard.resourceprofile.common.security.dto.Passport;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class PassportBindingFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    private final List<String> ignorePaths = List.of("/h2-console", "/swagger", "/swagger-ui", "/favicon.ico", "/v3/api-docs");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String servletPath = request.getServletPath();
        log.trace("entering path={}", servletPath);
        return ignorePaths.stream().anyMatch(servletPath::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.trace("resolve path={}", request.getServletPath());

        String passportValue = request.getHeader(PassportConstants.PASSPORT_HEADER_NAME);
        log.info("passportValue={}", passportValue);
        Passport passport = objectMapper.readValue(passportValue, Passport.class);
        log.info("passport = {}", passport);
        bindAuthentication(passport);

        filterChain.doFilter(request, response);
    }

    private void bindAuthentication(Passport passport){
        Authentication authentication = PassportAuthentication.authenticated(passport);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}
