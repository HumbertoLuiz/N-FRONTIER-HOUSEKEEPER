package com.learning.api.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.learning.api.dtos.responses.ErrorResponse;
import com.learning.core.exceptions.TokenBlackListException;
import com.learning.core.exceptions.ValidatingException;
import com.learning.core.gatewaypagamento.exceptions.GatewayPaymentException;
import com.learning.core.services.checkaddress.exceptions.AddressServiceException;
import com.learning.core.services.checkcity.exceptions.CheckCityServiceException;
import com.learning.core.services.token.exceptions.TokenServiceException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations= RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private SnakeCaseStrategy camelCaseToSnakeCase= new SnakeCaseStrategy();

    @ExceptionHandler(AddressServiceException.class)
    public ResponseEntity<Object> handleEnderecoServiceException(
        AddressServiceException exception, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(),
            request.getRequestURI());
    }

    @ExceptionHandler(ValidatingException.class)
    public ResponseEntity<Object> handleValidacaoException(ValidatingException exception) {
        var body= new HashMap<String, List<String>>();
        var fieldError= exception.getFieldError();
        var fieldErrors= new ArrayList<String>();
        fieldErrors.add(fieldError.getDefaultMessage());
        var field= camelCaseToSnakeCase.translate(fieldError.getField());
        body.put(field, fieldErrors);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(TokenServiceException.class)
    public ResponseEntity<Object> handleTokenServiceException(
        TokenServiceException exception, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage(),
            request.getRequestURI());
    }

    @ExceptionHandler(TokenBlackListException.class)
    public ResponseEntity<Object> handleTokenBlackListException(
        TokenBlackListException exception, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage(),
            request.getRequestURI());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(
        EntityNotFoundException exception, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(),
            request.getRequestURI());
    }

    @ExceptionHandler(CheckCityServiceException.class)
    public ResponseEntity<Object> handleConsultaCidadeServiceException(
        CheckCityServiceException exception, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(),
            request.getRequestURI());
    }


    @ExceptionHandler(GatewayPaymentException.class)
    public ResponseEntity<Object> handleGatewayPagamentoException(
    		GatewayPaymentException exception, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(),
            request.getRequestURI());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(
            BindException exception,
            WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


    private ResponseEntity<Object> createErrorResponse(HttpStatus status, String message,
        String path) {
        var errorResponse= ErrorResponse.builder()
            .status(status.value())
            .timestamp(LocalDateTime.now())
            .message(message)
            .path(path)
            .build();
        return new ResponseEntity<>(errorResponse, status);
    }

}
