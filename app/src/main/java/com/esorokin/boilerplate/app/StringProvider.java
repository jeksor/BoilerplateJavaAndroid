package com.esorokin.boilerplate.app;

import android.support.annotation.StringRes;

public interface StringProvider {
	String getString(@StringRes int stringRes, Object... formatArgs);
}
