package com.educandoweb.course.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@ControllerAdvice // É a que vai interceptar as exceções que irão acontecer! Para possiveis
					// tratamentos
public class ResourceExceptionHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(ResourceNotFoundException.class) // A anotação que irá interceder na exceção da classe
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found!";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
