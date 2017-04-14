package com.esorokin.boilerplate.model.network.api.handler;

import java.lang.reflect.Type;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;

class SingleCallAdapter<BaseResponse> implements CallAdapter<BaseResponse, Single<BaseResponse>> {
	private final Type responseType;
	private final NetworkErrorAdapter networkErrorAdapter;
	private final HttpResponseAdapter<BaseResponse> httpResponseAdapter;

	public SingleCallAdapter(@NonNull Type responseType,
	                         @NonNull NetworkErrorAdapter networkErrorAdapter,
	                         @NonNull HttpResponseAdapter<BaseResponse> httpResponseAdapter) {
		this.responseType = responseType;
		this.networkErrorAdapter = networkErrorAdapter;
		this.httpResponseAdapter = httpResponseAdapter;
	}

	@Override
	public Type responseType() {
		return responseType;
	}

	@Override
	public Single<BaseResponse> adapt(Call<BaseResponse> call) {
		return new CallExecuteObservable<>(call).singleOrError()
				.onErrorResumeNext(throwable -> Single.error(networkErrorAdapter.adaptNetworkError(throwable)))
				.map(httpResponseAdapter::adaptHttpResponse);
	}
}
