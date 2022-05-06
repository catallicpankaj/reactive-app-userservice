package com.xcomp.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler {
	
	
	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<String> handleRestClientException(Exception ex, Throwable th) {
		log.error(ex.getMessage());
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<String> handleSomethingElse(Exception ex, Throwable th) {
		log.error(ex.getMessage());
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
