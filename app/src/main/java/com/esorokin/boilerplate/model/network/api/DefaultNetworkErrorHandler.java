package com.esorokin.boilerplate.model.network.api;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import com.esorokin.boilerplate.model.network.api.handler.SingleNetworkErrorHandler;
import com.esorokin.boilerplate.model.network.data.BaseResponse;
import com.esorokin.boilerplate.model.network.exception.NetworkException;
import com.esorokin.boilerplate.model.network.exception.TimeoutException;
import com.esorokin.boilerplate.model.network.exception.UnhandledApiException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class DefaultNetworkErrorHandler implements SingleNetworkErrorHandler<BaseResponse> {
	@Inject
	public DefaultNetworkErrorHandler() {
		//inject
	}

	@Override
	public Single<Response<BaseResponse>> handleNonHttpError(Throwable throwable) {
		Timber.e(throwable);

		if (throwable instanceof SocketTimeoutException) {
			return Single.error(new TimeoutException());

		} else if (throwable instanceof UnknownHostException) {
			return Single.error(new NetworkException());

		} else {
			return Single.error(new UnhandledApiException(throwable));
		}
	}
}
