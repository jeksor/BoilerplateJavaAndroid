package com.esorokin.boilerplate.di;

import com.esorokin.boilerplate.di.module.AndroidApplicationModule;
import com.esorokin.boilerplate.di.module.ApiModule;
import com.esorokin.boilerplate.model.network.api.SomethingApi;
import com.esorokin.boilerplate.presentation.presenter.LaunchPresenter;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AndroidApplicationModule.class, ApiModule.class})
public interface AppComponent {
	Gson gson();

	SomethingApi somethingApi();

	void inject(LaunchPresenter launchPresenter);
}
