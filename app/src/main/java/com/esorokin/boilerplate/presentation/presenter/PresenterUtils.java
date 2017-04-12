package com.esorokin.boilerplate.presentation.presenter;

import com.arellomobile.mvp.MvpView;
import com.esorokin.boilerplate.model.ModelWrapper;
import com.esorokin.boilerplate.presentation.error.ErrorProcessor;
import com.esorokin.boilerplate.presentation.error.UserError;
import com.esorokin.boilerplate.presentation.view.ErrorView;
import com.esorokin.boilerplate.presentation.view.LoadingView;

import io.reactivex.ObservableTransformer;
import timber.log.Timber;

public final class PresenterUtils {
	private PresenterUtils() {
		//utils
	}

	public static <Data> ObservableTransformer<ModelWrapper<Data>, Data> defaultLoadErrorCompleteConsumer(ErrorProcessor errorProcessor, MvpView viewState) {
		return source -> source
				.doOnNext(modelWrapper -> {
					if (modelWrapper.isLoading()) {
						hideError(viewState);
						showLoading(viewState);
					}

					if (modelWrapper.isError()) {
						hideLoading(viewState);
						showError(viewState, errorProcessor.processError(modelWrapper.getError()));
					}

					if (modelWrapper.isComplete()) {
						hideLoading(viewState);
						hideError(viewState);
					}
				})
				.filter(ModelWrapper::isComplete)
				.map(ModelWrapper::getData);
	}

	private static void showLoading(MvpView viewState) {
		if (viewState instanceof LoadingView) {
			((LoadingView) viewState).showLoading();
		} else {
			Timber.e("View [%s] should implement LoadingView for using default handler.", viewState.toString());
		}
	}

	private static void hideLoading(MvpView viewState) {
		if (viewState instanceof LoadingView) {
			((LoadingView) viewState).hideLoading();
		} else {
			Timber.e("View [%s] should implement LoadingView for using default handler.", viewState.toString());
		}
	}

	private static void showError(MvpView viewState, UserError error) {
		if (viewState instanceof ErrorView) {
			((ErrorView) viewState).showError(error);
		} else {
			Timber.e("View [%s] should implement ErrorView for using default handler.", viewState.toString());
		}
	}

	private static void hideError(MvpView viewState) {
		if (viewState instanceof ErrorView) {
			((ErrorView) viewState).hideError();
		} else {
			Timber.e("View [%s] should implement ErrorView for using default handler.", viewState.toString());
		}
	}
}
