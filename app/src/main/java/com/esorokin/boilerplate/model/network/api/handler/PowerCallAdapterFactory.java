package com.esorokin.boilerplate.model.network.api.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import android.support.annotation.NonNull;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public final class PowerCallAdapterFactory extends CallAdapter.Factory {
	/**
	 * Create call adapter factory for your response type and it's child.<br/>
	 * You can register plenty of factories for separate child. Just register in right order.<br/>
	 * For example:<br/>
	 * Child1<br/>
	 * Child2<br/>
	 * Parent<br/>
	 *
	 * @param baseResponseType    Target response type
	 * @param networkErrorAdapter Handler network, converting and other non-http errors
	 * @param httpResponseAdapter Handler server response (fail, success)
	 */
	public static <R> PowerCallAdapterFactory create(@NonNull Class<R> baseResponseType,
	                                                 @NonNull NetworkErrorAdapter networkErrorAdapter,
	                                                 @NonNull HttpResponseAdapter<R> httpResponseAdapter) {
		return new PowerCallAdapterFactory(baseResponseType, networkErrorAdapter, httpResponseAdapter);
	}

	private final Class<?> baseResponseType;
	private final NetworkErrorAdapter networkErrorAdapter;
	private final HttpResponseAdapter<?> httpResponseAdapter;

	private PowerCallAdapterFactory(Class<?> baseResponseType, NetworkErrorAdapter networkErrorAdapter, HttpResponseAdapter<?> httpResponseAdapter) {
		super();
		this.baseResponseType = baseResponseType;
		this.networkErrorAdapter = networkErrorAdapter;
		this.httpResponseAdapter = httpResponseAdapter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
		Class<?> rawType = getRawType(returnType);

		if (rawType != Single.class && rawType != Maybe.class) {
			return null;
		}

		if (!(returnType instanceof ParameterizedType)) {
			throw new IllegalStateException("Single return type must be parameterized as Single<Foo> or Single<? extends Foo>");
		}

		Class<?> responseType = getRawType(getParameterUpperBound(0, (ParameterizedType) returnType));
		if (!baseResponseType.isAssignableFrom(responseType)) {
			return null;
		}

		if (rawType == Single.class) {
			return new SingleCallAdapter(responseType, networkErrorAdapter, httpResponseAdapter);

		} else {
			return new MaybeCallAdapter(responseType, networkErrorAdapter, httpResponseAdapter);
		}
	}
}
