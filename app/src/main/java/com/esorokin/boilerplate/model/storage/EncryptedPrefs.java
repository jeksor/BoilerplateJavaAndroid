package com.esorokin.boilerplate.model.storage;

import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;

/**
 * More slowly than default prefs.
 * Use only for sensitive user data.
 */
class EncryptedPrefs extends Prefs {
	public EncryptedPrefs(String sharedPrefsName) {
		super(sharedPrefsName);
	}

	@Override
	protected SharedPreferences createSharedPreferences() {
		return new SecurePreferences(getContext().getApplicationContext(), "", getSharedPrefsName());
	}
}
