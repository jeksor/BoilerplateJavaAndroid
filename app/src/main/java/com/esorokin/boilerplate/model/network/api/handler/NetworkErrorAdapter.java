package com.esorokin.boilerplate.model.network.api.handler;

public interface NetworkErrorAdapter {
	Throwable adaptNetworkError(Throwable throwable);
}
