package com.esorokin.boilerplate.model.network.api.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import android.support.annotation.NonNull;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public final class RxCallAdapterFactory extends CallAdapter.Factory {
	/**
	 * Create call adapter factory for your response type and it's childs.<br/>
	 * You can register plenty of factories for separate child. Just register in right order.<br/>
	 * For example:<br/>
	 * Child1<br/>
	 * Child2<br/>
	 * Parent<br/>
	 *
	 * @param baseResponseType    Target response type
	 * @param networkErrorAdapter Handler network, converting and other non-http errors
	 * @param httpResponseAdapter Handler server response (fail, success, status etc)
	 */
	public static <R> RxCallAdapterFactory create(@NonNull Class<R> baseResponseType,
	                                              @NonNull NetworkErrorAdapter networkErrorAdapter,
	                                              @NonNull HttpResponseAdapter<R> httpResponseAdapter) {
		return new RxCallAdapterFactory(baseResponseType, networkErrorAdapter, httpResponseAdapter);
	}

	private final Class<?> baseResponseType;
	private final NetworkErrorAdapter networkErrorAdapter;
	private final HttpResponseAdapter<?> httpResponseAdapter;

	private RxCallAdapterFactory(Class<?> baseResponseType, NetworkErrorAdapter networkErrorAdapter, HttpResponseAdapter<?> httpResponseAdapter) {
		super();
		this.baseResponseType = baseResponseType;
		this.networkErrorAdapter = networkErrorAdapter;
		this.httpResponseAdapter = httpResponseAdapter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
		Class<?> rawType = getRawType(returnType);

		boolean isCompletable = rawType == Comparable.class;
		boolean isFlowable = rawType == Flowable.class;
		boolean isSingle = rawType == Single.class;
		boolean isMaybe = rawType == Maybe.class;

		if (isCompletable) {
			return new CompletableCallAdapter(networkErrorAdapter, new InternalCompletableResponseHandler());
		}

		if (!isFlowable && !isSingle && !isMaybe && rawType != Observable.class) {
			return null;
		}

		if (!(returnType instanceof ParameterizedType)) {
			String rxName = rawType.getSimpleName();
			String message = String.format("%s return type must be parametrized as %s<Foo> or %s<? extends Foo>", rxName, rxName, rxName);
			throw new IllegalStateException(message);
		}

		Class<?> responseType = getRawType(getParameterUpperBound(0, (ParameterizedType) returnType));
		if (!baseResponseType.isAssignableFrom(responseType)) {
			return null;
		}

		if (isSingle) {
			return new SingleCallAdapter(responseType, networkErrorAdapter, httpResponseAdapter);

		} else if (isMaybe) {
			return new MaybeCallAdapter(responseType, networkErrorAdapter, httpResponseAdapter);

		} else if (isFlowable) {
			return new FlowableCallAdapter(responseType, networkErrorAdapter, httpResponseAdapter);

		} else {
			return new ObservableCallAdapter(responseType, networkErrorAdapter, httpResponseAdapter);
		}
	}
}
