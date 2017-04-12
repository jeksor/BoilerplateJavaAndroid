package com.esorokin.boilerplate.model.service.example;

import com.esorokin.boilerplate.model.ModelWrapper;
import com.esorokin.boilerplate.model.data.ExampleItem;
import com.esorokin.boilerplate.model.mapper.Mapper;
import com.esorokin.boilerplate.model.network.api.ExampleServerApi;
import com.esorokin.boilerplate.model.network.data.example.ExampleDto;
import com.esorokin.boilerplate.model.service.ServiceUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import lombok.Getter;

/**
 * Services can't interacting with other services.
 * Service using ServerApi, Database, Memory cache and live across application live or dagger scope.
 */
@Singleton
public class ExampleService {
	@Inject
	ExampleServerApi exampleServerApi;

	@Inject
	Mapper<ExampleDto, ExampleItem> exampleItemMapper;

	@Getter
	private final Subject<ModelWrapper<ExampleItem>> userNameUpdateEmitter = PublishSubject.create();

	@Inject
	public ExampleService() {
		super();
	}

	public void requestUserName() {
		ServiceUtils.subscribeIgnoreResult(userName());
	}

	public Single<ExampleItem> userName() {
		return exampleServerApi.getExample()
				.subscribeOn(Schedulers.io())
				.map(exampleItemMapper::convert)
				.compose(ServiceUtils.transitEventsToEmitter(userNameUpdateEmitter));
	}
}
