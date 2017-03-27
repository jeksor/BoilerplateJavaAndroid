package com.esorokin.boilerplate.presentation.errors;

/**
 * Using by presenters for localize domain exceptions into user friendly messages.
 */
public interface ErrorProcessor {
	UserError processError(Throwable throwable);
}
