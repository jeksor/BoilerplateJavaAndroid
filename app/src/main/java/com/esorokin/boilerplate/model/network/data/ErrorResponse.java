package com.esorokin.boilerplate.model.network.data;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Error structure is contract with web server.
 */
@Data
@Getter
@AllArgsConstructor
public class ErrorResponse {
	@SerializedName("error_code")
	private ApiErrorCode errorCode;

	@SerializedName("error_message")
	private String errorMessage;
}
