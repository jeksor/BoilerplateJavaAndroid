package com.esorokin.boilerplate.presentation.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.esorokin.boilerplate.model.interactor.BaseInteractor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends MvpView, I extends BaseInteractor> extends MvpPresenter<V> {
	private CompositeDisposable compositeDisposable;
	private I interactor;

	public BasePresenter() {
		this.compositeDisposable = new CompositeDisposable();
	}

	protected void injectInteractor(I interactor) {
		this.interactor = interactor;
	}

	public I interactor() {
		return interactor;
	}

	protected void autoDispose(Disposable disposable) {
		compositeDisposable.add(disposable);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		compositeDisposable.dispose();
		interactor.onDestroy();
	}
}