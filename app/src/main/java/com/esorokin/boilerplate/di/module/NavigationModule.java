package com.esorokin.boilerplate.di.module;

import com.art.alligator.AndroidNavigator;
import com.art.alligator.NavigationContextBinder;
import com.art.alligator.Navigator;
import com.art.alligator.ScreenResolver;
import com.esorokin.boilerplate.presentation.navigation.AppNavigationFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationModule {
	private AndroidNavigator navigator;

	public NavigationModule() {
		navigator = new AndroidNavigator(new AppNavigationFactory());
	}

	@Provides
	@Singleton
	public Navigator provideNavigator() {
		return navigator;
	}

	@Provides
	@Singleton
	public NavigationContextBinder provideNavigationContextBinder() {
		return navigator;
	}

	@Provides
	@Singleton
	public ScreenResolver proScreenResolver() {
		return navigator.getScreenResolver();
	}
}
