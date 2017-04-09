package com.esorokin.boilerplate.model;

import android.support.annotation.NonNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelWrapper<Data> {
	public static <T> ModelWrapper<T> loading() {
		return new ModelWrapper<>(ModelState.LOADING, null, null);
	}

	public static <T> ModelWrapper<T> error(@NonNull Throwable throwable) {
		return new ModelWrapper<>(ModelState.ERROR, throwable, null);
	}

	public static <T> ModelWrapper<T> complete(@NonNull T data) {
		return new ModelWrapper<>(ModelState.COMPLETE, null, data);
	}

	public static <T> ModelWrapper<T> create(@NonNull ModelState state, @NonNull T data) {
		return new ModelWrapper<>(state, null, data);
	}

	public static <T> ModelWrapper<T> create(@NonNull ModelState state, @NonNull Throwable error) {
		return new ModelWrapper<>(state, error, null);
	}

	public static <T> ModelWrapper<T> create(@NonNull ModelState state, @NonNull T data, @NonNull Throwable error) {
		return new ModelWrapper<>(state, error, data);
	}

	private ModelState state;
	private Throwable error;
	private Data data;

	public boolean isLoading() {
		return ModelState.LOADING.equals(state);
	}

	public boolean isComplete() {
		return ModelState.COMPLETE.equals(state);
	}

	public boolean isError() {
		return ModelState.ERROR.equals(state);
	}

	private enum ModelState {
		LOADING,
		COMPLETE,
		ERROR
	}
}
