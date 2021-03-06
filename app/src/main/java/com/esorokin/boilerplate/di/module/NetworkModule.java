package com.esorokin.boilerplate.di.module;

import java.util.concurrent.TimeUnit;

import com.esorokin.boilerplate.utils.BuildUtils;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = GsonModule.class)
public class NetworkModule {
	private static final int CONNECT_TIMEOUT = 15;
	private static final int READ_TIMEOUT = 30;
	private static final int WRITE_TIMEOUT = 30;

	@Provides
	@Singleton
	public OkHttpClient provideOkHttpClient() {
		HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
		logInterceptor.setLevel(BuildUtils.isTurnLogs() ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

		return new OkHttpClient.Builder()
				.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
				.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
				.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
				.addInterceptor(logInterceptor)
				.build();
	}

	@Provides
	@Singleton
	public Converter.Factory provideConverter(Gson gson) {
		return GsonConverterFactory.create(gson);
	}

	@Provides
	@Singleton
	public Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Converter.Factory converterFactory) {
		return new Retrofit.Builder()
				.client(okHttpClient)
				.addConverterFactory(converterFactory);
	}
}
