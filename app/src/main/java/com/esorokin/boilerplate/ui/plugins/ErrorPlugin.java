package com.esorokin.boilerplate.ui.plugins;

import android.app.Dialog;
import android.support.annotation.Nullable;

import com.esorokin.boilerplate.presentation.errors.UserError;
import com.esorokin.boilerplate.ui.plugins.base.BaseDependencyPlugin;

public abstract class ErrorPlugin<T extends ContextProvider> extends BaseDependencyPlugin<T> {
	public ErrorPlugin(T contextProvider) {
		super(contextProvider);
	}

	public abstract void showUiError(UserError userError);

	public abstract void showUiError(UserError userError, @Nullable Dialog.OnClickListener hideErrorListener);

	public abstract void hideUiError();
}
