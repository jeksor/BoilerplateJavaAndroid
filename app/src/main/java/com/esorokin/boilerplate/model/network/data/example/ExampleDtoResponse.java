package com.esorokin.boilerplate.model.network.data.example;

import com.esorokin.boilerplate.model.network.data.BaseResponse;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class ExampleDtoResponse extends BaseResponse {
	@SerializedName("exampleJsonKey1")
	private String exampleStringField;

	@SerializedName("exampleJsonKey2")
	private String exampleIntField;
}
