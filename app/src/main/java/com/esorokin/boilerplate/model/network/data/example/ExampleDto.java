package com.esorokin.boilerplate.model.network.data.example;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class ExampleDto {
	@SerializedName("exampleJsonKey1")
	private String exampleStringField;

	@SerializedName("exampleJsonKey2")
	private String exampleIntField;
}
