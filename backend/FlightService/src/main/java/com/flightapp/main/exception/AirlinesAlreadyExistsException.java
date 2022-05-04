package com.flightapp.main.exception;

public class AirlinesAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public AirlinesAlreadyExistsException(String message) {
		super(message);
	}

}
