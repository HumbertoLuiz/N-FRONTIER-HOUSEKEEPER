package br.com.xfrontier.housekeeper.api.handlers;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.xfrontier.housekeeper.api.dtos.responses.ErrorResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        var status= HttpStatus.UNAUTHORIZED;

        var errorResponse= ErrorResponse.builder()
            .status(status.value())
            .timestamp(LocalDateTime.now())
            .message(authException.getLocalizedMessage())
            .path(request.getRequestURI())
            .build();

        var json= objectMapper.writeValueAsString(errorResponse);

        response.setStatus(status.value());
        response.setHeader("Content-Type", "application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }

}