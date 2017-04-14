package com.esorokin.boilerplate.model.network.api;

import com.esorokin.boilerplate.model.network.data.example.ExampleDtoResponse;

import io.reactivex.Maybe;
import retrofit2.http.GET;

public interface ExampleApi {
	@GET("api/v1/forecasts/current")
	Maybe<ExampleDtoResponse> getExample();
}
