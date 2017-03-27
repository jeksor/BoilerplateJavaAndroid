package com.esorokin.boilerplate.model.interactor;

import com.esorokin.boilerplate.model.service.SomethingService;

import javax.inject.Inject;

public class TestInteractor extends BaseInteractor {
	@Inject
	SomethingService somethingService;

	@Inject
	public TestInteractor() {/**/}
}
