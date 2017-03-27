package com.esorokin.boilerplate.model.interactor;

import com.esorokin.boilerplate.model.service.SomethingService;

import javax.inject.Inject;

public class LaunchInteractor extends BaseInteractor {
	@Inject
	SomethingService somethingService;

	@Inject
	TestInteractor testInteractor;

	@Inject
	public LaunchInteractor() {/**/}

	public void test() {
		testInteractor.hashCode();
	}
}
