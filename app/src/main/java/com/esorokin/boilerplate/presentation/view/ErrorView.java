package com.esorokin.boilerplate.presentation.view;

import com.esorokin.boilerplate.presentation.error.UserError;

public interface ErrorView {
	void showError(UserError error);
	void hideError();
}
