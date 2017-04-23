package com.esorokin.boilerplate.model.network.api.handler;

import java.lang.reflect.Type;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.CallAdapter;

class ObservableCallAdapter<BaseResponse> implements CallAdapter<BaseResponse, Observable<BaseResponse>> {
	private final Type responseType;
	private final NetworkErrorAdapter networkErrorAdapter;
	private final HttpResponseAdapter<BaseResponse> httpResponseHandler;

	public ObservableCallAdapter(@NonNull Type responseType,
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
	public Observable<BaseResponse> adapt(Call<BaseResponse> call) {
		return new CallExecuteObservable<>(call)
				.onErrorResumeNext((Throwable throwable) -> Observable.error(networkErrorAdapter.adaptNetworkError(throwable)))
				.map(httpResponseHandler::adaptHttpResponse);
	}
}
