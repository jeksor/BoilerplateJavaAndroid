package com.esorokin.boilerplate.model.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

abstract class Prefs {
	private String sharedPrefsName;
	private SharedPreferences sharedPrefs;

	@Inject
	protected Gson gson;

	@Inject
	protected Context context;

	public Prefs(String sharedPrefsName) {
		this.sharedPrefsName = sharedPrefsName;
	}

	protected SharedPreferences getSharedPreferences() {
		if (sharedPrefs == null) {
			sharedPrefs = createSharedPreferences();
		}

		return sharedPrefs;
	}

	protected final SharedPreferences.Editor getEditor() {
		return getSharedPreferences().edit();
	}

	protected Gson getGson() {
		return gson;
	}

	protected Context getContext() {
		return context;
	}

	protected SharedPreferences createSharedPreferences() {
		return context.getSharedPreferences(getSharedPrefsName(), Context.MODE_PRIVATE);
	}

	protected String getSharedPrefsName() {
		return sharedPrefsName;
	}

	public void clear() {
		getEditor().clear().commit();
	}
}
