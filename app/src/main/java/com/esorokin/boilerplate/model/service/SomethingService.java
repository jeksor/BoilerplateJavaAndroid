package com.esorokin.boilerplate.model.service;

import com.esorokin.boilerplate.model.network.api.SomethingServerApi;
import com.esorokin.boilerplate.model.storage.PrivatePrefs;
import com.esorokin.boilerplate.model.storage.PublicPrefs;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Services can't interacting with other services.
 * Service using ServerApi, Database, Memory cache and live across application live or dagger scope.
 */
@Singleton
public class SomethingService {
	@Inject
	SomethingServerApi somethingServerApi;

	@Inject
	PublicPrefs publicPrefs;

	@Inject
	PrivatePrefs privatePrefs;

	@Inject
	public SomethingService() {/**/}
}
