package com.esorokin.boilerplate.model.network.api.handler;

import java.lang.reflect.Type;

import android.support.annotation.NonNull;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.CallAdapter;

class FlowableCallAdapter<BaseResponse> implements CallAdapter<BaseResponse, Flowable<BaseResponse>> {
	private final Type responseType;
	private final NetworkErrorAdapter networkErrorAdapter;
	private final HttpResponseAdapter<BaseResponse> httpResponseHandler;

	public FlowableCallAdapter(@NonNull Type responseType,
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
	public Flowable<BaseResponse> adapt(Call<BaseResponse> call) {
		return new CallExecuteObservable<>(call).toFlowable(BackpressureStrategy.LATEST)
				.onErrorResumeNext((Throwable throwable) -> Flowable.error(networkErrorAdapter.adaptNetworkError(throwable)))
				.map(httpResponseHandler::adaptHttpResponse);
	}
}
