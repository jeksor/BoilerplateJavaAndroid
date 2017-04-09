package com.esorokin.boilerplate.di;

import com.esorokin.boilerplate.di.module.AndroidApplicationModule;
import com.esorokin.boilerplate.di.module.ApiModule;
import com.esorokin.boilerplate.di.module.ErrorProcessingModule;
import com.esorokin.boilerplate.di.module.MapperModule;
import com.esorokin.boilerplate.model.network.api.ExampleApi;
import com.esorokin.boilerplate.presentation.presenter.example.ExamplePresenter;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AndroidApplicationModule.class, ApiModule.class, MapperModule.class, ErrorProcessingModule.class})
public interface AppComponent {
	Gson gson();

	ExampleApi somethingApi();

	void inject(ExamplePresenter examplePresenter);
}
