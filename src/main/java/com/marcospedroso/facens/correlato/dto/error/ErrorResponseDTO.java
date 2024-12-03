package com.marcospedroso.facens.correlato.dto.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDTO {
	private String message;
    private HttpStatus httpStatus;
    private Integer statusCode;
}
