package com.esorokin.boilerplate.model.network.api.handler;

import io.reactivex.Single;
import retrofit2.Response;

public interface SingleNetworkErrorHandler {
	Single<Response<?>> handleNonHttpError(Throwable throwable);
}
