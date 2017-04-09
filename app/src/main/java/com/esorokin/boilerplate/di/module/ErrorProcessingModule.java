package com.esorokin.boilerplate.di.module;

import com.esorokin.boilerplate.app.StringProvider;
import com.esorokin.boilerplate.presentation.error.DefaultErrorProcessor;
import com.esorokin.boilerplate.presentation.error.ErrorProcessor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ErrorProcessingModule {
	@Singleton
	@Provides
	ErrorProcessor processorDefaultErrorProcessor(StringProvider stringProvider) {
		return new DefaultErrorProcessor(stringProvider);
	}
}
