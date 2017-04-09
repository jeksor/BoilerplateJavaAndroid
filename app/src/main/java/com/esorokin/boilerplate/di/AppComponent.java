package com.esorokin.boilerplate.di;

import com.art.alligator.ScreenResolver;
import com.esorokin.boilerplate.di.module.AndroidApplicationModule;
import com.esorokin.boilerplate.di.module.ApiModule;
import com.esorokin.boilerplate.di.module.ErrorProcessingModule;
import com.esorokin.boilerplate.di.module.MapperModule;
import com.esorokin.boilerplate.di.module.NavigationModule;
import com.esorokin.boilerplate.presentation.presenter.example.ExamplePresenter;
import com.esorokin.boilerplate.ui.plugins.NavigationPlugin;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		AndroidApplicationModule.class,
		ApiModule.class,
		MapperModule.class,
		ErrorProcessingModule.class,
		NavigationModule.class})
public interface AppComponent {
	ScreenResolver screenResolver();

	void inject(ExamplePresenter examplePresenter);

	void inject(NavigationPlugin navigationPlugin);
}
