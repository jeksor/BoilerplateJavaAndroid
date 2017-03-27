package com.esorokin.boilerplate.di.module;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {
	@Provides
	@Singleton
	JsonDeserializer<Date> provideDateDeserializer() {
		String[] dateFormats = new String[]{"yyyy-MM-dd'T'HH:mm:ss"};

		return (json, typeOfT, context) -> {
			for (String format : dateFormats) {
				try {
					return new SimpleDateFormat(format, Locale.getDefault()).parse(json.getAsString());
				} catch (ParseException ignore) {
					//ignore, try next
				}
			}
			throw new JsonParseException("Unparsable date: \"" + json.getAsString() + "\". Supported formats: " + Arrays.toString(dateFormats));
		};
	}

	@Provides
	@Singleton
	JsonSerializer<Date> provideDateSerializer() {
		String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";

		return (src, typeOfSrc, context) -> new JsonPrimitive(new SimpleDateFormat(dateFormat, Locale.getDefault()).format(src));
	}

	@Provides
	@Singleton
	Gson provideGson(JsonDeserializer<Date> dateDeserializer, JsonSerializer<Date> dateSerializer) {
		return new GsonBuilder()
				.registerTypeAdapter(Date.class, dateDeserializer)
				.registerTypeAdapter(Date.class, dateSerializer)
				.create();
	}
}
