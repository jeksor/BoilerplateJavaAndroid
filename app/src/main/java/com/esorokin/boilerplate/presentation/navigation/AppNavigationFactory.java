package com.esorokin.boilerplate.presentation.navigation;

import com.art.alligator.navigationfactories.RegistryNavigationFactory;
import com.esorokin.boilerplate.ui.activity.ExampleActivity;

public class AppNavigationFactory extends RegistryNavigationFactory {
	public AppNavigationFactory() {
		super();
		registerActivity(AppScreen.Example.class, ExampleActivity.class);
	}
}
