package com.esorokin.boilerplate.model.interactor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <pre>
 * Interactor it's a middle man between Presenter and Service.
 * Here you can inject one or more services.
 * Also you can use other interactors.
 *
 * Interactor represent concrete business feature.
 * <pre/>
 */
public abstract class BaseInteractor {
	private CompositeDisposable compositeDisposable;

	public BaseInteractor() {
		compositeDisposable = new CompositeDisposable();
	}

	protected void autoDispose(Disposable disposable) {
		compositeDisposable.add(disposable);
	}

	public void onDestroy() {
		compositeDisposable.dispose();
	}
}
