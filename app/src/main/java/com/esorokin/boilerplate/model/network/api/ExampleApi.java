package com.esorokin.boilerplate.model.network.api;

import com.esorokin.boilerplate.model.network.data.example.ExampleDto;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExampleApi {
	@GET("api/v1/forecasts/current")
	Call<ExampleDto> getExample();
}
