package com.esorokin.boilerplate.model.network.api.handler;

import io.reactivex.Single;
import retrofit2.Response;

public interface SingleNetworkErrorHandler<BaseResponse> {
	Single<Response<BaseResponse>> handleNonHttpError(Throwable throwable);
}
