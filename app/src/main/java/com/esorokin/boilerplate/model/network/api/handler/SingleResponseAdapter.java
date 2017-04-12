package com.esorokin.boilerplate.model.network.api.handler;

import java.lang.reflect.Type;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;

class SingleResponseAdapter<BaseResponse> implements CallAdapter<BaseResponse, Single<BaseResponse>> {
	private Type responseType;
	private final SingleNetworkErrorHandler<BaseResponse> errorHandler;
	private final SingleHttpResponseHandler<BaseResponse> httpResponseHandler;

	public SingleResponseAdapter(@NonNull Type responseType,
	                             @NonNull SingleNetworkErrorHandler<BaseResponse> errorHandler,
	                             @NonNull SingleHttpResponseHandler<BaseResponse> httpResponseHandler) {
		this.responseType = responseType;
		this.errorHandler = errorHandler;
		this.httpResponseHandler = httpResponseHandler;
	}

	@Override
	public Type responseType() {
		return responseType;
	}

	@Override
	public Single<BaseResponse> adapt(Call<BaseResponse> call) {
		return new CallExecuteObservable<>(call).singleOrError()
				.onErrorResumeNext(errorHandler::handleNonHttpError)
				.flatMap(httpResponseHandler::handleHttpResponse);
	}
}
