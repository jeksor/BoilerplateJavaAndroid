package com.esorokin.boilerplate.ui.activity;

import java.util.Locale;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.esorokin.boilerplate.R;
import com.esorokin.boilerplate.databinding.ActivityExampleBinding;
import com.esorokin.boilerplate.model.data.ExampleItem;
import com.esorokin.boilerplate.presentation.error.UserError;
import com.esorokin.boilerplate.presentation.presenter.example.ExamplePresenter;
import com.esorokin.boilerplate.presentation.view.example.ExampleView;
import com.esorokin.boilerplate.ui.plugins.BindingPlugin;
import com.esorokin.boilerplate.ui.plugins.DialogErrorPlugin;
import com.esorokin.boilerplate.ui.plugins.ErrorPlugin;
import com.esorokin.boilerplate.ui.plugins.NavigationPlugin;
import com.esorokin.boilerplate.ui.plugins.ProgressPlugin;

public class ExampleActivity extends BaseActivity implements ExampleView {
	@InjectPresenter
	ExamplePresenter presenter;

	private BindingPlugin<ActivityExampleBinding> bindingPlugin;
	private ErrorPlugin errorPlugin;
	private ProgressPlugin progressPlugin;

	@Override
	protected void initPlugins() {
		super.initPlugins();
		compositionPlugin().attach(new NavigationPlugin(this));
		compositionPlugin().attach(bindingPlugin = new BindingPlugin<>(this, R.layout.activity_example));
		compositionPlugin().attach(errorPlugin = new DialogErrorPlugin(() -> this));
		compositionPlugin().attach(progressPlugin = new ProgressPlugin(() -> this));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//extract all parameters for screen. If you launch it from Navigator.
		//AppScreen.Example screen = ScreenUtils.getScreen(this, AppScreen.Example.class);
		//screen.getExampleParameter();
	}

	@Override
	public void showLoading() {
		progressPlugin.showProgress();
	}

	@Override
	public void hideLoading() {
		progressPlugin.hideProgress();
	}

	@Override
	public void showError(UserError error) {
		errorPlugin.showUiError(error, () -> presenter.userHideError());
	}

	@Override
	public void hideError() {
		errorPlugin.hideUiError();
	}

	@Override
	public void setExampleData(ExampleItem exampleItem) {
		bindingPlugin.get().exampleTextView.setText(String.format(Locale.getDefault(), "%s %d", exampleItem.getExampleString(), exampleItem.getExampleInt()));
	}
}
