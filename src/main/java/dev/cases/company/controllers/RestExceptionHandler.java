package dev.cases.company.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			sb.append(fieldName);
			sb.append(":");
			sb.append(errorMessage);
			sb.append("|");
		});
		errors.put("message", sb.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGeneralException(Exception exception) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}
