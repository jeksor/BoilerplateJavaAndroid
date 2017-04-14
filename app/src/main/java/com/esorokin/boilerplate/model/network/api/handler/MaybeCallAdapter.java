package com.esorokin.boilerplate.model.network.api.handler;

import java.lang.reflect.Type;

import android.support.annotation.NonNull;

import io.reactivex.Maybe;
import retrofit2.Call;
import retrofit2.CallAdapter;

class MaybeCallAdapter<BaseResponse> implements CallAdapter<BaseResponse, Maybe<BaseResponse>> {
	private final Type responseType;
	private final NetworkErrorAdapter networkErrorAdapter;
	private final HttpResponseAdapter<BaseResponse> httpResponseHandler;

	public MaybeCallAdapter(@NonNull Type responseType,
	                        @NonNull NetworkErrorAdapter networkErrorAdapter,
	                        @NonNull HttpResponseAdapter<BaseResponse> httpResponseAdapter) {
		this.responseType = responseType;
		this.networkErrorAdapter = networkErrorAdapter;
		this.httpResponseHandler = httpResponseAdapter;
	}

	@Override
	public Type responseType() {
		return responseType;
	}

	@Override
	public Maybe<BaseResponse> adapt(Call<BaseResponse> call) {
		return new CallExecuteObservable<>(call).singleElement()
				.onErrorResumeNext((Throwable throwable) -> Maybe.error(networkErrorAdapter.adaptNetworkError(throwable)))
				.map(httpResponseHandler::adaptHttpResponse);
	}
}
