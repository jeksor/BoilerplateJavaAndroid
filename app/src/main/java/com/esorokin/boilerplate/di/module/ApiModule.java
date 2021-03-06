package com.esorokin.boilerplate.di.module;

import com.esorokin.boilerplate.R;
import com.esorokin.boilerplate.app.StringProvider;
import com.esorokin.boilerplate.model.network.api.DefaultNetworkErrorAdapter;
import com.esorokin.boilerplate.model.network.api.ExampleApi;
import com.esorokin.boilerplate.model.network.api.DefaultResponseAdapter;
import com.esorokin.boilerplate.model.network.api.handler.RxCallAdapterFactory;
import com.esorokin.boilerplate.model.network.data.BaseResponse;

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
	Retrofit provideBaseRetrofit(Retrofit.Builder retrofitBuilder, DefaultNetworkErrorAdapter errorAdapter, DefaultResponseAdapter responseAdapter) {
		return retrofitBuilder
				.baseUrl(baseApiUrl)
				.addCallAdapterFactory(RxCallAdapterFactory.create(BaseResponse.class, errorAdapter, responseAdapter))
				.build();
	}

	@Provides
	@Singleton
	ExampleApi provideSomethingApi(@Named(BASE_API_QUALIFIER) Retrofit retrofit) {
		return retrofit.create(ExampleApi.class);
	}
}
