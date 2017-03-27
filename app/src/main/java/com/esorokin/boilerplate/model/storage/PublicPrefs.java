package com.esorokin.boilerplate.model.storage;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class PublicPrefs extends Prefs {
	//region Create
	private static final String PREFS_NAME = "PublicPrefs";

	@Inject
	public PublicPrefs() {
		super(PREFS_NAME);
	}

	//endregion
}
