package com.esorokin.boilerplate.di.module;

import com.esorokin.boilerplate.model.data.ExampleItem;
import com.esorokin.boilerplate.model.mapper.Mapper;
import com.esorokin.boilerplate.model.network.data.example.ExampleDto;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {
	@Singleton
	@Provides
	Mapper<ExampleDto, ExampleItem> provideExampleMapper() {
		return exampleDto -> new ExampleItem(exampleDto.getExampleStringField(), Integer.valueOf(exampleDto.getExampleIntField()));
	}
}
