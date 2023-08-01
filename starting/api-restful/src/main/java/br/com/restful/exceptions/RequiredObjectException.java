package br.com.restful.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequiredObjectException() {
		super("It is not allowed to persist a null object");
	}
	
	public RequiredObjectException(String message) {
		super(message);
	}
}