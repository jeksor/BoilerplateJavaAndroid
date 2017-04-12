package com.esorokin.boilerplate.ui.plugins;

import android.support.v7.app.AppCompatActivity;

import com.art.alligator.NavigationContext;
import com.art.alligator.NavigationContextBinder;
import com.art.alligator.Navigator;
import com.art.alligator.TransitionAnimationProvider;
import com.esorokin.boilerplate.di.DependencyManager;
import com.esorokin.boilerplate.presentation.navigation.animation.DefaultAnimationProvider;
import com.esorokin.boilerplate.ui.plugins.base.BasePlugin;

import javax.inject.Inject;

public class NavigationPlugin extends BasePlugin {
	@Inject
	NavigationContextBinder navigationContextBinder;

	@Inject
	Navigator navigator;

	private AppCompatActivity activity;
	private TransitionAnimationProvider transitionAnimationProvider;

	public NavigationPlugin(AppCompatActivity activity) {
		this(activity, new DefaultAnimationProvider());
	}

	public NavigationPlugin(AppCompatActivity activity, TransitionAnimationProvider transitionAnimationProvider) {
		super();
		DependencyManager.getAppComponent().inject(this);
		this.activity = activity;
		this.transitionAnimationProvider = transitionAnimationProvider;
	}

	@Override
	public void onResume() {
		super.onResume();
		navigationContextBinder.bind(new NavigationContext.Builder(activity)
				.transitionAnimationProvider(transitionAnimationProvider)
				.build());
	}

	@Override
	public void onPause() {
		super.onPause();
		navigationContextBinder.unbind();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		navigator.goBack();
	}
}
