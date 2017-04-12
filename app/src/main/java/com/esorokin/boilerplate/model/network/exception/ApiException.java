package com.esorokin.boilerplate.model.network.exception;

import com.esorokin.boilerplate.model.network.data.ErrorResponse;

public class ApiException extends RuntimeException {
	private final ErrorResponse errorResponse;

	public ApiException(ErrorResponse errorResponse) {
		super(errorResponse.toString());
		this.errorResponse = errorResponse;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}
