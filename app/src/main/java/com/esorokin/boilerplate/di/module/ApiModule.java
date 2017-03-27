package com.esorokin.boilerplate.di.module;

import com.esorokin.boilerplate.R;
import com.esorokin.boilerplate.app.StringProvider;
import com.esorokin.boilerplate.model.network.api.SomethingApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class ApiModule {
	private static final String BASE_API_QUALIFIER = "BASE_API_QUALIFIER";

	private final String baseApiUrl;

	public ApiModule(StringProvider stringProvider) {
		this.baseApiUrl = getBaseApiUrl(stringProvider);
	}

	private String getBaseApiUrl(StringProvider stringProvider) {
		return stringProvider.getString(R.string.base_api_url);
	}

	@Provides
	@Named(BASE_API_QUALIFIER)
	@Singleton
	Retrofit provideBaseRetrofit(Retrofit.Builder retrofitBuilder) {
		return retrofitBuilder
				.baseUrl(baseApiUrl)
				.build();
	}

	@Provides
	@Singleton
	SomethingApi provideSomethingApi(@Named(BASE_API_QUALIFIER) Retrofit retrofit) {
		return retrofit.create(SomethingApi.class);
	}
}