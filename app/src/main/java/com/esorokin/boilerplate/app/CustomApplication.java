package com.esorokin.boilerplate.app;

import android.app.Application;

import com.esorokin.boilerplate.di.DependencyManager;
import com.esorokin.boilerplate.utils.BuildUtils;

import io.realm.Realm;
import timber.log.Timber;

public class CustomApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		setupLogSystem();
		DependencyManager.init(this);
		Realm.init(this);
	}

	private void setupLogSystem() {
		if (BuildUtils.isTurnLogs()) {
			Timber.plant(new Timber.DebugTree());
		}
	}
}
