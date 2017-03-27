package com.esorokin.boilerplate.model.network.exception;

@SuppressWarnings("serial")
public class NetworkException extends RuntimeException {
	public NetworkException() {
		super("Network trouble. No connection, etc.");
	}
}
