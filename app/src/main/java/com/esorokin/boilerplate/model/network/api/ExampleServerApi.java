package com.esorokin.boilerplate.model.network.api;

import java.io.IOException;

import com.esorokin.boilerplate.model.network.data.ApiErrorCode;
import com.esorokin.boilerplate.model.network.data.ErrorResponse;
import com.esorokin.boilerplate.model.network.data.example.ExampleDto;
import com.esorokin.boilerplate.model.network.exception.UnhandledApiException;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class ExampleServerApi extends ServerApi {
	@Inject
	Gson gson;

	@Inject
	ExampleApi exampleApi;

	@Inject
	public ExampleServerApi() {/**/}

	public Single<ExampleDto> getExample() {
		return handleHttpErrorCall(exampleApi.getExample());
	}

	@Override
	protected <R> Single<R> handleHttpResponse(Response<R> response) {
		if (response.isSuccessful()) {
			//response code 2xx
			return Single.just(response.body());

		} else {
			ErrorResponse errorResponse = getErrorResponse(response);
			switch (errorResponse.getErrorCode()) {

				//TODO create exceptions for special server error codes
				/*case REGISTER_INVALID_DATA:
					return Single.error(new ApiException(errorResponse));*/

				case UNKNOWN:
				default:
					return Single.error(new UnhandledApiException(new Exception(errorResponse.getErrorMessage())));
			}
		}
	}

	private <R> ErrorResponse getErrorResponse(Response<R> response) {
		try {
			return gson.fromJson(response.errorBody().string(), ErrorResponse.class);
		} catch (IOException e) {
			Timber.e(e);
			return new ErrorResponse(ApiErrorCode.UNKNOWN, "Unhandled API error");
		}
	}
}
