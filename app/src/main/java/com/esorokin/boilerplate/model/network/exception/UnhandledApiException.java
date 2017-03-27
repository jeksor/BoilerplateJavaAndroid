package com.esorokin.boilerplate.model.network.exception;

import com.esorokin.boilerplate.model.network.data.ApiErrorCode;
import com.esorokin.boilerplate.model.network.data.ErrorResponse;

public class UnhandledApiException extends ApiException {
	public UnhandledApiException(Throwable cause) {
		super(new ErrorResponse(ApiErrorCode.UNKNOWN, cause.getMessage()));
	}
}
