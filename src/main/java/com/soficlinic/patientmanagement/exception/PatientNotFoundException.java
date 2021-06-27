package com.soficlinic.patientmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final static String message = "Patient Not Found";

	public PatientNotFoundException() {
		super(message);
	}

}