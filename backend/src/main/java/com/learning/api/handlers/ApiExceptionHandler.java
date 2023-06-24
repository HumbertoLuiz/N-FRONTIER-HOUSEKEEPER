package com.learning.api.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.learning.api.dtos.responses.ErrorResponse;
import com.learning.core.exceptions.TokenNaBlackListException;
import com.learning.core.exceptions.ValidatingException;
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

    @ExceptionHandler(TokenNaBlackListException.class)
    public ResponseEntity<Object> handleTokenNaBlackListException(
        TokenNaBlackListException exception, HttpServletRequest request) {
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


//    @ExceptionHandler(GatewayPagamentoException.class)
//    public ResponseEntity<Object> handleGatewayPagamentoException(
//        GatewayPagamentoException exception, HttpServletRequest request) {
//        return criarErrorResponse(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(),
//            request.getRequestURI());
//    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {
        return handleBindException(exception, headers, status, request);
    }

    protected ResponseEntity<Object> handleBindException(
        BindException exception,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {
        var body= new HashMap<String, List<String>>();
        exception.getBindingResult().getFieldErrors()
            .forEach(fieldError -> {
                var field= camelCaseToSnakeCase.translate(fieldError.getField());
                if (!body.containsKey(field)) {
                    var fieldErrors= new ArrayList<String>();
                    fieldErrors.add(fieldError.getDefaultMessage());
                    body.put(field, fieldErrors);
                } else {
                    body.get(field).add(fieldError.getDefaultMessage());
                }
            });
        return ResponseEntity.badRequest().body(body);
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
