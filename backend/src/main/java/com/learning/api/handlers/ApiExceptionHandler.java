package com.learning.api.handlers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.learning.api.dtos.response.ErrorResponse;
import com.learning.core.services.checkaddress.exceptions.AddressServiceException;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AddressServiceException.class)
	public ResponseEntity<Object> handleAddressServiceException(AddressServiceException exception,
			HttpServletRequest request) {
		
		var errorResponse = ErrorResponse.builder()
			.status(400)
			.timestamp(LocalDateTime.now())
			.message(exception.getLocalizedMessage())
			.path(request.getRequestURI())
			.build();

		return ResponseEntity.badRequest().body(errorResponse);
	}

}
