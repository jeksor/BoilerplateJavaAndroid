package com.esorokin.boilerplate.presentation.error;

import com.esorokin.boilerplate.R;
import com.esorokin.boilerplate.app.StringProvider;
import com.esorokin.boilerplate.model.network.exception.ApiException;
import com.esorokin.boilerplate.model.network.exception.NetworkException;
import com.esorokin.boilerplate.model.network.exception.TimeoutException;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class DefaultErrorProcessor implements ErrorProcessor {
	private final StringProvider stringProvider;

	@Inject
	public DefaultErrorProcessor(StringProvider stringProvider) {
		this.stringProvider = stringProvider;
	}

	@Override
	public UserError processError(Throwable throwable) {
		if (throwable instanceof ApiException) {
			return handleApiException(((ApiException) throwable));

		} else if (throwable instanceof NetworkException) {
			return handleNetworkException((NetworkException) throwable);

		} else {
			return handleUnknownException(throwable);
		}
	}

	protected UserError handleNetworkException(NetworkException networkException) {
		if (networkException instanceof TimeoutException) {
			return new UserError(stringProvider.getString(R.string.sorry), stringProvider.getString(R.string.error_timeout));
		} else {
			return new UserError(stringProvider.getString(R.string.sorry), stringProvider.getString(R.string.error_internet_connection));
		}
	}

	protected UserError handleApiException(ApiException apiException) {
		/*if (apiException.getErrorResponse() == null || TextUtils.isEmpty(apiException.getErrorResponse().getErrorMessage())) {
			return handleUnknownException(apiException);

		} else {
			return new UserError(stringProvider.getString(R.string.sorry), apiException.getErrorResponse().getErrorMessage());
		}*/
		return handleUnknownException(apiException);
	}

	protected UserError handleUnknownException(Throwable unknown) {
		Timber.w("Unknown exception! Maybe something wrong with this app.");
		Timber.w(unknown);

		return new UserError(stringProvider.getString(R.string.error_unknown_title), stringProvider.getString(R.string.error_unknown_message));
	}
}
