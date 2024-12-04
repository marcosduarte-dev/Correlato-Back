package com.marcospedroso.facens.correlato.exception;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = -3341095477873465611L;

	public NotFoundException(String message) {
        super(message);
    }

}
