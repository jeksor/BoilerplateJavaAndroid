package com.esorokin.boilerplate.presentation.navigation;

import java.io.Serializable;

import com.art.alligator.Screen;

import lombok.AllArgsConstructor;
import lombok.Getter;

public final class AppScreen {

	@Getter
	@AllArgsConstructor
	public static class Example implements Screen, Serializable {
		private String exampleParameter;
	}
}
