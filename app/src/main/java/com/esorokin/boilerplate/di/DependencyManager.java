package com.esorokin.boilerplate.di;

import android.content.Context;

import com.esorokin.boilerplate.di.module.AndroidApplicationModule;
import com.esorokin.boilerplate.di.module.ApiModule;
import com.esorokin.boilerplate.di.module.GsonModule;
import com.esorokin.boilerplate.di.module.NetworkModule;

public final class DependencyManager {
	//region Singleton
	private static volatile DependencyManager instance;

	private static DependencyManager get() {
		if (instance == null) {
			synchronized (DependencyManager.class) {
				if (instance == null) {
					instance = new DependencyManager();
				}
			}
		}

		return instance;
	}

	private DependencyManager() {/**/}
	//endregion

	private AppComponent appComponent;

	public void initAppComponent(Context context) {
		appComponent = DaggerAppComponent.builder()
				.androidApplicationModule(new AndroidApplicationModule(context))
				.gsonModule(new GsonModule())
				.networkModule(new NetworkModule())
				.apiModule(new ApiModule(context::getString))
				.build();
	}

	public static void init(Context context) {
		get().initAppComponent(context);
	}

	public static AppComponent getAppComponent() {
		return get().appComponent;
	}
}
