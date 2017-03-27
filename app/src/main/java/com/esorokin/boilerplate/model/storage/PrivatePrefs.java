package com.esorokin.boilerplate.model.storage;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class PrivatePrefs extends EncryptedPrefs {
	//region Create
	public static final String PREFS_NAME = "PrivatePrefs";

	@Inject
	public PrivatePrefs() {
		super(PREFS_NAME);
	}

	//endregion

	//TODO example. remove me.
	/*private static final String TAG = "PrivatePrefs";
	private static final String KEY_AUTH_DATA = TAG + ".KEY_AUTH_DATA";

	public AuthenticateData getAuthenticateData() {
		return getGson().fromJson(getSharedPreferences().getString(KEY_AUTH_DATA, ""), AuthenticateData.class);
	}

	public void setAuthenticateData(AuthenticateData authenticateData) {
		if (authenticateData == null) {
			getEditor().remove(KEY_AUTH_DATA).commit();
		} else {
			getEditor().putString(KEY_AUTH_DATA, getGson().toJson(authenticateData)).apply();
		}
	}*/
}
