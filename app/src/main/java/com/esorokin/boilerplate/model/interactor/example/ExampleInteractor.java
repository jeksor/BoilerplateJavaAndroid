package com.esorokin.boilerplate.model.interactor.example;

import com.esorokin.boilerplate.model.ModelWrapper;
import com.esorokin.boilerplate.model.data.ExampleItem;
import com.esorokin.boilerplate.model.interactor.BaseInteractor;
import com.esorokin.boilerplate.model.service.example.ExampleService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ExampleInteractor extends BaseInteractor {
	@Inject
	ExampleService exampleService;

	@Inject
	public ExampleInteractor() {/**/}

	public Observable<ModelWrapper<ExampleItem>> getExampleUseCaseDataEmitter() {
		return exampleService.getUserNameUpdateEmitter();
	}

	public void exampleUserCase() {
		exampleService.requestUserName();
	}
}
