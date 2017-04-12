package com.esorokin.boilerplate.model.service;

import com.esorokin.boilerplate.model.ModelWrapper;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.subjects.Subject;

public final class ServiceUtils {
	private ServiceUtils() {
		//utils
	}

	public static <Data> SingleTransformer<Data, Data> transitEventsToEmitter(Subject<ModelWrapper<Data>> emitter) {
		return source -> source
				.doOnSubscribe(disposable -> emitter.onNext(ModelWrapper.loading()))
				.doOnSuccess(credentials -> emitter.onNext(ModelWrapper.complete(credentials)))
				.doOnError(throwable -> emitter.onNext(ModelWrapper.error(throwable)));
	}

	public static void subscribeIgnoreResult(Single<?> source) {
		source.toCompletable().onErrorComplete().subscribe();
	}

	public static void subscribeIgnoreResult(Maybe<?> source) {
		source.onErrorComplete().subscribe();
	}

	public static void subscribeIgnoreResult(Completable source) {
		source.onErrorComplete().subscribe();
	}
}
