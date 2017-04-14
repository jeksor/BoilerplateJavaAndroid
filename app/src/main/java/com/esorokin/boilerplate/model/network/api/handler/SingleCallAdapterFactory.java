package com.esorokin.boilerplate.model.network.api.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public final class SingleCallAdapterFactory extends CallAdapter.Factory {
	/**
	 * Create call adapter factory for your response type and it's child.<br/>
	 * You can register plenty of factories for separate child. Just register in right order.<br/>
	 * For example:<br/>
	 * Child1<br/>
	 * Child2<br/>
	 * Parent<br/>
	 * @param baseResponseType Target response type
	 * @param errorHandler Handler network, converting and other non-http errors
	 * @param httpResponseHandler Handler server response (fail, success)
	 */
	public static <R> SingleCallAdapterFactory create(@NonNull Class<R> baseResponseType,
	                                                  @NonNull SingleNetworkErrorHandler errorHandler,
	                                                  @NonNull SingleHttpResponseHandler<R> httpResponseHandler) {
		return new SingleCallAdapterFactory(baseResponseType, errorHandler, httpResponseHandler);
	}

	private final Class<?> baseResponseType;
	private final SingleNetworkErrorHandler errorHandler;
	private final SingleHttpResponseHandler<?> httpResponseHandler;

	private SingleCallAdapterFactory(Class<?> baseResponseType, SingleNetworkErrorHandler errorHandler, SingleHttpResponseHandler<?> httpResponseHandler) {
		super();
		this.baseResponseType = baseResponseType;
		this.errorHandler = errorHandler;
		this.httpResponseHandler = httpResponseHandler;
	}

	@Override
	public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
		Class<?> rawType = getRawType(returnType);

		if (rawType != Single.class) {
			return null;
		}

		if (!(returnType instanceof ParameterizedType)) {
			throw new IllegalStateException("Single return type must be parameterized as Single<Foo> or Single<? extends Foo>");
		}

		Class<?> responseType = getRawType(getParameterUpperBound(0, (ParameterizedType) returnType));
		if (!baseResponseType.isAssignableFrom(responseType)) {
			return null;
		}

		//noinspection unchecked
		return new SingleResponseAdapter(responseType, errorHandler, httpResponseHandler);
	}
}
