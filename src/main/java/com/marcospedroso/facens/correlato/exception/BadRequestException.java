package com.marcospedroso.facens.correlato.exception;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 7095942770840256640L;

	public BadRequestException(String message) {
        super(message);
    }

}
