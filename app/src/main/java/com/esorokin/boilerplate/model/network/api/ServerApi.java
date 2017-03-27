package com.esorokin.boilerplate.model.network.api;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.support.annotation.NonNull;

import com.esorokin.boilerplate.model.network.exception.NetworkException;
import com.esorokin.boilerplate.model.network.exception.TimeoutException;
import com.esorokin.boilerplate.model.network.exception.UnhandledApiException;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

/**
 * <pre>
 * Need for detecting API errors. For example: if 'okFlag' == 48 -> new OkException();
 *
 * Not communicate with other api!
 * Not handle error!
 * Not convert to user getText!
 * Work only with 1 api group!
 * For specific logic user repository/manager/service.
 * <pre/>
 */
public abstract class ServerApi {
	/**
	 * <pre>
	 * Use this method for add default error and response handling.
	 * Override if need special default call wrapper in subclass service.
	 *
	 * If you want only special error handler or response handler - use {@link ServerApi#handleNonHttpError} or {@link ServerApi#handleHttpResponse}.
	 * </pre>
	 */
	protected final <R> Single<R> handleHttpErrorCall(final Call<R> apiCall) {
		return handleNetworkErrorCall(apiCall)
				.flatMap(this::handleHttpResponse);
	}

	@SuppressWarnings("unchecked")
	protected final <R> Single<Response<R>> handleNetworkErrorCall(final Call<R> apiCall) {
		return baseCall(apiCall)
				.onErrorResumeNext(this::handleNonHttpError);
	}

	/**
	 * Simple wrapper Retrofit Call's to Single with onSuccess, onError.<br>
	 * Don't modify or add you specified logic here. Use {@link ServerApi#handleHttpErrorCall(Call)} for this.
	 */
	@NonNull
	protected final <R> Single<Response<R>> baseCall(final Call<R> apiCall) {
		return Single.fromCallable(apiCall::execute)
				.doOnDispose(apiCall::cancel);
	}

	/**
	 * Default handle non http errors (connection lost, converted errors, unexpected exceptions etc.).
	 * Override if need special default handling in subclass service.
	 */
	protected <R> Single<Response<R>> handleNonHttpError(Throwable throwable) {
		Timber.e(throwable);

		if (throwable instanceof SocketTimeoutException) {
			return Single.error(new TimeoutException());

		} else if (throwable instanceof UnknownHostException) {
			return Single.error(new NetworkException());

		} else {
			return Single.error(new UnhandledApiException(throwable));
		}
	}

	/**
	 * Default handle http response.
	 * Override if need special default handling in subclass service.
	 */
	protected abstract <R> Single<R> handleHttpResponse(Response<R> response);
}
