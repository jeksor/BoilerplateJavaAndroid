package com.esorokin.boilerplate.presentation.presenter.example;

import com.arellomobile.mvp.InjectViewState;
import com.esorokin.boilerplate.di.DependencyManager;
import com.esorokin.boilerplate.model.interactor.example.ExampleInteractor;
import com.esorokin.boilerplate.presentation.error.ErrorProcessor;
import com.esorokin.boilerplate.presentation.presenter.BasePresenter;
import com.esorokin.boilerplate.presentation.presenter.PresenterUtils;
import com.esorokin.boilerplate.presentation.view.example.ExampleView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class ExamplePresenter extends BasePresenter<ExampleView> {
	@Inject
	ExampleInteractor interactor;

	@Inject
	ErrorProcessor errorProcessor;

	public ExamplePresenter() {
		super();
		DependencyManager.getAppComponent().inject(this);

		interactor.getExampleUseCaseDataEmitter()
				.observeOn(AndroidSchedulers.mainThread())
				.compose(PresenterUtils.defaultLoadErrorCompleteConsumer(errorProcessor, getViewState()))
				.subscribe(exampleItem -> getViewState().setExampleData(exampleItem));

		interactor.exampleUserCase();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		interactor.onDestroy();
	}

	public void userHideError() {
		getViewState().hideError();
	}
}
