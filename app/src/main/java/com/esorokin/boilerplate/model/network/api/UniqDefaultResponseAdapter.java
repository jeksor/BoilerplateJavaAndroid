package com.esorokin.boilerplate.model.network.api;

import com.esorokin.boilerplate.model.network.api.handler.HttpResponseAdapter;
import com.esorokin.boilerplate.model.network.data.BaseResponse;
import com.esorokin.boilerplate.model.network.exception.ApiException;
import com.esorokin.boilerplate.model.network.exception.UnhandledApiException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class UniqDefaultResponseAdapter implements HttpResponseAdapter<BaseResponse> {
	@Inject
	public UniqDefaultResponseAdapter() {
		//inject
	}

	@Override
	public BaseResponse adaptHttpResponse(Response<BaseResponse> response) throws ApiException {
		if (response.isSuccessful()) {
			//response code 2xx
			return response.body();

		} else {
			throw new UnhandledApiException(new Exception("response.getMessage() or response.errorBody()"));
		}
	}
}
