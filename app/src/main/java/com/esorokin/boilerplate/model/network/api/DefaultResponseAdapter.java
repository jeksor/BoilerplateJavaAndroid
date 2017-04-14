package com.esorokin.boilerplate.model.network.api;

import com.esorokin.boilerplate.model.network.api.handler.SingleHttpResponseAdapter;
import com.esorokin.boilerplate.model.network.data.BaseResponse;
import com.esorokin.boilerplate.model.network.exception.UnhandledApiException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;

@Singleton
public class DefaultResponseAdapter implements SingleHttpResponseAdapter<BaseResponse> {
	@Inject
	public DefaultResponseAdapter() {
		//inject
	}

	@Override
	public Single<BaseResponse> handleHttpResponse(Response<BaseResponse> response) {
		if (response.isSuccessful()) {
			//response code 2xx
			return Single.just(response.body());

		} else {
			return Single.error(new UnhandledApiException(new Exception("response.getMessage() or response.errorBody()")));
		}
	}
}
