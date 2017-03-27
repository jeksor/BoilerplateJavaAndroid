package com.esorokin.boilerplate.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.esorokin.boilerplate.di.DependencyManager;
import com.esorokin.boilerplate.model.interactor.LaunchInteractor;
import com.esorokin.boilerplate.presentation.view.LaunchView;

import javax.inject.Inject;

@InjectViewState
public class LaunchPresenter extends BasePresenter<LaunchView, LaunchInteractor> {
	@Inject
	@Override
	protected void injectInteractor(LaunchInteractor interactor) {
		super.injectInteractor(interactor);
	}

	public LaunchPresenter() {
		DependencyManager.getAppComponent().inject(this);

		interactor().test();
	}
}
