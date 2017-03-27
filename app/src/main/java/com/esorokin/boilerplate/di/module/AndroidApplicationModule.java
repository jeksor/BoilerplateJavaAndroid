package com.esorokin.boilerplate.di.module;

import android.content.Context;

import com.esorokin.boilerplate.app.StringProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidApplicationModule {
	private Context context;

	public AndroidApplicationModule(Context context) {
		this.context = context;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public StringProvider provideStringProvider() {
		return context::getString;
	}
}
