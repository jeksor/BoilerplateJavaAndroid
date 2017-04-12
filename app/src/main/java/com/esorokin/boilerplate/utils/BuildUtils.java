package com.esorokin.boilerplate.utils;

import com.esorokin.boilerplate.BuildConfig;

public final class BuildUtils {
	private BuildUtils() {
		//utils
	}

	public static boolean isRelease() {
		return !BuildConfig.DEBUG;
	}

	public static boolean isDebug() {
		return BuildConfig.DEBUG;
	}

	public static boolean isTurnLogs() {
		return BuildConfig.IS_TURN_LOGS;
	}
}
